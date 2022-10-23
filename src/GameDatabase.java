import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class GameDatabase extends Database
{
	private PriorityQueue<Game> gameQueue;

	public GameDatabase()
	{
		super("baseballSchedule.txt");
	}

	protected void loadFile() //Loads the from the file to the class
	{
		Scanner scan; 
		PriorityQueue<Game> temp = new PriorityQueue<Game>();
		try
		{
			scan = new Scanner(new File("baseballSchedule.txt"));
			while (scan.hasNext()) 
			{
				int year = scan.nextInt();
				int month = scan.nextInt();
				int day = scan.nextInt();
				int minute = scan.nextInt();
				int hour = scan.nextInt();
				int tScore = scan.nextInt();
				int oScore = scan.nextInt();
				boolean isHome;
				if (scan.nextInt() == 1)
					isHome = true;
				else
					isHome = false;
				scan.nextLine();
				String jerseyColor = scan.nextLine();
				String opp = scan.nextLine();
				if (tScore == -1 || oScore == -1) //If this game is not completed
				temp.add(new Game(opp, year, month, day, minute, hour, jerseyColor, isHome));
				else //If this game is completed
				temp.add(new Game(opp, year, month, day, minute, hour, jerseyColor, tScore, oScore,isHome));
			}
		}
		catch (IOException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		gameQueue = temp;
	}

	public PriorityQueue<Game> getData() //Returns the PriorityQueue containing the games
	{
		return gameQueue;
	}

	public void save(PriorityQueue<Game> save) //Saves the PriorityQueue passed in to file
	{
		try 
		{
			PrintWriter printer = new PrintWriter(new File("baseballSchedule.txt"));
			while (!save.isEmpty()) 
			{
				Game temp = (Game) save.remove();
				printer.print(temp.getDate().getYear() + " " + temp.getDate().getMonthValue() + " " + temp.getDate().getDayOfMonth() + " " + temp.getDate().getMinute() 
						+ " " + temp.getDate().getHour() + " "+ temp.getTeamScore() + " " +temp.getOppScore() + " ");
				if(temp.getLocation().equals("Home"))
					printer.print("1" + "\n"); //Home
				else
					printer.print("2" + "\n"); //Away
				printer.println(temp.getJerseyColor() + "\n" + temp.getOpponent());
			}
			printer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}


	public void addToDatabase(Game game) //Adds the game passed in to the database and saves it to file
	{
		gameQueue.add(game);
		save(gameQueue);
	}


	public void edit(Game gme, String opponent, int year, int month, int day, int minute, int hour, String jerseyColor, int teamScore, int oppScore, boolean isHome) // Finds the game in the PriorityQueue, and then edits values to those passed in 
	{
		ArrayList<Game> temp = new ArrayList<Game>();
		Game check = (Game) gameQueue.remove(); //Get the game at the front of the list
		while(!check.equalTo(gme)) //If the current game is not the game you are looking for
		{
			temp.add(check); //Add the game to the list of games that have been removed
			check = (Game) gameQueue.remove(); //Get the next game
		}
		check.setDate(year, month, day, minute, hour);
		check.setLocation(isHome);
		check.setOpponent(opponent);
		check.setTeamScore(teamScore);
		check.setOppScore(oppScore);
		check.setJerseyColor(jerseyColor);
		while(!temp.isEmpty()) //Add the items that have been removed back to the list
			gameQueue.add(temp.remove(0));
		gameQueue.add(check);
		save(gameQueue);
	}
}
