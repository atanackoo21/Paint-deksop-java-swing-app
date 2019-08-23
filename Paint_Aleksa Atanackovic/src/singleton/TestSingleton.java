package singleton;

public class TestSingleton {

	public static void main(String[] args) {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		DatabaseConnection databaseConnection2 = DatabaseConnection.getInstance();

		System.out.println(databaseConnection);
		System.out.println(databaseConnection2);

		/*
		 * DatabaseConnection databaseConnection3 = new DatabaseConnection();
		 *
		 * System.out.println(databaseConnection3);
		 */

		DatabaseConnectionLazy databaseConnectionLazy = DatabaseConnectionLazy.getInstance();
		DatabaseConnectionLazy databaseConnectionLazy2 = DatabaseConnectionLazy.getInstance();

		System.out.println(databaseConnectionLazy);
		System.out.println(databaseConnectionLazy2);

	}

}
