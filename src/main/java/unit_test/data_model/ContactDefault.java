package unit_test.data_model;

public class ContactDefault implements Contact {
	private String name;
	private String eMail;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getEMail() {
		return eMail;
	}

	public ContactDefault(String name, String eMail) {
		this.name = name;
		this.eMail = eMail;
	}
}