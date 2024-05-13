package store.DAL;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Db {
	private static Connection cn = null;

	public static Connection connect() {
		try {
			if (cn == null) {
				Class.forName("org.postgresql.Driver");
//				Properties p = new Properties();
//				FileReader f = new FileReader("D:\\web\\store\\src\\main\\java\\dal\\Db.properties");
//				p.load(f);
				String URL = "jdbc:postgresql://localhost:5432/plf_training";
				String UNAME = "postgres";
				String PWD = "1234";
//				cn = DriverManager.getConnection(p.getProperty("URL"), p.getProperty("UNAME"), p.getProperty("PWD"));
				cn = DriverManager.getConnection(URL, UNAME, PWD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
}
