import java.awt.EventQueue;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class MainWindow extends JFrame {

	private JPanel mainPanel;
	private JTable rosterTable;
	private JPanel rosterPanel;
	private JTabbedPane mainWindowTabbedPane;
	private JTable scheduleTable;
	private DefaultTableModel playerModel;
	private DefaultTableModel scheduleModel;
	private GameDatabase gDatabase;
	private PriorityQueue<Game> gameQueue;
	private ArrayList<Game> gameList;
	private PlayerDatabase pDatabase;
	private PriorityQueue<Player> playerQueue;
	private ArrayList<Player> playerList;
	private JTable rosterTeamStatsTable;
	private DefaultTableModel teamStatsModel;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public MainWindow() 
	{
		initializeMainWindow();
	}

	public void updateToRosterTab() // Sets the selected tab to the roster tab
	{
		mainWindowTabbedPane.setSelectedIndex(1); 
	}

	private void updateTeamStats() // Updates the team's statistics on the roster tab
	{
		String teamName = "Team";
		int teamPA = 0;
		int teamWalks = 0;
		int teamSingles = 0;
		int teamDoubles = 0;
		int teamTriples = 0;
		int teamHR = 0;
		for (int i = 0; i<playerList.size(); i++)
		{
			teamPA+=playerList.get(i).getPlateAppearances();
			teamWalks+= playerList.get(i).getWalks();
			teamSingles+= playerList.get(i).getSingles();
			teamDoubles+= playerList.get(i).getDoubles();
			teamTriples+= playerList.get(i).getTriples();
			teamHR+= playerList.get(i).getHR();
		}
		Player team = new Player(teamName, teamPA, teamWalks, teamSingles, teamDoubles, teamTriples, teamHR); //Stores the team as a player
		if(teamStatsModel.getRowCount()!=0) // If the table is not empty, remove the current row storing the old team statistics
			teamStatsModel.removeRow(0);
		teamStatsModel.insertRow(0, new String [] {team.getAVG(), team.getOBP(),team.getSLG(),team.getOPS()}); //Add the new statistics to the team stats table

	}

	private void initializeSchedule()
	{
		// Read the data from file, put it into an ArrayList to be stored
		gDatabase = new GameDatabase();
		gameQueue= gDatabase.getData();
		gameList= new ArrayList<Game>();
		while (!gameQueue.isEmpty())
			gameList.add(gameQueue.remove());

//Initializing Elements of the schedule tab and setting their properties
		//SchedulePanel
		JPanel schedulePanel = new JPanel();
		mainWindowTabbedPane.addTab("Schedule", null, schedulePanel, null);
		schedulePanel.setLayout(null);

		//ScheduleAddButton
		JButton scheduleAddButton = new JButton("Add");
		scheduleAddButton.setBounds(6, 345, 110, 25);
		schedulePanel.add(scheduleAddButton);

		//ScheduleEditButton
		JButton scheduleEditButton = new JButton("Edit");
		scheduleEditButton.setBounds(126, 345, 110, 25);
		schedulePanel.add(scheduleEditButton);

		//ScheduleRemoveButton
		JButton scheduleRemoveButton = new JButton("Remove");
		scheduleRemoveButton.setBackground(Color.RED);
		scheduleRemoveButton.setBounds(246, 345, 110, 25);
		scheduleRemoveButton.setForeground(Color.BLACK);
		schedulePanel.add(scheduleRemoveButton);

		//ScheduleTable
		scheduleTable = new JTable();
		scheduleModel=new DefaultTableModel(getGameMatrix(),
				new String[] {
						"Opponent", "Date", "Location", "Jersey Color", "Score (Team, Opponent)"
				}
				);
		scheduleTable.setDefaultEditor(Object.class, null); //Table cannot be directly edited
		scheduleTable.getTableHeader().setReorderingAllowed(false); //Table cannot be reordered
		scheduleTable.getTableHeader().setResizingAllowed(false); //Table cannot be resized
		scheduleTable.setModel(scheduleModel);

		//ScheduleScrollPane
		JScrollPane scheduleScrollPane = new JScrollPane();
		scheduleScrollPane.setBounds(12, 11, 727, 325);
		schedulePanel.add(scheduleScrollPane);
		scheduleScrollPane.setViewportView(scheduleTable);

		//scheduleClearButton
		JButton scheduleClearButton = new JButton("Clear");
		scheduleClearButton.setBackground(Color.RED);
		scheduleClearButton.setForeground(Color.BLACK);
		scheduleClearButton.setBounds(627, 345, 110, 25);
		schedulePanel.add(scheduleClearButton);

//Action Listeners
		//ScheduleAddButton
		scheduleAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GameInfoWindow addWindow = new GameInfoWindow(null);
				addWindow.setVisible(true);
				dispose();
			}
		}); // When the schedule add button is pushed, open the game add window, pass in null to GameInfoWindow's constructor to show that a new game is being added and not a current game being edited

		//ScheduleEditButton
		scheduleEditButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				int slctRow = scheduleTable.getSelectedRow();
				if(slctRow >= 0) // If there is a row selected
				{
					Game slctGame = gameList.get(slctRow); 
					GameInfoWindow frame = new GameInfoWindow(slctGame); // Open a GameInfoWindow and pass in the selected game to be edited
					frame.setVisible(true);
					dispose();
				}
				else // If there isn't a row selected
					showMessageDialog(null, "Ensure a game is selected to edit", "Cannot Edit Game",
							JOptionPane.WARNING_MESSAGE);
			}

		});

		//scheduleRemoveButton
		scheduleRemoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				int index = scheduleTable.getSelectedRow();
				if (index >= 0) // If there is a row selected
				{  
					int dialogResult = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to remove the selected \ngame?",
							"Remove Confirmation", JOptionPane.ERROR_MESSAGE);
					if (dialogResult == 0) // If the user selects "Yes" in the dialog box
					{
						scheduleModel.removeRow(index);
						gameList.remove(index);
						PriorityQueue<Game> temp = new PriorityQueue<Game>();
						for(int i = 0; i < gameList.size(); i++)
							temp.add(gameList.get(i));
						gDatabase.save(temp);
					}
				}
				else // If there isn't a row selected
					showMessageDialog(null, "Ensure a game is selected to remove", "Cannot Remove Game",
							JOptionPane.WARNING_MESSAGE);
			}
		});

		//ScheduleClearButton
		scheduleClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (gameList.size()!=0) //If there are games to be cleared
				{
					int dialogResult = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to clear the \nschedule? (All saved data will be removed)",
							"Reset Confirmation", JOptionPane.ERROR_MESSAGE);
					if (dialogResult == 0) //If the user selects "Yes" in the dialog box
					{
						while (gameList.size() != 0) 
						{
							scheduleModel.removeRow(0);
							gameList.remove(0);
						}
						gDatabase.clearDatabase();
					}
				}
				else //There are no games in the schedule
					showMessageDialog(null, "Schedule is already empty", "Cannot Clear Schedule", JOptionPane.WARNING_MESSAGE);
			}
		});

	}

	private void initializeRoster()
	{
		// Read the data from file, put it into an ArrayList to be stored
		pDatabase = new PlayerDatabase();
		playerQueue= pDatabase.getData();
		playerList= new ArrayList<Player>();
		while (!playerQueue.isEmpty())
			playerList.add(playerQueue.remove());

//Initializing Elements of the roster tab and setting their properties
		//rosterPanel
		rosterPanel = new JPanel();
		mainWindowTabbedPane.addTab("Roster", null, rosterPanel, null);
		rosterPanel.setLayout(null);

		//rosterAddButton
		JButton rosterAddButton = new JButton("Add");
		rosterAddButton.setBounds(6, 347, 117, 29);
		rosterPanel.add(rosterAddButton);

		//rosterEditButton
		JButton rosterEditButton = new JButton("Edit");
		rosterEditButton.setBounds(126, 347, 117, 29);
		rosterPanel.add(rosterEditButton);

		//rosterTable
		rosterTable = new JTable();
		playerModel=new DefaultTableModel(getPlayerMatrix(), new String[] {"Name", "AVG", "OBP", "SLG", "OPS"});
		rosterTable.setDefaultEditor(Object.class, null); //Table cannot be directly edited
		rosterTable.getTableHeader().setReorderingAllowed(false); //Table cannot be directly reordered
		rosterTable.getTableHeader().setResizingAllowed(false);  //Table cannot be resized
		rosterTable.setModel(playerModel);
		
		//Add all of the players from file to the table

		//rosterRemoveButton
		JButton rosterRemoveButton = new JButton("Remove");
		rosterRemoveButton.setBackground(Color.RED);
		rosterRemoveButton.setBounds(245, 347, 117, 29);
		rosterRemoveButton.setForeground(Color.BLACK);
		rosterPanel.add(rosterRemoveButton);

		//rosterScrollPane
		JScrollPane rosterScrollPane = new JScrollPane();
		rosterScrollPane.setBounds(12, 11, 727, 271);
		rosterPanel.add(rosterScrollPane);	
		rosterScrollPane.setViewportView(rosterTable);

		//rosterClearButton
		JButton rosterClearButton = new JButton("Clear");
		rosterClearButton.setBackground(Color.RED);
		rosterClearButton.setForeground(Color.BLACK);
		rosterClearButton.setBounds(620, 347, 117, 29);
		rosterPanel.add(rosterClearButton);

		//rosterTeamStatsLabel
		JLabel rosterTeamStatsLabel = new JLabel("Team Stats:");
		rosterTeamStatsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rosterTeamStatsLabel.setBounds(17, 294, 131, 43);
		rosterPanel.add(rosterTeamStatsLabel);

		//rosterTeamStatsScrollPane
		JScrollPane rosterTeamStatsScrollPane = new JScrollPane();
		rosterTeamStatsScrollPane.setEnabled(true);
		rosterTeamStatsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		rosterTeamStatsScrollPane.setBounds(126, 298, 611, 38);
		rosterPanel.add(rosterTeamStatsScrollPane);

		//rosterTeamStatsTable
		rosterTeamStatsTable = new JTable();
		teamStatsModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"AVG", "OBP", "SLG", "OPS"
				}
				);
		rosterTeamStatsTable.setModel(teamStatsModel);
		rosterTeamStatsScrollPane.add(rosterTeamStatsTable);
		rosterTeamStatsTable.setDefaultEditor(Object.class, null); //Table cannot be directly edited
		rosterTeamStatsTable.getTableHeader().setReorderingAllowed(false); //Table cannot be reordered
		rosterTeamStatsTable.getTableHeader().setResizingAllowed(false); //Table cannot be resized
		rosterTeamStatsScrollPane.setViewportView(rosterTeamStatsTable);
		updateTeamStats(); //Calculates the statistics for the team and adds them to the table

