package domaine;

public class Paiement {
	private int id;
    private String numero;
    private String date;
    private int montant;
    private Examen examen;
	public Paiement() {
		super();
	}
	public Paiement(String numero, String date, int montant, Examen examen) {
		super();
		this.numero = numero;
		this.date = date;
		this.montant = montant;
		this.examen = examen;
	}
	public Paiement(int id, String numero, String date, int montant, Examen examen) {
		super();
		this.id = id;
		this.numero = numero;
		this.date = date;
		this.montant = montant;
		this.examen = examen;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	public Examen getExamen() {
		return examen;
	}
	public void setExamen(Examen examen) {
		this.examen = examen;
	}
    
}
