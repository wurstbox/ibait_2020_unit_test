package unit_test.reading;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RawDataToContactConverterTest
{
	@Test
	void convertMustNotErrorForCorrectRawData() throws Exception
	{
		var conv = new RawDataToContactConverter();
		List<String> list = new LinkedList<>();

		String rawdata = "name\tname@email.com";
		list.add(rawdata);

		try
		{
			conv.convert(list);
		}
		catch(ConversionException e)
		{
			fail("Exception occurred: " + e.getClass());
		}
	}

	@Test
	void convertMustErrorForWrongRawData() throws Exception
	{
		var conv = new RawDataToContactConverter();
		List<String> list = new LinkedList<>();

		String rawdata = "name name@email.com";
		list.add(rawdata);

		try
		{
			conv.convert(list);
			fail("ConversionException expected but not thrown");
		}
		catch(ConversionException e)
		{
		}
	}

	@Test
	void convertMustReturnContactWithNameEqualToXXX() throws Exception
	{
		var conv = new RawDataToContactConverter();
		List<String> list = new LinkedList<>();

		String rawdata = "XXX\tname@email.com";
		list.add(rawdata);

		var result = conv.convert(list);

		assertEquals("XXX", result.get(0).getName());
	}

	@Test
	void convertMustReturnContactWithEMailEqualToYYY() throws Exception
	{
		var conv = new RawDataToContactConverter();
		List<String> list = new LinkedList<>();

		String rawdata = "XXX\tYYY";
		list.add(rawdata);

		var result = conv.convert(list);

		assertEquals("YYY", result.get(0).getEMail());
	}

	@Test
	void convertMustReturnContactWithNameEqualToXXXDespiteOfAdditionalTabCharacter() throws Exception
	{
		var conv = new RawDataToContactConverter();
		List<String> list = new LinkedList<>();

		String rawdata = "XXX\tname@email.com\t";
		list.add(rawdata);

		var result = conv.convert(list);

		assertEquals("XXX", result.get(0).getName());
	}

	@Test
	void convertMustReturnContactWithEMailEqualToYYYDespiteOfAdditionalTabCharacter() throws Exception
	{
		var conv = new RawDataToContactConverter();
		List<String> list = new LinkedList<>();

		String rawdata = "XXX\tYYY\t";
		list.add(rawdata);

		var result = conv.convert(list);

		assertEquals("YYY", result.get(0).getEMail());
	}

	@Test
	void convertMustErrorForNullData() throws Exception
	{
		var conv = new RawDataToContactConverter();
		try
		{
			conv.convert(null);
			fail("ConversionException expected but not thrown");
		}
		catch(ConversionException e)
		{
		}
	}

	@Test
	void convertMustErrorForNullRawDataItem() throws Exception
	{
		var conv = new RawDataToContactConverter();
		List<String> list = new LinkedList<>();

		list.add("XXX\tXXX@YYY.com");
		list.add(null);

		try
		{
			conv.convert(list);
			fail("ConversionException expected but not thrown");
		}
		catch(ConversionException e)
		{
		}
	}

	@Test
	void emptyListCreatesEmptyContactList() throws Exception
	{
		var conv = new RawDataToContactConverter();
		List<String> list = new LinkedList<>();

		var result = conv.convert(list);

		assertTrue(result.isEmpty());
	}
}
