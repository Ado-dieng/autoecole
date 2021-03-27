package domaine;

public class Moniteur extends Personne {
    private String dateEmb;
	public Moniteur() {
		super();
	}
	public Moniteur(String nom, String prenom, String telephone, String adresse, String dateEmb) {
		super(nom, prenom, telephone, adresse);
		this.dateEmb = dateEmb;
	}
	public Moniteur(int id, String nom, String prenom, String telephone, String adresse, String dateEmb) {
		super(id, nom, prenom, telephone, adresse);
		this.dateEmb = dateEmb;
	}
	public String getDateEmb() {
		return dateEmb;
	}
	public void setDateEmb(String dateEmb) {
		this.dateEmb = dateEmb;
	}
	@Override
	public String toString() {
		return "Moniteur [dateEmb=" + dateEmb + ", id=" + getId() + ", nom=" + getNom() + ", prenom="
				+ getPrenom() + ", telephone=" + getTelephone() + ", adresse=" + getAdresse() + "]";
	}
    

}
