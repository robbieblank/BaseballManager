import java.io.FileNotFoundException;
import java.io.PrintWriter;
public abstract class Database {
	private String file;
	public Database(String fileName)
	{
		file = fileName;
		loadFile();
	}
	protected abstract void loadFile();
	public void clearDatabase() //Clears the file
	{
		PrintWriter writer;
		try 
		{
			writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
}
