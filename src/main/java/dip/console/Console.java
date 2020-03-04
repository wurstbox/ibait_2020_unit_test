package dip.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console
{
	private final BufferedReader consoleReader;
	
	public Console()
	{
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
	}

	public String readLine(String prompt) throws ConsoleException
	{
		try
		{
			System.out.print(prompt + " ");
			String line = consoleReader.readLine();
			return line;
		}
		catch(IOException e)
		{
			throw new ConsoleException(e);
		}
	}

	public void writeLine(String line)
	{
		System.out.println(line);
	}
}

