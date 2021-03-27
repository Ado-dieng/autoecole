package domaine;

public class Etudiant extends Personne {
    private String numContrat;
    private int nbrLecon;
    private String dateIns;
    private String codePostal;
	public Etudiant() {
		super();
	}

	public Etudiant(String nom, String prenom, String telephone, String adresse, String numContrat, int nbrLecon,
			String dateIns,String codePostal) {
		super(nom, prenom, telephone, adresse);
		this.numContrat = numContrat;
		this.nbrLecon = nbrLecon;
		this.dateIns = dateIns;
		this.codePostal = codePostal;
	}

	public Etudiant(int id, String nom, String prenom, String telephone, String adresse, String numContrat,
			int nbrLecon, String dateIns,String codePostal) {
		super(id, nom, prenom, telephone, adresse);
		this.numContrat = numContrat;
		this.nbrLecon = nbrLecon;
		this.dateIns = dateIns;
		this.codePostal = codePostal;
	}

	public String getNumContrat() {
		return numContrat;
	}
	public void setNumContrat(String numContrat) {
		this.numContrat = numContrat;
	}
	public int getNbrLecon() {
		return nbrLecon;
	}
	public void setNbrLecon(int nbrLecon) {
		this.nbrLecon = nbrLecon;
	}
	public String getDateIns() {
		return dateIns;
	}
	public void setDateIns(String dateIns) {
		this.dateIns = dateIns;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	@Override
	public String toString() {
		return "Etudiant [numContrat=" + numContrat + ", nbrLecon=" + nbrLecon + ", dateIns=" + dateIns + ", id="
				+ getId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + ", telephone="
				+ getTelephone() + ", adresse=" + getAdresse() +", codePostal=" + getCodePostal() + "]";
	}


}
