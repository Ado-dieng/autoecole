package repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import domaine.Moniteur;
import repository.IMoniteurRepository;

public class JdbcBasedMoniteurRepository implements IMoniteurRepository {
	private final String SQL_INSERT="INSERT INTO personne(\r\n"
			+ "	nom, prenom, telephone,	adresse, dateEmb)\r\n"
			+ "	VALUES (?, ?, ?, ?, ?);";
	private final String SQL_SELECT_ONE="SELECT * FROM personne\r\n"
    		+ "WHERE id = ?";
    private final String SQL_SELECT_ALL="SELECT *FROM personne where dateEmb!='';";
	private DataSource datasource;

	public JdbcBasedMoniteurRepository(DataSource datasource) {
		super();
		this.datasource = datasource;
	}

	public int create(Moniteur objet) {
int result=0;

		datasource.initPS(SQL_INSERT);
        try {
        	datasource.getPstm().setString(1,objet.getNom());
        	datasource.getPstm().setString(2,objet.getPrenom());
        	datasource.getPstm().setString(3,objet.getTelephone());
        	datasource.getPstm().setString(4,objet.getAdresse());
        	datasource.getPstm().setString(5,objet.getDateEmb());

            datasource.executeMaj();
            ResultSet rs=datasource.getPstm().getGeneratedKeys();
            if(rs.next()){
                result=rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JdbcBasedMoniteurRepository.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
              datasource.CloseConnection();
        }

        return result;
	}

	public int update(Moniteur objet) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Moniteur> findAll() {
		datasource.initPS(SQL_SELECT_ALL);
        List<Moniteur> result=null;
        try {
         //Mapping  objet(Profil) avec Table Profil
           ResultSet rs=datasource.executeSelect();
           result=new ArrayList<>();
           while(rs.next()){
        	   Moniteur moniteur=new Moniteur();
	           	moniteur.setId(rs.getInt("id"));
	           	moniteur.setNom(rs.getString("nom"));
	           	moniteur.setPrenom(rs.getString("prenom"));
	           	moniteur.setTelephone(rs.getString("telephone"));
	           	moniteur.setAdresse(rs.getString("adresse"));
	           	moniteur.setDateEmb(rs.getString("dateEmb"));
               result.add(moniteur);
           }


     } catch (SQLException ex) {
         Logger.getLogger(JdbcBasedMoniteurRepository.class.getName()).log(Level.SEVERE, null, ex);
     }
     datasource.CloseConnection();
     return result;
	}

	public Moniteur findById(int id) {
		Moniteur moniteur = null;
        datasource.initPS(SQL_SELECT_ONE);
        try {
        	datasource.getPstm().setInt(1, id);
            ResultSet rs = datasource.executeSelect();
            if(rs.next()){
            	moniteur=new Moniteur();
            	moniteur.setId(rs.getInt("id"));
            	moniteur.setNom(rs.getString("nom"));
            	moniteur.setPrenom(rs.getString("prenom"));
            	moniteur.setTelephone(rs.getString("telephone"));
            	moniteur.setAdresse(rs.getString("adresse"));
            	moniteur.setDateEmb(rs.getString("dateEmb"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcBasedMoniteurRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        datasource.CloseConnection();
        return moniteur;
	}

}
