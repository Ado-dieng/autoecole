package service.console;

import domaine.Etudiant;
import domaine.Examen;
import domaine.Moniteur;
import domaine.Vehicule;
import service.DisplayService;

public class ConsoleDisplayService implements DisplayService {

	@Override
	public void displayBienvenu() {
		System.out.println("Bienvenu dans Mon Auto Ecole");
	}

	@Override
	public void displayMenuPrincipal() {
		System.out.println("> m - Enregistrer un Moniteur");
		System.out.println("> e - Enregistrer un Etudiant");
		System.out.println("> v - Enregistrer un Vehicule");
		System.out.println("> p - Enregistrer un Paiement");
		System.out.println("> n - Programmer Examen");
	}
	@Override
	public void displayChoixInconnu() {
		System.out.println("Choix Inconnu !!!");
	}
	@Override
	public void displaySaisie(String saisie) {
		System.out.print(saisie+":\t");
	}

    @Override
    public void displayVehicule(Vehicule v) {
        System.out.println("> "+v.getId()+"   "+v.getNumero());
    }

    @Override
    public void displayEtudiant(Etudiant e) {
        System.out.println("> "+e.getId()+"   "+e.getNom()+"   "+e.getPrenom());
    }

    @Override
    public void displayMoniteur(Moniteur m) {
        System.out.println("> "+m.getId()+"   "+m.getNom()+"   "+m.getPrenom());
    }

    @Override
    public void displayExamen(Examen ex) {
        System.out.println("> "+ex.getId()+"   "+ex.getDate());
    }
}
