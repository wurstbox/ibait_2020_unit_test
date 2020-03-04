package dip.console;

import java.io.IOException;

public class ConsoleException extends Exception
{

	public ConsoleException(IOException e)
	{
		super(e);
	}

}
