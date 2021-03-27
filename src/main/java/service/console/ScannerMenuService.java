package service.console;

import java.util.List;
import java.util.Scanner;

import domaine.*;
import repository.jdbc.*;
import service.DisplayService;
import service.MenuService;

public class ScannerMenuService implements MenuService {
	private final DisplayService displayService;
	private final Scanner scanner;
	private final DataSource dataSource;
	private final JdbcBasedEtudiantRepository etudiantRepository;
	private final JdbcBasedMoniteurRepository moniteurRepository;
	private final JdbcBasedVehiculeRepository vehiculeRepository;
	private final JdbcBasedExamenRepository examenRepository;
	private final JdbcBasedPaiementRepository paiementRepository;


	public ScannerMenuService(DisplayService displayService) {
		super();
		this.displayService = displayService;
		this.scanner = new Scanner(System.in);
		this.dataSource=new MySQLDataSource();
		this.etudiantRepository=new JdbcBasedEtudiantRepository(dataSource);
		this.moniteurRepository=new JdbcBasedMoniteurRepository(dataSource);
		this.vehiculeRepository=new JdbcBasedVehiculeRepository(dataSource);
		this.examenRepository=new JdbcBasedExamenRepository(dataSource);
		this.paiementRepository=new JdbcBasedPaiementRepository(dataSource);

	}
	@Override
	public void displayMenu() {
		String choix=this.lireChoix();
		this.serviceBienvenu(choix);

	}
	private String lireChoix() {
        return scanner.nextLine();
    }
	private void serviceBienvenu(String choix)
	{
		switch (choix) {
		case "m":
            if(this.serviceAddMoniteur(this.serviceAdd())!=0)

                System.out.println("A new monitor was inserted successfully!");
            else
                System.out.println("A new monitor was not inserted successfully!");
			break;

		case "e":
            if(this.serviceAddEtudiant(this.serviceAdd())!=0)
                System.out.println("A new student was inserted successfully!");
            else
                 System.out.println("A new student was not inserted successfully!");
            break;



       case "v":
           if(this.serviceAddVehicule(this.serviceAddV())!=0)
                 System.out.println("A new vehicle was inserted successfully!");
            else
                 System.out.println("A new vehicle was not inserted successfully!");
           break;



       case "n":
          if(this.serviceAddExamen(this.serviceAddE())!=0)
            System.out.println("A new exam was inserted successfully!");
          else
            System.out.println("A new exam was not inserted successfully!");

             break;

		case "p":
            if(this.serviceAddPaiement(this.serviceAddP())!=0)
            System.out.println("A new payment was inserted successfully!");
            else
            System.out.println("A new payment was not inserted successfully!");
            break;


		default:
			displayService.displayChoixInconnu();
			break;
		}
		displayService.displayBienvenu();
		displayService.displayMenuPrincipal();
		this.displayMenu();
	}

    /***
     * Etudiant  et Moniteur
     *
     * ****/
	private int serviceAddEtudiant(String[] personne)
	{
		displayService.displaySaisie("Nombre de leçons");
		int nbrLecon=Integer.parseInt(this.lireChoix());
		displayService.displaySaisie("Numéro Contrat");
		String numContrat=this.lireChoix();
		displayService.displaySaisie("Date Inscription");
		String dateIns=this.lireChoix();
		displayService.displaySaisie("Code Postal");
		String codePostal=this.lireChoix();
		Etudiant e=new Etudiant(personne[0],personne[1],personne[2],personne[3],numContrat,nbrLecon,dateIns,codePostal);

		return this.etudiantRepository.create(e);
	}
	private int serviceAddMoniteur(String[] personne)
	{
		displayService.displaySaisie("Date Embauche");
		String dateEmb=this.lireChoix();
		Moniteur m=new Moniteur(personne[0],personne[1],personne[2],personne[3],dateEmb);

		return this.moniteurRepository.create(m);
	}
	private String[] serviceAdd()
	{
		displayService.displaySaisie("Nom");
		String nom=this.lireChoix();
		displayService.displaySaisie("Prénom");
		String prenom=this.lireChoix();
		displayService.displaySaisie("Téléphone");
		String telephone=this.lireChoix();
		displayService.displaySaisie("Adresse");
		String adresse=this.lireChoix();
		String[] tab={nom,prenom,telephone,adresse};
		return tab;
	}

