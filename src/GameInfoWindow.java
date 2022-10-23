import java.awt.Color;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.time.DateTimeException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class GameInfoWindow extends JFrame {

	private JPanel gameInfoWindowParentPane;
	private JTextField gameInfoOppTxtField;
	private JTextField gameInfoColorTxtField;
	private Game myGame;

	public GameInfoWindow(Game gme) {
		initializeGUI(gme);
	}

	private void initializeGUI(Game gme)
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 400);
//Initializing Elements of the window and setting their properties
		//gameInfoWindowParentPane
		gameInfoWindowParentPane = new JPanel();
		gameInfoWindowParentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(gameInfoWindowParentPane);
		gameInfoWindowParentPane.setLayout(null);

		//gameInfoWindowPane
		JPanel gameInfoWindowPane = new JPanel();
		gameInfoWindowPane.setBounds(6, 34, 408, 293);
		gameInfoWindowParentPane.add(gameInfoWindowPane);
		gameInfoWindowPane.setLayout(null);

		//gameInfoOppLabel
		JLabel gameInfoOppLabel = new JLabel("Opponent:");
		gameInfoOppLabel.setBounds(6, 5, 83, 16);
		gameInfoOppLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		gameInfoWindowPane.add(gameInfoOppLabel);

		//gameInfoDateLabel
		JLabel gameInfoDateLabel = new JLabel("Date:");
		gameInfoDateLabel.setBounds(5, 43, 40, 16);
		gameInfoDateLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		gameInfoWindowPane.add(gameInfoDateLabel);

		//gameInfoTimeLabel
		JLabel gameInfoTimeLabel = new JLabel("Time:");
		gameInfoTimeLabel.setBounds(6, 81, 61, 16);
		gameInfoTimeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		gameInfoWindowPane.add(gameInfoTimeLabel);

		//gameInfoColorLabel
		JLabel gameInfoColorLabel = new JLabel("Jersey Color:");
		gameInfoColorLabel.setBounds(6, 119, 93, 16);
		gameInfoColorLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		gameInfoWindowPane.add(gameInfoColorLabel);

		//gameInfoTscoreLabel
		JLabel gameInfoTscoreLabel = new JLabel("Team Score:");
		gameInfoTscoreLabel.setBounds(6, 195, 93, 16);
		gameInfoTscoreLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		gameInfoWindowPane.add(gameInfoTscoreLabel);

		//gameInfoOScoreLabel
		JLabel gameInfoOScoreLabel = new JLabel("Opponent Score:");
		gameInfoOScoreLabel.setBounds(6, 233, 124, 16);
		gameInfoOScoreLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		gameInfoWindowPane.add(gameInfoOScoreLabel);

		//gameInfoOppTxtField
		gameInfoOppTxtField = new JTextField();
		gameInfoOppTxtField.setBounds(94, 1, 311, 26);
		gameInfoWindowPane.add(gameInfoOppTxtField);
		gameInfoOppTxtField.setColumns(10);

		//gameInfoColorTxtField
		gameInfoColorTxtField = new JTextField();
		gameInfoColorTxtField.setBounds(94, 111, 311, 26);
		gameInfoColorTxtField.setColumns(10);
		gameInfoWindowPane.add(gameInfoColorTxtField);

		//gameInfoDayLabel
		JLabel gameInfoDayLabel = new JLabel("Day:");
		gameInfoDayLabel.setBounds(96, 43, 34, 16);
		gameInfoDayLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		gameInfoWindowPane.add(gameInfoDayLabel);

		//gameInfoDaySpinner
		JSpinner gameInfoDaySpinner = new JSpinner();
		gameInfoDaySpinner.setBounds(132, 40, 53, 26);
		gameInfoWindowPane.add(gameInfoDaySpinner);
		gameInfoDaySpinner.setValue(java.time.LocalDate.now().getDayOfMonth());

		//gameInfoMonthSpinner
		JSpinner gameInfoMonthSpinner = new JSpinner();
		gameInfoMonthSpinner.setBounds(245, 40, 53, 26);
		gameInfoWindowPane.add(gameInfoMonthSpinner);
		gameInfoMonthSpinner.setValue(java.time.LocalDateTime.now().getMonthValue());

		//gameInfoMonthLabel
		JLabel gameInfoMonthLabel = new JLabel("Month:");
		gameInfoMonthLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		gameInfoMonthLabel.setBounds(198, 43, 53, 16);
		gameInfoWindowPane.add(gameInfoMonthLabel);

		//gameInfoYearSpinner
		JSpinner gameInfoYearSpinner = new JSpinner();
		gameInfoYearSpinner.setValue(java.time.LocalDateTime.now().getYear());
		gameInfoYearSpinner.setBounds(335, 40, 71, 26);
		gameInfoYearSpinner.setEditor(new JSpinner.NumberEditor(gameInfoYearSpinner, "#"));
		gameInfoWindowPane.add(gameInfoYearSpinner);

		//gameInfoYearLabel
		JLabel gameInfoYearLabel = new JLabel("Year:");
		gameInfoYearLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		gameInfoYearLabel.setBounds(303, 43, 87, 16);
		gameInfoWindowPane.add(gameInfoYearLabel);

		//gameInfoHourLabel
		JLabel gameInfoHourLabel = new JLabel("Hour:");
		gameInfoHourLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		gameInfoHourLabel.setBounds(96, 81, 61, 16);
		gameInfoWindowPane.add(gameInfoHourLabel);

		//gameInfoHourSpinner
		JSpinner gameInfoHourSpinner = new JSpinner();
		gameInfoHourSpinner.setBounds(132, 75, 53, 26);
		gameInfoWindowPane.add(gameInfoHourSpinner);
		gameInfoHourSpinner.setValue(java.time.LocalDateTime.now().getHour()); //Set the hour spinner's value to the current hour

		//gameInfoMinuteSpinner
		JSpinner gameInfoMinuteSpinner = new JSpinner();
		gameInfoMinuteSpinner.setBounds(245, 77, 53, 26);
		gameInfoWindowPane.add(gameInfoMinuteSpinner);
		gameInfoMinuteSpinner.setValue(java.time.LocalDateTime.now().getMinute()); //Set the minute spinner's value to the current minute

		//gameInfoMinuteLabel
		JLabel gameInfoMinuteLabel = new JLabel("Minute:");
		gameInfoMinuteLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		gameInfoMinuteLabel.setBounds(198, 81, 61, 16);
		gameInfoWindowPane.add(gameInfoMinuteLabel);

		//gameInfoTScoreSpinner
		JSpinner gameInfoTscoreSpinner = new JSpinner();
		gameInfoTscoreSpinner.setBounds(132, 191, 53, 26);
		gameInfoWindowPane.add(gameInfoTscoreSpinner);

		//gameInfoOScoreSpinner
		JSpinner gameInfoOscoreSpinner = new JSpinner();
		gameInfoOscoreSpinner.setBounds(132, 229, 53, 26);
		gameInfoWindowPane.add(gameInfoOscoreSpinner);

		//gameInfoLocationLabel
		JLabel gameInfoLocationLabel = new JLabel("Location:");
		gameInfoLocationLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		gameInfoLocationLabel.setBounds(6, 157, 89, 14);
		gameInfoWindowPane.add(gameInfoLocationLabel);

		//gameInfoHomeButton
		JRadioButton gameInfoHomeButton = new JRadioButton("Home");
		gameInfoHomeButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		gameInfoHomeButton.setBounds(94, 154, 70, 23);
		gameInfoWindowPane.add(gameInfoHomeButton);

		//gameInfoAwayButton
		JRadioButton gameInfoAwayButton = new JRadioButton("Away");
		gameInfoAwayButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		gameInfoAwayButton.setBounds(160, 154, 109, 23);
		gameInfoWindowPane.add(gameInfoAwayButton);

		//bGroup
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(gameInfoAwayButton);
		bGroup.add(gameInfoHomeButton);
		//Ensure that when the home button is selected, the away button is deselected, and when the away button is selected, the home button is deselected

		//gameInfoCompleteBox
		JCheckBox gameInfoCompleteBox = new JCheckBox("Completed?");
		gameInfoCompleteBox.setBounds(0, 264, 175, 23);
		gameInfoWindowPane.add(gameInfoCompleteBox);

		//gameInfoWindowLabel
		JLabel gameInfoWindowLabel = new JLabel("Game Information");
		gameInfoWindowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameInfoWindowLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		gameInfoWindowLabel.setBounds(6, 11, 408, 22);
		gameInfoWindowParentPane.add(gameInfoWindowLabel);

		//gameInfoSaveButton
		JButton gameInfoSaveButton = new JButton("Save & Exit");
		gameInfoSaveButton.setBounds(6, 330, 110, 25);
		gameInfoWindowParentPane.add(gameInfoSaveButton);

		//gameInfoCancelButton
		JButton gameInfoCancelButton = new JButton("Cancel");
		gameInfoCancelButton.setBackground(Color.RED);
		gameInfoCancelButton.setBounds(122, 330, 110, 25);
		gameInfoWindowParentPane.add(gameInfoCancelButton);

		//If the game is being edited, set all of the values the fields to the values of the game that is passed in
		if (gme != null) //A game is being edited
		{
			gameInfoOppTxtField.setText(gme.getOpponent());
			gameInfoDaySpinner.setValue(gme.getDate().getDayOfMonth());
			gameInfoMonthSpinner.setValue(gme.getDate().getMonthValue());
			gameInfoYearSpinner.setValue(gme.getDate().getYear());
			gameInfoMinuteSpinner.setValue(gme.getDate().getMinute());
			gameInfoHourSpinner.setValue(gme.getDate().getHour());
			gameInfoColorTxtField.setText(gme.getJerseyColor());
			gameInfoHomeButton.setSelected(gme.getLocation().equals("Home"));
			gameInfoAwayButton.setSelected(!gme.getLocation().equals("Home"));
			if (gme.getTeamScore() == -1 || gme.getOppScore()==-1) //If the game is not completed
				gameInfoCompleteBox.setSelected(false);
			else//If the game is completed
			{
				gameInfoTscoreSpinner.setValue(gme.getTeamScore());
				gameInfoOscoreSpinner.setValue(gme.getOppScore());
				gameInfoCompleteBox.setSelected(true);
			}
		}
