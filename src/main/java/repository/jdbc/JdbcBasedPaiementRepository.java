package repository.jdbc;

import domaine.Examen;
import domaine.Paiement;
import repository.IPaiementRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcBasedPaiementRepository implements IPaiementRepository {

    private final String SQL_INSERT="INSERT INTO paiement(\r\n"
        + "	numero, date, montant,	examen_id)\r\n"
        + "	VALUES (?, ?, ?, ?);";
    private final String SQL_SELECT_ONE="SELECT * FROM paiement\r\n"
        + "WHERE id = ?";
    private final String SQL_SELECT_ALL="SELECT *FROM paiement";
    private DataSource datasource;
    private  JdbcBasedExamenRepository daoExamen;

    public JdbcBasedPaiementRepository(DataSource datasource) {
        super();
        this.datasource = datasource;
        daoExamen=new JdbcBasedExamenRepository(datasource);
    }



    public int create(Paiement objet) {
        int result=0;

        datasource.initPS(SQL_INSERT);
        try {
            datasource.getPstm().setString(1,objet.getNumero());
            datasource.getPstm().setString(2,objet.getDate());
            datasource.getPstm().setInt(3,objet.getMontant());
            datasource.getPstm().setInt(4,objet.getExamen().getId());

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


    public int update(Paiement objet) {
        return 0;
    }


    public List<Paiement> findAll() {
        datasource.initPS(SQL_SELECT_ALL);
        List<Paiement> result=null;
        try {

            ResultSet rs=datasource.executeSelect();
            result=new ArrayList<>();
            while(rs.next()){
                Paiement paiement=new Paiement();
                paiement.setId(rs.getInt("id"));
                paiement.setNumero(rs.getString("numero"));
                paiement.setDate(rs.getString("date"));
                paiement.setMontant(rs.getInt("montant"));
                int id_examen=rs.getInt("examen_id");
                Examen ex =daoExamen.findById(id_examen);
                paiement.setExamen(ex);

                result.add(paiement);
            }


        } catch (SQLException ex) {
            Logger.getLogger(JdbcBasedEtudiantRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        datasource.CloseConnection();
        return result;
    }


    public Paiement findById(int id) {
        Paiement paiement = null;
        datasource.initPS(SQL_SELECT_ONE);
        try {
            datasource.getPstm().setInt(1, id);
            ResultSet rs = datasource.executeSelect();
            if(rs.next()){
                paiement=new Paiement();
                paiement.setId(rs.getInt("id"));
                paiement.setNumero(rs.getString("numero"));
                paiement.setDate(rs.getString("date"));
                paiement.setMontant(rs.getInt("montant"));


            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcBasedEtudiantRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        datasource.CloseConnection();
        return paiement;
    }
}
