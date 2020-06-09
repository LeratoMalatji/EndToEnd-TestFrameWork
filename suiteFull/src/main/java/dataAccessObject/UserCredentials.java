package dataAccessObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import plainOldJavaObjects.User;
import utility.DatabaseUtil;

public class UserCredentials {

	private static Logger log = LogManager.getLogger(UserCredentials.class.getName());
	
	public List<User> getCredentials() {

		User user;
		List<User> users = new ArrayList<>();

		try (Connection con = DatabaseUtil.getConnection(); Statement s = con.createStatement();) {

			try (ResultSet rs = s.executeQuery("select * from EmployeeInfo");)

			{
				log.info("Quering the database");

				while (rs.next()) {

					user = new User(rs.getString("email"), rs.getString("passwords"));
					users.add(user);
				}
			}

		} catch (SQLException e) {

			log.error("Something went wrong while quering the database");
			e.printStackTrace();
		}

		log.info("Quering returning database results");

		return users;
	}

}
