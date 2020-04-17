public class Main {

	public static void main(String[] args) {

		var sqliteTest = new SqliteTest();
		try {
			//sqliteTest.execute();
			sqliteTest.transactionSample();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
