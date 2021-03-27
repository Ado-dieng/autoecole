package domaine;

public class Vehicule {
	private int id;
    private String numero;
    private String marque;
    private String modele;
    private String type;


	public Vehicule() {
		super();
	}
	public Vehicule(String numero, String marque, String modele, String type) {
		super();
		this.numero = numero;
		this.marque = marque;
		this.modele = modele;
		this.type = type;
	}
	public Vehicule(int id, String numero, String marque, String modele, String type) {
		super();
		this.id = id;
		this.numero = numero;
        this.marque = marque;
        this.modele = modele;
        this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
    public String getMarque() {
        return marque;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }
    public String getModele() {
        return modele;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Vehicule [id=" + id + ", numero=" + numero + ", marque=" + marque + ", modele=" + modele + ", type="
				+ type + "]";
	}

}
