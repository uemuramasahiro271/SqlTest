import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class MySqlTest {

	public void execute() {

		ResourceBundle rb = ResourceBundle.getBundle("database");

		Path path = Paths.get("bin\\Test.txt");
		String text = "";

//		try {
//			text = Files.readString(file);
//		} catch (IOException e1) {
//			// TODO 自動生成された catch ブロック
//			e1.printStackTrace();
//		}

//		try {
//		    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
//		    lines.forEach(line -> {
//		    	System.out.println(line);
//	    	});
//
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}

		try {
		    text = Files.readString(path);
		    System.out.println(text);

		} catch (IOException e) {
		    e.printStackTrace();
		}

		Properties info = new Properties();
		info.put("user", rb.getString("loginUser"));
		info.put("password", rb.getString("password"));

        //text = String.format("CREATE TABLE IF NOT EXISTS %s", "testdb.SqlTest");

//		List<String> list = new  ArrayList<String>();
//		list.add("CREATE TABLE if not exists testdb.sqlTest(ID int not null primary key, Name varchar(50))");
//		list.add("CREATE TABLE if not exists testdb.sqlTest(ID int not null primary key, Name varchar(50))");

        text = "CREATE TABLE if not exists testdb.sqlTest(ID int not null primary key, Name varchar(50)) insert into testdb.sqlTest (ID, Name) values (? , ?)";

		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(rb.getString("url"), info);

			String sql = "CREATE TABLE if not exists testdb.sqlTest(ID int not null primary key, Name varchar(50))";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();

			sql = "insert into testdb.sqlTest values (7 , 'hh')";
			ps = con.prepareStatement(sql);
//			ps.setInt(1, 1);
//			ps.setString(2, "aa");
			ps.execute(sql);



	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        System.out.println("処理が完了しました");
	    }
	}
}
