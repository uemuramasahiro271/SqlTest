import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class MySqlTest {

	public void execute() throws Exception {

		ResourceBundle rb = ResourceBundle.getBundle("database");

		Properties info = new Properties();
		info.put("user", rb.getString("loginUser"));
		info.put("password", rb.getString("password"));

        Connection con = null;

		try {
			con = DriverManager.getConnection(rb.getString("url"), info);

			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement("drop table if exists testdb.sqlTest");
			ps.executeUpdate();

			ps = con.prepareStatement("CREATE TABLE if not exists testdb.sqlTest(id int not null primary key auto_increment, name varchar(50))");
			ps.executeUpdate();

			ps = con.prepareStatement("insert into testdb.sqlTest(name) values ('Mike')");
			ps.executeUpdate();

			//結果を表示する
			ps = con.prepareStatement("select * from testdb.sqlTest");
			var rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("id = " + rs.getInt("id"));
				System.out.println("name = " + rs.getString("name"));
			}

			con.commit();

	    } catch (SQLException e) {
	        e.printStackTrace();
			con.rollback();
			System.out.println("rollback");
	    } finally {
		      try {
		          if ( con != null ) con.close();
		        }
		        catch(Exception e){
		          System.out.println( "Exception! :" + e.toString() );
		          throw new Exception();
		        }
	        System.out.println("処理が完了しました");
	    }
	}
}
