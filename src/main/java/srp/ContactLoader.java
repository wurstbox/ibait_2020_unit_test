package srp;

import java.util.List;

public class ContactLoader
{
	private final String pathToResource;

	public ContactLoader(String pathToResource)
	{
		this.pathToResource = pathToResource;
	}

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
