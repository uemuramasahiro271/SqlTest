public class Main {

	public static void main(String[] args) {

//		var mySqlTest = new MySqlTest();
//		try {
//			mySqlTest.execute();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		var sqliteTest = new SqliteTest();
		try {
			//sqliteTest.sample();
			sqLiteExecute(sqliteTest);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void sqLiteExecute(SqliteTest sqliteTest) throws Exception {

		sqliteTest.connect("SqlTest");

		var sqlList = new String[] {
			"create table if not exists Department(departmentNo integer primary key autoincrement, departmentName varchar(50));",
			"insert into Department(departmentName) values('部署1');",
			"insert into Department(departmentName) values('部署2');",
			"create table if not exists Position(code integer primary key autoincrement, positionName varchar(50));",
			"insert into Position(positionName) values('部長');",
			"insert into Position(positionName) values('課長');",
			"create table if not exists Employee(employeeNo integer primary key autoincrement, name varchar(50), positionCode integer, age integer, foreign key(positionCode) references Position(code));",
			"insert into Employee(name, positionCode, age) values('一郎', 1, 1);",
			"insert into Employee(name, positionCode, age) values('二郎', 2, 2);",
			"create table if not exists Staff(id integer primary key autoincrement, departmentNo integer, employeeNo integer, foreign key(departmentNo) references Department(departmentNo), foreign key(employeeNo) references Employee(employeeNo));",
			"insert into Staff(departmentNo, employeeNo) values(1, 2);",
			"insert into Staff(departmentNo, employeeNo) values(2, 2);",
		};

		sqliteTest.execute(sqlList);

		sqliteTest.select("select * from Employee;");

		sqliteTest.disConnect();

		System.out.println("完了");

	}

}
