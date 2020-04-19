public class Main {

	public static void main(String[] args) {

		var sqliteTest = new SqliteTest();
		try {
			sqliteTest.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
