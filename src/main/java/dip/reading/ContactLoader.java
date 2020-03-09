package dip.reading;

import java.util.List;

import dip.data_model.Contact;

public abstract class ContactLoader
{

	public ContactLoader()
	{
		super();
	}

	public abstract List<Contact> load() throws LoadingError;

}