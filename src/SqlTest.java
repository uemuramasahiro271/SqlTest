import java.sql.SQLException;

public interface SqlTest {

	void connect(String dbName) throws SQLException;

	void disconnect() throws SQLException;

	void execute(String[] sqlList) throws SQLException;

	void select(String sql) throws SQLException;

}
