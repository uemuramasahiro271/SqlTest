import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseSqlTest implements SqlTest{

	private Connection connection;
	private String connectionStr;

	protected BaseSqlTest(String connectionStr) {
		this.connectionStr = connectionStr;
	}

	public void connect() throws SQLException {
		connection = DriverManager.getConnection(connectionStr);
	}

	public void disconnect() throws SQLException {
	      try {
	          if ( connection != null ) connection.close();
	        }
	        catch(SQLException e){
	          System.out.println( "Exception! :" + e.toString() );
	          throw e;
	        }
	}

	public void execute(String[] sqlList)  throws SQLException{
		try {
			connection.setAutoCommit(false);

			for(var sql : sqlList) {
				var stmt = connection.prepareStatement(sql);
				stmt.executeUpdate();
			}

			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
			System.out.println("rollback");
		}
	}

	public void select(String sql)  throws SQLException{
		//結果を表示する
		var stmt = connection.prepareStatement(sql);
		var rs = stmt.executeQuery();
		showResult(rs);
	}

	protected void initConnection(String connectionStr) throws SQLException {
		connection = DriverManager.getConnection(connectionStr);
	}

	private void showResult(ResultSet rs) throws SQLException {

		String columnString = "";
		List<String> rowList = new ArrayList<String>();
		boolean flag = false;

		while(rs.next()) {
			ResultSetMetaData rsmd = rs.getMetaData();

			var rowStr = "";
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				var columnName = rsmd.getColumnLabel(i);
				if(!flag) {
					columnString += columnName + ", ";
				}
				rowStr += rs.getString(columnName) + ", ";
			}
			flag = true;
			rowList.add(rowStr);
		}

		System.out.println("----------------------------------------------");
		System.out.println(columnString);
		for(var rowStr : rowList) {
			System.out.println(rowStr);
		}
		System.out.println("----------------------------------------------");

	}

}
