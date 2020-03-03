package srp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class ContactRawDataReader
{
	private final String pathToResource;
	
	public ContactRawDataReader(String pathToResource)
	{
		this.pathToResource = pathToResource;
	}
	
	public List<String> read() throws ReaderException
	{
		List<String> list = new LinkedList<>();
		
		try(BufferedReader reader = new BufferedReader(
				new InputStreamReader(Controller.class.getResourceAsStream(pathToResource))))
		{
			String line;
			while((line = reader.readLine()) != null)
			{
				list.add(line);
			}
		}
		catch(Exception e)
		{
			throw new ReaderException(e);
		}
		
		return list;
	}
	
}
