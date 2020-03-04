package dip.reading;

class ReaderException extends Exception
{

	public ReaderException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ReaderException(String message)
	{
		super(message);
	}

	public ReaderException(Throwable cause)
	{
		super(cause);
	}
	
}
