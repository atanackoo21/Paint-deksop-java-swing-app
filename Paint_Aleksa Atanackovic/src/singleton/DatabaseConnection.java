package singleton;

public class DatabaseConnection {
	private static DatabaseConnection instance = new DatabaseConnection();

	public static DatabaseConnection getInstance() {
		return instance;
	}

	private DatabaseConnection() {

	}
}