	/***
     * Vehicule
     * ****/
    private int serviceAddVehicule(String[] vehicule)
    {

        Vehicule v=new Vehicule(vehicule[0],vehicule[1],vehicule[2],vehicule[3]);

        return this.vehiculeRepository.create(v);
    }

	private String[] serviceAddV()
    {
        displayService.displaySaisie("Numero");
        String numero=this.lireChoix();
        displayService.displaySaisie("Marque");
        String marque=this.lireChoix();
        displayService.displaySaisie("Modele");
        String modele=this.lireChoix();
        displayService.displaySaisie("Type");
        String type=this.lireChoix();
        String[] tab={numero,marque,modele,type};
        return tab;
    }

    /***
     * Examen
     * ****/

    private int serviceAddExamen(String[] examen)
    {
        Vehicule v =this.vehiculeRepository.findById(Integer.parseInt(examen[3]));
        Etudiant e =this.etudiantRepository.findById(Integer.parseInt(examen[4]));
        Moniteur m =this.moniteurRepository.findById(Integer.parseInt(examen[5]));

        Examen ex=new Examen(examen[0],examen[1],examen[2],v,e,m);

        return this.examenRepository.create(ex);
    }

    private String[] serviceAddE()
    {
        displayService.displaySaisie("Type");
        String type=this.lireChoix();
        displayService.displaySaisie("Date");
        String date=this.lireChoix();
        displayService.displaySaisie("Heure");
        String heure=this.lireChoix();

        this.serviceListVehicule(vehiculeRepository.findAll());
        String idVehicule =this.lireChoix() ;
        this.serviceListEtudiant(etudiantRepository.findAll());
        String  idEtudiant =this.lireChoix() ;
        this.serviceListMoniteur(moniteurRepository.findAll());
        String idMoniteur =this.lireChoix() ;



        String[] tab={type,date,heure,idVehicule,idEtudiant,idMoniteur};
        return tab;
    }

    private void serviceListVehicule(List<Vehicule> vehicules)
    {
        if(vehicules.isEmpty())

            System.out.println("la liste est vide");
        else
        {
            System.out.println("------\n \tListe des Vehicules\n------");
            vehicules.stream().forEach(v->displayService.displayVehicule(v));
            System.out.println("--------------------------------");
        }
    }

    private void serviceListEtudiant(List<Etudiant> etudiants)
    {
        if(etudiants.isEmpty())

            System.out.println("la liste est vide");
        else
        {
            System.out.println("------\n \tListe des Etudiants\n------");
            etudiants.stream().forEach(e->displayService.displayEtudiant(e));
            System.out.println("--------------------------------");
        }
    }

    private void serviceListMoniteur(List<Moniteur> moniteurs)
    {
        if(moniteurs.isEmpty())

            System.out.println("la liste est vide");
        else
        {
            System.out.println("------\n \tListe des Moniteurs\n------");
            moniteurs.stream().forEach(m->displayService.displayMoniteur(m));
            System.out.println("--------------------------------");
        }
    }

    /***
     * Paiement
     * ****/

    private int serviceAddPaiement(String[] paiement)
    {
        Examen ex =this.examenRepository.findById(Integer.parseInt(paiement[3]));


        Paiement p=new Paiement(paiement[0],paiement[1],Integer.parseInt(paiement[2]),ex);

        return this.paiementRepository.create(p);
    }

    private String[] serviceAddP()
    {
        displayService.displaySaisie("Numero");
        String numero=this.lireChoix();
        displayService.displaySaisie("Date");
        String date=this.lireChoix();
        displayService.displaySaisie("Montant");
        String montant=this.lireChoix();

        this.serviceListExamen(examenRepository.findAll());
        String idExamen =this.lireChoix() ;
        String[] tab={numero,date,montant,idExamen};
        return tab;
    }
    private void serviceListExamen(List<Examen> examens)
    {
        if(examens.isEmpty())

            System.out.println("la liste est vide");
        else
        {
            System.out.println("------\n \tListe des Examens\n------");
            examens.stream().forEach(ex->displayService.displayExamen(ex));
            System.out.println("--------------------------------");
        }
    }

}
