import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerInfoWindow extends JFrame 
{

	private JPanel playerInfoWindowParentPane;
	private JTextField playerInfoNameTxtField;
	public PlayerInfoWindow(Player play) 
	{
		initializeGUI(play);
	}

	private void initializeGUI(Player ply)
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 285);

//Initializing Elements of the window and setting their properties
		//playerInfoWindowParentPane
		playerInfoWindowParentPane = new JPanel();
		playerInfoWindowParentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(playerInfoWindowParentPane);
		playerInfoWindowParentPane.setLayout(null);

		//playerInfoWindowPane
		JPanel playerInfoWindowPane = new JPanel();
		playerInfoWindowPane.setBounds(6, 34, 438, 179);
		playerInfoWindowParentPane.add(playerInfoWindowPane);
		playerInfoWindowPane.setLayout(null);

		//playerInfoNameLabel
		JLabel playerInfoNameLabel = new JLabel("Name:");
		playerInfoNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		playerInfoNameLabel.setBounds(6, 5, 83, 16);
		playerInfoWindowPane.add(playerInfoNameLabel);

		//playerInfoPALabel
		JLabel playerInfoPALabel = new JLabel("Plate Appearances:");
		playerInfoPALabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		playerInfoPALabel.setBounds(6, 30, 137, 16);
		playerInfoWindowPane.add(playerInfoPALabel);

		//playerInfoWalksLabel
		JLabel playerInfoWalksLabel = new JLabel("Walks:");
		playerInfoWalksLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		playerInfoWalksLabel.setBounds(6, 55, 61, 16);
		playerInfoWindowPane.add(playerInfoWalksLabel);

		//playerInfoSingleLabel
		JLabel playerInfoSingleLabel = new JLabel("Singles:");
		playerInfoSingleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		playerInfoSingleLabel.setBounds(6, 80, 73, 16);
		playerInfoWindowPane.add(playerInfoSingleLabel);

		//playerInfoDoubleLabel
		JLabel playerInfoDoubleLabel = new JLabel("Doubles:");
		playerInfoDoubleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		playerInfoDoubleLabel.setBounds(6, 105, 61, 16);
		playerInfoWindowPane.add(playerInfoDoubleLabel);

		//playerInfoTripleLabel
		JLabel playerInfoTripleLabel = new JLabel("Triples:");
		playerInfoTripleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		playerInfoTripleLabel.setBounds(6, 130, 61, 16);
		playerInfoWindowPane.add(playerInfoTripleLabel);

		//playerInfoHRLabel
		JLabel playerInfoHRLabel = new JLabel("Home Runs:");
		playerInfoHRLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		playerInfoHRLabel.setBounds(6, 155, 83, 16);
		playerInfoWindowPane.add(playerInfoHRLabel);

		//playerInfoNameTxtField
		playerInfoNameTxtField = new JTextField();
		playerInfoNameTxtField.setBounds(56, 1, 376, 26);
		playerInfoWindowPane.add(playerInfoNameTxtField);
		playerInfoNameTxtField.setColumns(10);

		//playerInfoPASpinner
		JSpinner playerInfoPASpinner = new JSpinner();
		playerInfoPASpinner.setBounds(136, 29, 296, 20);
		playerInfoWindowPane.add(playerInfoPASpinner);

		//playerInfoWalkSpinner
		JSpinner playerInfoWalkSpinner = new JSpinner();
		playerInfoWalkSpinner.setBounds(136, 54, 296, 20);
		playerInfoWindowPane.add(playerInfoWalkSpinner);

		//playerInfoSinglesSpinner
		JSpinner playerInfoSinglesSpinner = new JSpinner();
		playerInfoSinglesSpinner.setBounds(136, 79, 296, 20);
		playerInfoWindowPane.add(playerInfoSinglesSpinner);

		//playerInfoDoubleSpinner
		JSpinner playerInfoDoubleSpinner = new JSpinner();
		playerInfoDoubleSpinner.setBounds(136, 104, 296, 20);
		playerInfoWindowPane.add(playerInfoDoubleSpinner);

		//playerInfoTripleSpinner
		JSpinner playerInfoTripleSpinner = new JSpinner();
		playerInfoTripleSpinner.setBounds(136, 129, 296, 20);
		playerInfoWindowPane.add(playerInfoTripleSpinner);

		//playerInfoHRSpinner
		JSpinner playerInfoHRSpinner = new JSpinner();
		playerInfoHRSpinner.setBounds(136, 154, 296, 20);
		playerInfoWindowPane.add(playerInfoHRSpinner);

		//playerInfoWindowLabel
		JLabel playerInfoWindowLabel = new JLabel("Player Information");
		playerInfoWindowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerInfoWindowLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		playerInfoWindowLabel.setBounds(6, 6, 438, 22);
		playerInfoWindowParentPane.add(playerInfoWindowLabel);

		//playerInfoSaveButton
		JButton playerInfoSaveButton = new JButton("Save & Exit");
		playerInfoSaveButton.setBounds(6, 217, 110, 25);
		playerInfoWindowParentPane.add(playerInfoSaveButton);

		//playerInfoCancel
		JButton playerInfoCancel = new JButton("Cancel");
		playerInfoCancel.setBackground(Color.RED);
		playerInfoCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				MainWindow frame = new MainWindow();
				frame.setVisible(true);
				dispose();
			}
		});
		playerInfoCancel.setBounds(119, 217, 110, 25);
		playerInfoWindowParentPane.add(playerInfoCancel);

		//If the player is being edited, set all of the values the fields to the values of the players that is passed in
		if (ply != null)
		{
			playerInfoNameTxtField.setText(ply.getName());
			playerInfoPASpinner.setValue(ply.getPlateAppearances());
			playerInfoWalkSpinner.setValue(ply.getWalks());
			playerInfoSinglesSpinner.setValue(ply.getSingles());
			playerInfoDoubleSpinner.setValue(ply.getDoubles());
			playerInfoTripleSpinner.setValue(ply.getTriples());
			playerInfoHRSpinner.setValue(ply.getHR());
		}

