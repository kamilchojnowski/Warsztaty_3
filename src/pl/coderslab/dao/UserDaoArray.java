package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;
/**
 * Przykład klasy Dao, która wykorzystuje tablice, wraz z metodą do dodawania oraz powiększania tablicy
 *
 */
public class UserDaoArray {

	private static final String SELECT_USERS = "Select * from users";

	public User[] findAll() {
		User[] users = new User[0];

		try (Connection conn = DbUtil.getConn(); PreparedStatement stat = conn.prepareStatement(SELECT_USERS)) {
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setId(rs.getInt("id"));
				users = addToArray(u, users);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Wystąpił błąd");
		}

		return users;
	}

	private User[] addToArray(User u, User[] users) {
		User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
		tmpUsers[users.length] = u;
		return tmpUsers;
	}
}
