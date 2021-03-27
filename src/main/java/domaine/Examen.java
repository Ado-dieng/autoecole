package domaine;

public class Examen {
	private int id;
    private String type;
    private String date;
    private String heure;
    private Vehicule vehicule;
    private Moniteur moniteur;
    private Etudiant etudiant;
	public Examen() {
		super();
	}
	public Examen(String type, String date, String heure, Vehicule vehicule, Etudiant etudiant, Moniteur moniteur) {
		super();
		this.type = type;
		this.date = date;
		this.heure = heure;
		this.vehicule = vehicule;
        this.etudiant = etudiant;
		this.moniteur = moniteur;

	}
	public Examen(int id, String type, String date, String heure, Vehicule vehicule,Etudiant etudiant,
                  Moniteur moniteur
			) {
		super();
		this.id = id;
		this.type = type;
		this.date = date;
		this.heure = heure;
		this.vehicule = vehicule;
        this.etudiant = etudiant;
		this.moniteur = moniteur;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Moniteur getMoniteur() {
        return moniteur;
    }

    public void setMoniteur(Moniteur moniteur) {
        this.moniteur = moniteur;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

	@Override
	public String toString() {
		return "Examen [id=" + id + ", type=" + type + ", date=" + date + ", heure=" + heure + ", vehicule=" + vehicule
				+ ", etudiant=" + etudiant + ", moniteur=" + moniteur + "]";
	}

}