//Action Listeners
		//playerInfoPASpinner
		playerInfoPASpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				if((int) playerInfoPASpinner.getValue()<0)
					playerInfoPASpinner.setValue(0);
			}
		}); //Ensures the value in the spinner is not negative

		//playerInfoWalkSpinner
		playerInfoWalkSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e)
			{
				if((int) playerInfoWalkSpinner.getValue()<0)
					playerInfoWalkSpinner.setValue(0);
			}
		}); //Ensures the value in the spinner is not negative

		//playerInfoSinglesSpinner
		playerInfoSinglesSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				if((int) playerInfoSinglesSpinner.getValue()<0)
					playerInfoSinglesSpinner.setValue(0);
			}
		}); //Ensures the value in the spinner is not negative

		//playerInfoDoubleSpinner
		playerInfoDoubleSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				if((int) playerInfoDoubleSpinner.getValue()<0)
					playerInfoDoubleSpinner.setValue(0);
			}
		}); //Ensures the value in the spinner is not negative

		//playerInfoTripleSpinner
		playerInfoTripleSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e)
			{
				if((int) playerInfoTripleSpinner.getValue()<0)
					playerInfoTripleSpinner.setValue(0);
			}
		}); //Ensures the value in the spinner is not negative

		//playerInfoHRSpinner
		playerInfoHRSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				if((int) playerInfoHRSpinner.getValue()<0)
					playerInfoHRSpinner.setValue(0);
			}
		}); //Ensures the value in the spinner is not negative

		//playerInfoSaveButton
		playerInfoSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(playerInfoNameTxtField.getText().isBlank()) //If the user did not type anything in the playerInfoNameTxtField
					showMessageDialog(null, "Ensure all fields are complete", "Invalid ", JOptionPane.WARNING_MESSAGE);
				else if((int)playerInfoWalkSpinner.getValue()+ (int)playerInfoSinglesSpinner.getValue()+
						(int)playerInfoDoubleSpinner.getValue()+ (int)playerInfoTripleSpinner.getValue()+ (int)playerInfoHRSpinner.getValue() > (int)playerInfoPASpinner.getValue()) 
					// If the number of walks, singles, doubles, triples, and home runs exceeds plate appearances
					showMessageDialog(null, "Number of Walks, Singles, Doubles, Triples, and Home Runs Exceeds Plate Appearances", "Invalid ", JOptionPane.WARNING_MESSAGE);
				else //If the user typed anything in the playerInfoNameTxtField, meaning all necessary fields have been completed
				{
					PlayerDatabase dBase = new PlayerDatabase();
					if(ply == null) //If a player has not been passed into the window, meaning a new player is to be added
						dBase.addToDatabase(new Player(playerInfoNameTxtField.getText(),  (int)playerInfoPASpinner.getValue(),  (int)playerInfoWalkSpinner.getValue(),  (int)playerInfoSinglesSpinner.getValue(),  
								(int)playerInfoDoubleSpinner.getValue(), (int)playerInfoTripleSpinner.getValue(),  (int)playerInfoHRSpinner.getValue()));
					else //If a player has been passed into the window, meaning a player is being edited
						dBase.edit(ply, playerInfoNameTxtField.getText(),  (int)playerInfoPASpinner.getValue(),  (int)playerInfoWalkSpinner.getValue(),  (int)playerInfoSinglesSpinner.getValue(),  
								(int)playerInfoDoubleSpinner.getValue(), (int)playerInfoTripleSpinner.getValue(),  (int)playerInfoHRSpinner.getValue());
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					frame.updateToRosterTab();
					dispose();
				}
			}
		});
	}
}
