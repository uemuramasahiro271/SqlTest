public class Main {

	public static void main(String[] args) {

		var mySqlTest = new MySqlTest();
		try {
			mySqlTest.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

//		var sqliteTest = new SqliteTest();
//		try {
//			sqliteTest.execute();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
