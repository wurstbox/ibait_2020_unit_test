package clean_up;

public class Contact {
	private String name;
	private String eMail;

	public String getName() {
		return name;
	}

	public String getEMail() {
		return eMail;
	}

	public Contact(String name, String eMail) {
		this.name = name;
		this.eMail = eMail;
	}
}