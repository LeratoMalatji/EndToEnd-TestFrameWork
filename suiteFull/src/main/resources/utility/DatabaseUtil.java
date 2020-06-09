package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseUtil {

	private static Logger log = LogManager.getLogger(DatabaseUtil.class.getName());

	public static Connection getConnection() {
		
		String url =null;
		String userName = null;
		String password = null;

		Properties properties = new Properties();

		FileInputStream input = null;
		
		try {
			input = new FileInputStream(new File(
					System.getProperty("user.dir") + "/src/main/resources/com/TestFrame/suiteFull/data.properties"));
			
		} catch (FileNotFoundException e1) {
			log.error("property file could not be be found ");
			e1.printStackTrace();
		}

		try {
			properties.load(input);
			log.info("loading property file needed for database input values");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		url =properties.getProperty("url");
	    userName = properties.getProperty("userName");
		password = properties.getProperty("password");

		Connection con = null;

		try {
			con = DriverManager.getConnection(url, userName, password);
			log.info("Sucessfully connected to database");
		} catch (SQLException e) {
			
			log.error("Could not connect to the database");
			e.printStackTrace();
		}

		return con;
	}

}
