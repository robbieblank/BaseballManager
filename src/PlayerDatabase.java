import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PlayerDatabase extends Database
{
	private PriorityQueue<Player> playerQueue;

	public PlayerDatabase()
	{
		super("baseballRoster.txt");
	}

	protected void loadFile() //Loads the from the file to the class
	{
		Scanner scan; 
		PriorityQueue<Player> temp = new PriorityQueue<Player>(); 
		try
		{
			scan = new Scanner(new File("baseballRoster.txt"));
			while (scan.hasNext()) 
			{
				int pa = scan.nextInt();
				int walks = scan.nextInt();
				int singles = scan.nextInt();
				int doubles = scan.nextInt();
				int triples = scan.nextInt();	
				int hr = scan.nextInt();
				scan.nextLine();
				String name = scan.nextLine();
				temp.add(new Player(name, pa, walks, singles, doubles, triples, hr));
			}
		}
		catch (IOException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		playerQueue=temp;
	}


	public void save(PriorityQueue<Player> save) //Saves the PriorityQueue that is passed in to the file
	{
		try 
		{
			PrintWriter printer = new PrintWriter(new File("baseballRoster.txt"));
			while (!save.isEmpty()) 
			{
				Player temp = (Player)save.remove();
				printer.print(temp.getPlateAppearances() + " " + temp.getWalks() + " " + temp.getSingles() + " " + temp.getDoubles()
				+ " " + temp.getTriples()+ " "+ temp.getHR() + "\n" + temp.getName() +"\n");
			}
			printer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void addToDatabase(Player plyr) //Adds the player that is passed in to the database and saves it to file 
	{
		playerQueue.add(plyr);
		save(playerQueue);
	}
	public void edit(Player plyr, String name,  int pa,  int walks,  int singles,  int doubles,  int triples,  int hr)  // Finds the player in the PriorityQueue, and then edits values to those passed in 
	{
		ArrayList<Player> temp = new ArrayList<Player>();
		Player check = (Player) playerQueue.remove(); //Get the player at the front of the list
		while(!check.equalTo(plyr))//If the current player is not the game you are looking for
		{
			temp.add(check); //Add the player to the list of games that have been removed
			check = playerQueue.remove(); //Get the next game
		}
		check.setName(name); 
		check.setPlateAppearances(pa);
		check.setSingles(singles);
		check.setDoubles(doubles);
		check.setTriples(triples);
		check.setHR(hr);
		while(!temp.isEmpty()) //Add the items that have been removed back to the list
			playerQueue.add(temp.remove(0));
		playerQueue.add(check);
		save(playerQueue);
	}
	public PriorityQueue<Player> getData() //Returns the PriorityQueue containing the games
	{
		return playerQueue;
	}


}





