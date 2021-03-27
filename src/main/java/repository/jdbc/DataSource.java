package repository.jdbc;

import java.sql.*;

public interface DataSource {

	public Connection getConnection();
	public void initPS(String sql);
    public int executeMaj();
    public ResultSet executeSelect();
    public PreparedStatement getPstm();
    public void CloseConnection();
}
