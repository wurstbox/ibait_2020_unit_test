package dip;

import dip.data_model.Contact;
import dip.data_model.ContactDefault;

public abstract class Factory
{
	static Factory implementation;
	
	static
	{
		implementation = new Factory()
		{
			@Override
			public Contact newContact(String name, String eMail)
			{
				System.out.println("Creating Contact From Factory");
				return new ContactDefault(name, eMail);
			}
		};
	}

	public static Factory get()
	{
		return implementation;
	}
	
	public abstract Contact newContact(String name, String eMail);
}
