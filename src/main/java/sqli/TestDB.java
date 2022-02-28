package sqli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestDB implements AutoCloseable {

	private static final String URL = "jdbc:sqlite:res/db/user.db";
	private static final String QUERY =
		"SELECT id, fname, lname, age FROM users WHERE lname = '%s'";

	private final Connection conn;

	public TestDB() throws SQLException {
		conn = DriverManager.getConnection(URL);
	}

	public List<User> getUsers(String id) {
		try {
			ResultSet rs = executeQuery(id);
			return toUsers(rs);
		}
		catch (SQLException e) {
			return new ArrayList<>();
		}
	}

	private List<User> toUsers(ResultSet rs) throws SQLException {
		List<User> users = new ArrayList<>();
		User user;

		while (rs.next()) {
			users.add(new User(
				rs.getInt("id"),
				rs.getString("fname"),
				rs.getString("lname"),
				rs.getInt("age")));
		}

		return users;
	}

	private ResultSet executeQuery(String id) throws SQLException {
		String sql = createQuery(id);
		Statement stmt = conn.createStatement();
		return stmt.executeQuery(sql);
	}

	private String createQuery(String id) {
		return String.format(Locale.US, QUERY, id);
	}

	@Override
	public void close() throws SQLException {
		if (conn == null) {
			return;
		}

		conn.close();
	}

}
