package dip.eventLoop;

public class EventLoop
{
	private boolean running;
	
	public EventLoop()
	{
		running = false;
	}

	public void start()
	{
		running = true;
	}
	
	public void stop()
	{
		running = false;
	}
	
	public boolean isRunning()
	{
		return running;
	}
}
