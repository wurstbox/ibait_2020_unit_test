package unit_test.reading;

import java.util.List;

import unit_test.data_model.Contact;

public abstract class ContactLoader
{

	public ContactLoader()
	{
		super();
	}

	public abstract List<Contact> load() throws LoadingError;

}