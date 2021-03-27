package service;


import domaine.Etudiant;
import domaine.Examen;
import domaine.Moniteur;
import domaine.Vehicule;

public interface DisplayService {
	void displayBienvenu();

    void displayMenuPrincipal();

	void displayChoixInconnu();

	void displaySaisie(String saisie);

	void displayVehicule(Vehicule v);
	void displayEtudiant(Etudiant e);
	void displayMoniteur(Moniteur m);
	void  displayExamen(Examen ex);


}
