package unit_test.reading;

import java.util.List;

import unit_test.data_model.Contact;

public class ContactFromFileLoader extends ContactLoader
{
	final String pathToResource;

	public ContactFromFileLoader(String pathToResource)
	{
		this.pathToResource = pathToResource;
	}

	@Override
	public List<Contact> load() throws LoadingError
	{
		ContactRawDataReader reader = new ContactRawDataReader(pathToResource);
		RawDataToContactConverter converter = new RawDataToContactConverter();

		try
		{
			List<String> rawData = reader.read();
			List<Contact> contacts = converter.convert(rawData);
			return contacts;
		}
		catch(ReaderException | ConversionException e)
		{
			throw new LoadingError(e);
		}
	}
}
