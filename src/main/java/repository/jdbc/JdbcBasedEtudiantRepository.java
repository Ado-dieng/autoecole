package repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import domaine.Etudiant;
import repository.IEtudiantRepository;

public class JdbcBasedEtudiantRepository implements IEtudiantRepository {
	private final String SQL_INSERT="INSERT INTO personne(\r\n"
			+ "	nom, prenom, telephone,	adresse, nbrLecon, numContrat, dateIns,	codePostal)\r\n"
			+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private final String SQL_SELECT_ONE="SELECT * FROM personne\r\n"
    		+ "WHERE id = ?";
    private final String SQL_SELECT_ALL="SELECT *FROM personne where dateIns!=''";
	private DataSource datasource;

	public JdbcBasedEtudiantRepository(DataSource datasource) {
		super();
		this.datasource = datasource;
	}

	public int create(Etudiant objet) {
int result=0;

		datasource.initPS(SQL_INSERT);
        try {
        	datasource.getPstm().setString(1,objet.getNom());
        	datasource.getPstm().setString(2,objet.getPrenom());
        	datasource.getPstm().setString(3,objet.getTelephone());
        	datasource.getPstm().setString(4,objet.getAdresse());
        	datasource.getPstm().setInt(5,objet.getNbrLecon());
        	datasource.getPstm().setString(6,objet.getNumContrat());
        	datasource.getPstm().setString(7,objet.getDateIns());
        	datasource.getPstm().setString(8,objet.getCodePostal());

            datasource.executeMaj();
            ResultSet rs=datasource.getPstm().getGeneratedKeys();
            if(rs.next()){
                result=rs.getInt(1);
            }


        } catch (SQLException ex) {
            Logger.getLogger(JdbcBasedEtudiantRepository.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
              datasource.CloseConnection();
        }

        return result;
	}

	public int update(Etudiant objet) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Etudiant> findAll() {
		datasource.initPS(SQL_SELECT_ALL);
        List<Etudiant> result=null;
        try {

           ResultSet rs=datasource.executeSelect();
           result=new ArrayList<>();
           while(rs.next()){
        	   Etudiant etudiant=new Etudiant();
        	   etudiant.setId(rs.getInt("id"));
	           	etudiant.setNom(rs.getString("nom"));
	           	etudiant.setPrenom(rs.getString("prenom"));
	           	etudiant.setTelephone(rs.getString("telephone"));
	           	etudiant.setAdresse(rs.getString("adresse"));
	           	etudiant.setNbrLecon(rs.getInt("nbrLecon"));
	           	etudiant.setNumContrat(rs.getString("numContrat"));
	           	etudiant.setDateIns(rs.getString("dateIns"));
	           	etudiant.setCodePostal(rs.getString("codePostal"));
               result.add(etudiant);
           }


     } catch (SQLException ex) {
         Logger.getLogger(JdbcBasedEtudiantRepository.class.getName()).log(Level.SEVERE, null, ex);
     }
     datasource.CloseConnection();
     return result;
	}

	public Etudiant findById(int id) {
		Etudiant etudiant = null;
        datasource.initPS(SQL_SELECT_ONE);
        try {
        	datasource.getPstm().setInt(1, id);
            ResultSet rs = datasource.executeSelect();
            if(rs.next()){
            	etudiant=new Etudiant();
            	etudiant.setId(rs.getInt("id"));
            	etudiant.setNom(rs.getString("nom"));
            	etudiant.setPrenom(rs.getString("prenom"));
            	etudiant.setTelephone(rs.getString("telephone"));
            	etudiant.setAdresse(rs.getString("adresse"));
            	etudiant.setNbrLecon(rs.getInt("nbrLecon"));
            	etudiant.setNumContrat(rs.getString("numContrat"));
            	etudiant.setDateIns(rs.getString("dateIns"));
            	etudiant.setCodePostal(rs.getString("codePostal"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcBasedEtudiantRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        datasource.CloseConnection();
        return etudiant;
	}

}
