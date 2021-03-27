package repository.jdbc;

import domaine.Vehicule;
import repository.IVehiculeRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcBasedVehiculeRepository implements IVehiculeRepository {
    private final String SQL_INSERT="INSERT INTO vehicule(\r\n"
        + "	numero, marque, modele,	type)\r\n"
        + "	VALUES (?, ?, ?, ?);";
    private final String SQL_SELECT_ONE="SELECT * FROM vehicule\r\n"
        + "WHERE id = ?";
    private final String SQL_SELECT_ALL="SELECT *FROM vehicule;";
    private DataSource datasource;

    public JdbcBasedVehiculeRepository(DataSource datasource) {

        this.datasource = datasource;
    }




    public int create(Vehicule objet) {
        int result=0;

        datasource.initPS(SQL_INSERT);
        try {
            datasource.getPstm().setString(1,objet.getNumero());
            datasource.getPstm().setString(2,objet.getMarque());
            datasource.getPstm().setString(3,objet.getModele());
            datasource.getPstm().setString(4,objet.getType());


            datasource.executeMaj();
            ResultSet rs=datasource.getPstm().getGeneratedKeys();
            if(rs.next()){
                result=rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JdbcBasedVehiculeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            datasource.CloseConnection();
        }

        return result;
    }


    public int update(Vehicule objet) {
        return 0;
    }


    public List<Vehicule> findAll() {
        datasource.initPS(SQL_SELECT_ALL);
        List<Vehicule> result=null;
        try {
            //Mapping  objet(Profil) avec Table Profil
            ResultSet rs=datasource.executeSelect();
            result=new ArrayList<>();
            while(rs.next()){
                Vehicule vehicule=new Vehicule();
                vehicule.setId(rs.getInt("id"));
                vehicule.setNumero(rs.getString("numero"));
                vehicule.setMarque(rs.getString("marque"));
                vehicule.setModele(rs.getString("modele"));
                vehicule.setType(rs.getString("type"));

                result.add(vehicule);
            }


        } catch (SQLException ex) {
            Logger.getLogger(JdbcBasedEtudiantRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        datasource.CloseConnection();
        return result;
    }


    public Vehicule findById(int id) {
        Vehicule vehicule = null;
        datasource.initPS(SQL_SELECT_ONE);
        try {
            datasource.getPstm().setInt(1, id);
            ResultSet rs = datasource.executeSelect();
            if(rs.next()){
                vehicule=new Vehicule();
                vehicule.setId(rs.getInt("id"));
                vehicule.setNumero(rs.getString("numero"));
                vehicule.setMarque(rs.getString("marque"));
                vehicule.setModele(rs.getString("modele"));
                vehicule.setType(rs.getString("type"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcBasedEtudiantRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        datasource.CloseConnection();
        return vehicule;
    }
}
