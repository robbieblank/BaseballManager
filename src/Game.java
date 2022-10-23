import java.time.*;
public class Game implements Comparable<Game>
{
	private String myOpponent;
	private LocalDateTime myDate;
	private String myJerseyColor;
	private int myTeamScore;
	private int myOppScore;
	private boolean myIsHome;
	public Game(String opponent, int year, int month, int day, int minute, int hour, String jerseyColor, boolean isHome)
	{
		myOpponent = opponent;
		myDate= LocalDateTime.of(year, month, day, hour, minute);
		myJerseyColor = jerseyColor;
		myTeamScore = -1;
		myOppScore = -1;
		myIsHome = isHome;
	}
	public Game(String opponent, int year, int month, int day, int minute, int hour, String jerseyColor, int teamScore, int oppScore, boolean isHome)
	{
		myOpponent = opponent;
		myDate= LocalDateTime.of(year, month, day, hour, minute);
		myJerseyColor = jerseyColor;
		myTeamScore = teamScore;
		myOppScore = oppScore;
		myIsHome = isHome;
	}
	public void setOpponent(String opponent)
	{
		myOpponent = opponent;
	}

	public void setDate(int year, int month, int day, int minute, int hour)
	{
		myDate= LocalDateTime.of(year, month, day, hour, minute);
	}

	public void setJerseyColor(String jerseyColor)
	{
		myJerseyColor = jerseyColor;
	}

	public void setTeamScore(int tScore)
	{
		myTeamScore = tScore;
	}

	public void setOppScore(int oppScore)
	{

		myOppScore = oppScore;
	}

	public void setLocation(boolean isHome)
	{
		myIsHome = isHome;
	}

	public String getLocation()
	{
		if (myIsHome)
		{
			return "Home";
		}
		else
		{
			return "Away";
		}
	}

	public boolean getBoolLocation()
	{
		return myIsHome;
	}

	public String getOpponent()
	{
		return myOpponent;
	}

	public LocalDateTime getDate()
	{
		return myDate;
	}

	public String getJerseyColor()
	{
		return myJerseyColor;
	}

	public int getTeamScore()
	{
		return myTeamScore;
	}

	public int getOppScore()
	{

		return myOppScore;
	}

	public int compareTo (Game otherGame)
	{
		return (otherGame.getDate()).compareTo(myDate);
	}
	public boolean equalTo(Game otherGame)
	{
		return myOpponent.equals(otherGame.getOpponent()) && myDate.equals(otherGame.getDate()) && myJerseyColor.equals(otherGame.getJerseyColor()) && myTeamScore == otherGame.getTeamScore() && myOppScore == otherGame.getOppScore() && myIsHome == otherGame.getBoolLocation();
	}
}
