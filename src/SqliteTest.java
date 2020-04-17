import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteTest {

	public void execute() throws Exception{

		createDBFolder();

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

	public void transactionSample() throws Exception {

		createDBFolder();

		Connection conn = null;

		try {

			// DBファイルに接続する (ファイルがなければ作成される)
			conn = DriverManager.getConnection("jdbc:sqlite:database/Employee.db");

			// 自動コミットモードをオフにする (オンの場合はSQL実行時に自動でコミットされる)
			conn.setAutoCommit(false);

			//テーブル作成
			var sql = "create table if not exists employee( id integer primary key autoincrement, name string, age integer )";
			var stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();

			// レコード挿入
			stmt = conn.prepareStatement("insert into employee( name, age ) values ( '太郎', 20 )");
			stmt.executeUpdate();

			stmt = conn.prepareStatement("insert into employee( name, age ) values ( ?, ? )");
			stmt.setString(1, "次郎");	// 1つ目の「?」に値を代入
			stmt.setInt(2, 16);			// 2つ目の「?」に値を代入
			stmt.executeUpdate();

			// レコードを削除
			stmt = conn.prepareStatement("delete from employee where name = '次郎'");
			stmt.executeUpdate();

			stmt = conn.prepareStatement("delete from employee where name = ?");
			stmt.setString(1, "次郎");
			stmt.executeUpdate();

			// レコードを更新
			stmt = conn.prepareStatement("update employee set name = '一郎' where name = '太郎'");
			stmt.executeUpdate();

			//結果を表示する
			stmt = conn.prepareStatement("select * from employee");
			var rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println("id = " + rs.getInt("id"));
				System.out.println("name = " + rs.getString("name"));
				System.out.println("age = " + rs.getInt("age"));
			}

			// コミットする
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println("rollback");
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

	private void createDBFolder() {
		var newdir = new File("database");
		newdir.mkdir();
	}
}
