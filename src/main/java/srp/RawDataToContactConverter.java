package srp;

import java.util.LinkedList;
import java.util.List;

public class RawDataToContactConverter
{
	public List<Contact> convert(List<String> rawData) throws ConversionException
	{
		List<Contact> result = new LinkedList<>();
		
		for( String rawDataSet : rawData )
		{
			Contact contact = convertSingle(rawDataSet);
			result.add(contact);
		}
		
		return result;
	}

	private Contact convertSingle(String rawDataSet) throws ConversionException
	{
		String[] splitRawData = splitRawData(rawDataSet);
		Contact contact = createContact(splitRawData);
		return contact;
	}

	private Contact createContact(String[] splitRawData)
	{
		Contact contact = new Contact(splitRawData[0], splitRawData[1]);
		return contact;
	}

	private String[] splitRawData(String rawDataSet) throws ConversionException
	{
		String[] splitRawData = rawDataSet.split("\t");
		checkSplitData(rawDataSet, splitRawData);
		return splitRawData;
	}

	private void checkSplitData(String rawDataSet, String[] splitRawData) throws ConversionException
	{
		if(splitRawData.length < 2)
			throw new ConversionException("Unexpected RawData: \"" + rawDataSet + "\"");
	}
}
