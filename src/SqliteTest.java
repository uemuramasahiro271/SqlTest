import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteTest {

	public void execute() throws Exception{

		var newdir = new File("database");
		newdir.mkdir();

		Connection conn = null;

		try {

			// DBファイルに接続する(ファイルがなければ作成される)
			conn = DriverManager.getConnection("jdbc:sqlite:database/test.db");

			//テーブル作成
			var sql = "create table if not exists test1( name string, age integer )";
			var stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();

			//値を入力する
			sql = "insert into test1 values ( ?, ? )";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "太郎");
			stmt.setInt(2, 16);
			stmt.executeUpdate();

			sql = "insert into test1 values ( ?, ? )";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "次郎");
			stmt.setInt(2, 18);
			stmt.executeUpdate();

			//結果を表示する
			sql = "select * from test1";
			stmt = conn.prepareStatement(sql);
			var rs = stmt.executeQuery();
			//var rs = stmt.executeQuery("select * from test1");
			while(rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("age"));
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		finally {
	      try {
	          if ( conn != null ) conn.close();
	        }
	        catch(Exception e){
	          System.out.println( "Exception! :" + e.toString() );
	          throw new Exception();
	        }
		}
	}
}
