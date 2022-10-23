public class Player implements Comparable 
{
	private String myName;
	private int myPA;
	private int myWalks;
	private int mySingles;
	private int myDoubles;
	private int myTriples;
	private int myHR;

	public Player(String name,  int pa,  int walks,  int singles,  int doubles,  int triples,  int hr)
	{
		myName = name;
		myPA = pa;
		myWalks = walks;
		mySingles = singles ;
		myDoubles = doubles;
		myTriples = triples;
		myHR = hr;
	}
	public void setName(String name)
	{
		myName = name;
	}

	public void setPlateAppearances(int pa)
	{
		myPA = pa;
	}
	public void setWalks(int walks)
	{
		myWalks = walks;
	}
	public void setSingles(int singles)
	{
		mySingles = singles ;
	}
	public void setDoubles(int doubles)
	{
		myDoubles = doubles;
	}
	public void setTriples(int triples)
	{
		myTriples = triples;
	}
	public void setHR(int hr)
	{
		myHR = hr;
	}
	public String getName()
	{
		return myName;
	}

	public int getPlateAppearances()
	{
		return myPA;
	}
	public int getWalks()
	{
		return myWalks;
	}
	public int getSingles()
	{
		return mySingles;
	}
	public int getDoubles()
	{
		return myDoubles;
	}
	public int getTriples()
	{
		return myTriples;
	}
	public int getHR()
	{
		return myHR;
	}
	public String getAVG() //Calculates the batting average
	{
		if (myPA>0 && (myWalks!=myPA))
		{
			double preFormat = (mySingles + myDoubles + myTriples + myHR) / (double)(myPA - myWalks);
			return String.format("%4.3f", preFormat);
		}
		else
			return "0.000";
	}
	public String getOBP()  //Calculates the on-base percentage
	{
		if (myPA>0)
		{
			double preFormat =  ((mySingles + myDoubles + myTriples + myHR + myWalks) / (double)myPA);
			return 	String.format("%4.3f", preFormat);
		}
		else
			return "0.000";

	}
	public String getSLG() //Calculates the slugging percentage
	{
		if (myPA>0 && (myWalks!=myPA)){
			double preFormat =  (mySingles + (2*myDoubles) + (3*myTriples) + (4*myHR) ) / (double)(myPA - myWalks);
			return String.format("%.3f", preFormat);
		}
		else
			return "0.000";
	}
	public String getOPS() //Calculates the on-base plus slugging
	{
		if (myPA>0)
		{
			double preFormat =  Double.parseDouble(getOBP()) +  Double.parseDouble(getSLG());
			return String.format("%4.3f", preFormat);	
		}
		else
			return "0.000";
	}
	public int compareTo (Object otherPlayer)
	{
		Player other = (Player) otherPlayer;
		return myName.compareTo(other.getName());
	}
	public boolean equalTo(Player otherPlayer)
	{
		return (myName.equals(otherPlayer.getName())) && mySingles == otherPlayer.getSingles() && myDoubles == otherPlayer.getDoubles() && myTriples == otherPlayer.getTriples() && myHR == otherPlayer.getHR() ;
	}
}
