package Proiect;

public class Admin {

	
	private String nume;
	private String prenume;
	private String email;
	
	public Admin(String nume, String prenume, String email) {
		
		this.nume = nume;
		this.prenume = prenume;
		this.email = email;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}public Admin() {
		// TODO Auto-generated constructor stub
	}
}
