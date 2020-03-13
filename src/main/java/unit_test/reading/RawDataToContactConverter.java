package unit_test.reading;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import unit_test.Factory;
import unit_test.data_model.Contact;

class RawDataToContactConverter
{
	/** converts raw data strings to Contacts
	 * @return a list of contacts derived from the raw data
	 * @param rawData raw data strings must contain name and email separated by tab character
	 * @throws ConversionException will be thrown if
	 * 	<ul>
	 * 		<li>rawData is null</li>
	 * 		<li>any rawData item is null</li>
	 *  	<li>rawData items not matching pattern: name + tabs + email [ + tabs + ...]</li>
	 *  </ul>
	 */
	public List<Contact> convert(List<String> rawData) throws ConversionException
	{
		checkInput(rawData);
		
		List<Contact> result = new LinkedList<>();
		
		for( String rawDataSet : rawData )
		{
			Contact contact = convertSingle(rawDataSet);
			result.add(contact);
		}
		
		return result;
	}

	private void checkInput(List<String> rawData) throws ConversionException
	{
		checkRawDataListIsNotNull(rawData);
	}

	private void checkRawDataListIsNotNull(List<String> rawData) throws ConversionException
	{
		if(Objects.equals(rawData, null))
			throw new ConversionException("rawData List is null");
	}

	private Contact convertSingle(String rawDataSet) throws ConversionException
	{
		checkRawDataSetIsNotNull(rawDataSet);
		
		String[] splitRawData = splitRawData(rawDataSet);
		Contact contact = createContact(splitRawData);
		return contact;
	}

	private void checkRawDataSetIsNotNull(String rawDataSet) throws ConversionException
	{
		if(Objects.equals(rawDataSet, null))
			throw new ConversionException("rawData List contains null value");
	}

	private Contact createContact(String[] splitRawData)
	{
		Contact contact = Factory.get().newContact(splitRawData[0], splitRawData[1]);
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
