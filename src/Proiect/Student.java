package Proiect;

public class Student {
	private String nume;
	private String prenume;
	private String email;
	private String specializare;
	private String anStudiu;
	private String nrMatricol;
	
	public Student(String nume, String prenume, String email, String specializare, String anStudiu, String nrMatricol) {
		super();
		this.nrMatricol = nrMatricol;
		this.nume = nume;
		this.prenume = prenume;
		this.email = email;
		this.specializare = specializare;
		this.anStudiu = anStudiu;
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
	}
	
	public String getSpecializare() {
		return specializare;
	}
	public void setSpecializare(String specializare) {
		this.specializare = specializare;
	}
	
	public String getAnStudiu() {
		return anStudiu;
	}
	public void setAnStudiu(String anStudiu) {
		this.anStudiu = anStudiu;
	}
	
	public String getNrMatricol() {
		return nrMatricol;
	}
	public void setNrMatricol(String nrMatricol) {
		this.nrMatricol = nrMatricol;
	}


	public String toString() {
		return "\nNumar matricol: " + nrMatricol + "\nNume: " + nume + "\nPrenume: " + prenume + "\nEmail: " + email + "\nSpecializare: " + specializare
				+ "\nAn de studiu: " + anStudiu + "\n" + "\n------";
	}
	
	
	
	

}