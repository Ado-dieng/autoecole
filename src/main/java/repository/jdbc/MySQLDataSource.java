package repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDataSource implements DataSource {
	private  Connection cnx;
    private  PreparedStatement pstm;
    private int ok;
    private ResultSet rs;
    public Connection getConnection(){
		try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/auto_ecole","root","");
            
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de Chargement de Driver");
        } catch (SQLException ex) {
            System.err.println("Erreur de Connexion");
        }
		return cnx;
	}

	public void initPS(String sql)
	{
		getConnection();
		try{
			  if(sql.toLowerCase().startsWith("insert"))
			     {
			    	pstm=cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			     }
			else{
				   pstm=cnx.prepareStatement(sql);
		        }
		    }catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public int executeMaj()
	{
		try {
			ok = pstm.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return ok;
	}
	public ResultSet executeSelect()
	{
		try {

			rs=pstm.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return rs;
	}
	public PreparedStatement getPstm()
	{
		return  this.pstm;

	}
    public void CloseConnection(){
		try{
		if(cnx!=null){
			cnx.close();
		}
		}catch(Exception ex){
			ex.printStackTrace();

		}
	}


}
