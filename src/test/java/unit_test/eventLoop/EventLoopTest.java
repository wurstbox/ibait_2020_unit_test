package unit_test.eventLoop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EventLoopTest
{
	@Test
	void startMustStartEventLoop()
	{
		EventLoop el = new EventLoop();
		el.start();
		
		Assertions.assertTrue(el.isRunning());
	}

	@Test
	void stopMustEndEventLoop()
	{
		EventLoop el = new EventLoop();
		el.start();
		el.stop();
		
		Assertions.assertFalse(el.isRunning());
	}
}