//Action Listeners
		//rosterAddButton
		rosterAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				PlayerInfoWindow addWindow = new PlayerInfoWindow(null);
				addWindow.setVisible(true);
				dispose();
			}
		});

		//rosterEditButton
		rosterEditButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) 
			{
				int slctRow = rosterTable.getSelectedRow();
				if(slctRow >= 0) //If a row is selected
				{
					Player slctPlayer = playerList.get(slctRow);
					PlayerInfoWindow frame = new PlayerInfoWindow(slctPlayer); //Open a player info window and pass in the selected player to be edited
					frame.setVisible(true);
					dispose();
				}
				else
					showMessageDialog(null, "Ensure a player is selected to edit", "Cannot Edit Player",
							JOptionPane.WARNING_MESSAGE);
			}
		});

		//rosterRemoveButton
		rosterRemoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int index = rosterTable.getSelectedRow();
				if (index >= 0) //If a row is selected
				{ 
					int dialogResult = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to remove the selected \nplayer?",
							"Remove Confirmation", JOptionPane.ERROR_MESSAGE);
					if (dialogResult == 0) //If the user selects "Yes" in the dialog box
					{
						playerModel.removeRow(index); 
						playerList.remove(index);
						PriorityQueue<Player> temp = new PriorityQueue<Player>();
						for(int i = 0; i < playerList.size(); i++)
						{
							temp.add(playerList.get(i));
						}
						pDatabase.save(temp);
					}
					updateTeamStats(); //Update the team statistics
				}
				else
					showMessageDialog(null, "Ensure a player is selected to remove", "Cannot Remove Game",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		//rosterClearButton
		rosterClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (playerList.size()!=0) //If there are games to be cleared
				{
					int dialogResult = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to clear the \nroster? (All saved data will be removed)",
							"Reset Confirmation", JOptionPane.ERROR_MESSAGE);
					if (dialogResult == 0) //If the user selects "Yes" in the dialog box
					{
						while (playerList.size() != 0) 
						{
							playerModel.removeRow(0);
							playerList.remove(0);
						}
						pDatabase.clearDatabase();
						updateTeamStats();
					}
				}
				else //There are no games in the schedule
					showMessageDialog(null, "Schedule is already empty", "Cannot Clear Schedule", JOptionPane.WARNING_MESSAGE);
			}
		});

	}

	private void initializeMainWindow()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 488);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		//Initialize the main panel
		mainWindowTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainWindowTabbedPane.setBounds(0, 32, 764, 428);
		mainPanel.add(mainWindowTabbedPane);
		//Initialize the TabbedPane and add it to the main panel
		JLabel mainWindowLabel = new JLabel("Baseball Manager");
		mainWindowLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		mainWindowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainWindowLabel.setBounds(0, 0, 764, 34);
		mainPanel.add(mainWindowLabel);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo.png")));
		initializeSchedule();
		initializeRoster();
	}

	private String [][] getGameMatrix() //Returns games in the list as a matrix
	{
		String [][] gameMatrix = new String [gameList.size()][5];
		for (int i=0; i<gameList.size(); i++)
		{
		Game newGame = gameList.get(i);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String formatDateTime = newGame.getDate().format(formatter);
		if (newGame.getTeamScore() == -1 || newGame.getOppScore() == -1)
			gameMatrix[i] = new String [] {newGame.getOpponent(),formatDateTime,newGame.getLocation(),newGame.getJerseyColor(),
					"N/A"};
		else
			gameMatrix[i] = new String [] {newGame.getOpponent(),formatDateTime,newGame.getLocation(),newGame.getJerseyColor(),
					newGame.getTeamScore() + " to "+ newGame.getOppScore()};
		}
		return gameMatrix;
	}

	private String [][] getPlayerMatrix() //Returns players in the list as a matrix
	{
		String [][] playerMatrix = new String [playerList.size()][5];
		for (int i=0; i<playerList.size(); i++)
		{
		Player newPlayer = playerList.get(i);
		playerMatrix[i] = new String [] {newPlayer.getName(), newPlayer.getAVG(), newPlayer.getOBP(), newPlayer.getSLG(), newPlayer.getOPS()};
		}
		return playerMatrix;
	}
}

