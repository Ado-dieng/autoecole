package repository.jdbc;

import domaine.Etudiant;
import domaine.Examen;
import repository.IEtudiantRepository;
import repository.IExamenRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcBasedExamenRepository implements IExamenRepository {

    private final String SQL_INSERT="INSERT INTO examen(\r\n"
        + "	type, date, heure,	vehicule_id, etudiant_id, moniteur_id)\r\n"
        + "	VALUES (?, ?, ?, ?, ?, ?);";
    private final String SQL_SELECT_ONE="SELECT * FROM examen\r\n"
        + "WHERE id = ?";
    private final String SQL_SELECT_ALL="SELECT *FROM examen;";
    private DataSource datasource;
    private  JdbcBasedEtudiantRepository daoEtudiant;

    public JdbcBasedExamenRepository(DataSource datasource) {
        super();
        this.datasource = datasource;
       daoEtudiant=new JdbcBasedEtudiantRepository(datasource);
    }

    public int create(Examen objet) {
        int result=0;

        datasource.initPS(SQL_INSERT);
        try {
            datasource.getPstm().setString(1,objet.getType());
            datasource.getPstm().setString(2,objet.getDate());
            datasource.getPstm().setString(3,objet.getHeure());
          datasource.getPstm().setInt(4,objet.getVehicule().getId());
            datasource.getPstm().setInt(5,objet.getEtudiant().getId());
            datasource.getPstm().setInt(6,objet.getMoniteur().getId());


            datasource.executeMaj();
            //datasource.getPstm().executeUpdate();
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


    public int update(Examen objet) {
        return 0;
    }


    public List<Examen> findAll() {
        datasource.initPS(SQL_SELECT_ALL);
        List<Examen> result=null;
        try {

            ResultSet rs=datasource.executeSelect();
            result=new ArrayList<>();
            while(rs.next()){
                Examen examen=new Examen();
                examen.setId(rs.getInt("id"));
                examen.setType(rs.getString("type"));
                examen.setDate(rs.getString("date"));
                examen.setHeure(rs.getString("heure"));
                int id_etudiant=rs.getInt("etudiant_id");
                Etudiant e =daoEtudiant.findById(id_etudiant);
                examen.setEtudiant(e);
               /* examen.setVehicule(rs.getInt("vehicule"));
                examen.setEtudiant(rs.getInt("etudiant"));
                examen.setMoniteur(rs.getInt("moniteur"));*/
                result.add(examen);
            }


        } catch (SQLException ex) {
            Logger.getLogger(JdbcBasedEtudiantRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        datasource.CloseConnection();
        return result;
    }


    public Examen findById(int id) {
        Examen examen = null;
        datasource.initPS(SQL_SELECT_ONE);
        try {
            datasource.getPstm().setInt(1, id);
            ResultSet rs = datasource.executeSelect();
            if(rs.next()){
                examen=new Examen();
                examen.setId(rs.getInt("id"));
                examen.setType(rs.getString("type"));
                examen.setDate(rs.getString("date"));
                examen.setHeure(rs.getString("heure"));
              /*  examen.setVehicule(rs.getInt("vehicule"));
                examen.setEtudiant(rs.getInt("etudiant"));
                examen.setMoniteur(rs.getInt("moniteur"));*/

            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcBasedEtudiantRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        datasource.CloseConnection();
        return examen;
    }
}