//Action Listeners
		//gameInfoDaySpinner
		gameInfoDaySpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				if((int) gameInfoDaySpinner.getValue()<1)
					gameInfoDaySpinner.setValue(1);
				if((int) gameInfoDaySpinner.getValue()>31)
					gameInfoDaySpinner.setValue(31);
			}
		});

		//gameInfoMonthSpinner
		gameInfoMonthSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				if((int) gameInfoMonthSpinner.getValue()<1)
					gameInfoMonthSpinner.setValue(1);
				if((int) gameInfoMonthSpinner.getValue()>12)
					gameInfoMonthSpinner.setValue(12);
			}
		}); //Ensures that the value in the spinner is not lower than 1 and not greater than 12

		//gameInfoYearSpinner
		gameInfoYearSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				if((int) gameInfoYearSpinner.getValue()<1000)
					gameInfoYearSpinner.setValue(1000);
				else if ((int) gameInfoYearSpinner.getValue()>9999)
					gameInfoYearSpinner.setValue(9999);
			}
		});  //Ensures that the value in the spinner is not lower than 1000 and not greater than 1999

		//gameInfoHourSpinner
		gameInfoHourSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				if((int) gameInfoHourSpinner.getValue()<0)
					gameInfoHourSpinner.setValue(0);
				if((int) gameInfoHourSpinner.getValue()>23)
					gameInfoHourSpinner.setValue(23);
			}
		}); //Ensures that the value in the spinner is not lower than 0 and not greater than 24

		//gameInfoMinuteSpinner
		gameInfoMinuteSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				if((int) gameInfoMinuteSpinner.getValue()<0)
					gameInfoMinuteSpinner.setValue(0);
				if((int) gameInfoMinuteSpinner.getValue()>59)
					gameInfoMinuteSpinner.setValue(59);
			}
		}); //Ensures that the value in the spinner is not lower than 0 and not greater than 59

		//gameInfoTScoreSpinner
		gameInfoTscoreSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				if((int) gameInfoTscoreSpinner.getValue()<0)
					gameInfoTscoreSpinner.setValue(0);
			}
		}); //Ensures that the value in the spinner is not negative

		//gameInfoOScoreSpinner
		gameInfoOscoreSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				if((int) gameInfoOscoreSpinner.getValue()<0)
					gameInfoOscoreSpinner.setValue(0);
			}
		});//Ensures that the value in the spinner is not negative

		//gameInfoSaveButton
		gameInfoSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (gameInfoOppTxtField.getText().isBlank() || gameInfoColorTxtField.getText().isBlank() || (!gameInfoHomeButton.isSelected() && !gameInfoAwayButton.isSelected())) 
					//If there is no text in the gameInfoOppTxtField or gameInfoColorTxtField, or if gameInfoHomeButton and gameInfoAwayButton are both not selected
				{
					showMessageDialog(null, "Ensure all fields are complete", "Invalid ", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					int tScore;
					int oppScore;
					try
					{

						if(gameInfoCompleteBox.isSelected()) //If the game is completed
						{
							tScore = (int)gameInfoTscoreSpinner.getValue();
							oppScore = (int)gameInfoOscoreSpinner.getValue();

							myGame = new Game(gameInfoOppTxtField.getText(), (int)gameInfoYearSpinner.getValue(), 
									(int)gameInfoMonthSpinner.getValue(), (int)gameInfoDaySpinner.getValue(), (int)gameInfoMinuteSpinner.getValue(),
									(int)gameInfoHourSpinner.getValue(), gameInfoColorTxtField.getText(), tScore,oppScore,gameInfoHomeButton.isSelected()); //Create a new game from the info in the window
						}
						else //If the game is not completed
						{
							tScore =-1;
							oppScore=-1;
							myGame = new Game(gameInfoOppTxtField.getText(), (int)gameInfoYearSpinner.getValue(), 
									(int)gameInfoMonthSpinner.getValue(), (int)gameInfoDaySpinner.getValue(), (int)gameInfoMinuteSpinner.getValue(),
									(int)gameInfoHourSpinner.getValue(), gameInfoColorTxtField.getText(),gameInfoHomeButton.isSelected()); 
							//Create a new game from the info in the window, use -1 for the team score and opponent score to show the game has not been completed
						}
						GameDatabase dataBase = new GameDatabase();
						if (gme==null) //If there is no game passed in (A game is to be added)
							dataBase.addToDatabase(myGame);
						else //If there is a game passed in (A game is to be edited)
							dataBase.edit(gme, gameInfoOppTxtField.getText(), (int)gameInfoYearSpinner.getValue(), 
									(int)gameInfoMonthSpinner.getValue(), (int)gameInfoDaySpinner.getValue(), (int)gameInfoMinuteSpinner.getValue(),
									(int)gameInfoHourSpinner.getValue(), gameInfoColorTxtField.getText(), tScore, oppScore,gameInfoHomeButton.isSelected());					
						MainWindow newWindow = new MainWindow();
						newWindow.setVisible(true);
						dispose();
					}
					catch (DateTimeException excep) //The date entered in is invalid
					{
						showMessageDialog(null, "Ensure a valid date is entered", "Invalid Date", JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		});
		//gameInfoCancelButton
		gameInfoCancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				MainWindow newWindow = new MainWindow();
				newWindow.setVisible(true);
				dispose();
			}
		});
	}
}
