package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

public class UserDao {

	// ZAPYTANIA SQL
	private static final String READ_USER_QUERY = "Select * from users where id = ?";
	private static final String UPDATE_USER_QUERY = "UPDATE	users SET name = ? , surname = ?, email = ?, password = ?, user_group_id =? WHERE	id = ?";
	private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM Users";

	static private final String FIND_ALL_USER_BELONG_TO_USER_GROUP = "SELECT * FROM users, user_group WHERE user_group.id = "
			+ "users.user_group_id AND user_group.id = ? ORDER	BY users.id ASC";

	//ZAPYTANIA DO OPROGRAMOWANIA
	static private final String FIND_ALL_USER_WHO_MADE_EXCERCISE = "SELECT * FROM users, solution WHERE solution.user_id = "
			+ "users.id AND solution.excercise_id = ? ORDER	BY	solution.created ASC";

	static private final String FIND_ALL_USER_USER_GROUP = "SELECT * FROM users LEFT JOIN user_group ON users.user_group_id = "
			+ "user_group.id  ORDER	BY	user_group.id ASC";

	private static final String READ_USER_BY_EMAIL_QUERY = "Select * from users where email = ?";
	private static final String FIND_USER_BY_SOLUTION_QUERY = "select * from Users where id = ?;";
	private static final String FIND_USERS_BY_GROUP_QUERY = "select * from Users where id_group = ?;";
	private static final String FIND_USER_BY_ID_QUERY = "select * from Users where id = ?;";
	private static final String ADD_USER_QUERY = "insert into Users (name, email, password, id_group) VALUES (?,?,?,?)";
	private static final String EDIT_USER_QUERY = "update Users set name = ?, email = ?, password = ?, id_group = ? where id = ?;" ;
	private static final String DELETE_USER_QUERY = "delete from Users where id = ?;";



	/**
	 * Read user by email address
	 * 
	 * @param email
	 * @return
	 */
	public User readByEmail(String email) {
		User user = new User();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(READ_USER_BY_EMAIL_QUERY);) {
			statement.setString(1, email);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					user.setId(resultSet.getInt("id"));
					user.setName(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.readPassword(resultSet.getString("password"));
					user.setUserGroupId(resultSet.getInt("id_group"));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return user;

	}

	// KONSTRUKTOR
	public UserDao() {
	}

	// DODANIE NOWEGO UZYTKOWNIKA


	// POBIERANIE USERA PO ID
	public User read(Integer userId) {
		User user = new User();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(READ_USER_QUERY);) {
			statement.setInt(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					user.setId(resultSet.getInt("id"));
					user.setName(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.setPassword(resultSet.getString("password"));
					user.setUserGroupId(resultSet.getInt("user_group_id"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return user;

	}

	// USUWANIE USERA PO ID
	public void delete(Integer userId) {
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);) {
			statement.setInt(1, userId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
	}

	// LISTA WSZYSTKICH UZYTKOWNIKOW
	public List<User> findAll() {
		List<User> userList = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_QUERY);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				User userToAdd = new User();
				userToAdd.setId(resultSet.getInt("id"));
				userToAdd.setName(resultSet.getString("name"));
				userToAdd.setEmail(resultSet.getString("email"));
				userToAdd.setPassword(resultSet.getString("password"));
				userToAdd.setUserGroupId(resultSet.getInt("id_group"));
				userList.add(userToAdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return userList;

	}

	// UPDATE
	public void update(User user) {
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);) {
			statement.setInt(6, user.getId());
			statement.setString(1, user.getName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getUserGroupId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

	}

	public List<User> findAllUserBelongToUserGruop(Integer userGroupId) {
		List<User> list = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_BELONG_TO_USER_GROUP);) {
			statement.setInt(1, userGroupId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {

					// Wczytanie uzytkownika
					User user = new User();
					user.setId(resultSet.getInt("users.id"));
					user.setName(resultSet.getString("users.name"));
					user.setEmail(resultSet.getString("users.email"));
					user.setUserGroupId(resultSet.getInt("users.user_group_id"));

					// Dodanie uzytkownika do listy
					list.add(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return list;

	}
	
	public User findUserBySolution(Solution solution){
		User user = new User();
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_SOLUTION_QUERY)){
			statement.setInt(1, solution.getUser_id());
			try(ResultSet result = statement.executeQuery()){
				result.next();
				user.setId(result.getInt("id"));
				user.setName(result.getString("name"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setUserGroupId(result.getInt("id_group"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> findUsersByGroup(int gruopId){
		List<User> users = new ArrayList<>();
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_USERS_BY_GROUP_QUERY)){
			statement.setInt(1, gruopId);
			try(ResultSet result = statement.executeQuery()){
				while(result.next()){
					User user = new User();
					user.setId(result.getInt("id"));
					user.setName(result.getString("name"));
					user.setEmail(result.getString("email"));
					user.setPassword(result.getString("password"));
					user.setUserGroupId(result.getInt("id_group"));
					users.add(user);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return users;
	}
	
	public User findUserById(int userId){
		User user = new User();
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID_QUERY)){
			statement.setInt(1, userId);
			try(ResultSet result = statement.executeQuery()){
				result.next();
				user.setId(result.getInt("id"));
				user.setName(result.getString("name"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setUserGroupId(result.getInt("id_group"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public void addUser(String name, String email, String password, int groupId) {
			try (Connection connection = DbUtil.getConn();
					PreparedStatement statement = connection.prepareStatement(ADD_USER_QUERY);) {
				statement.setString(1, name);
				statement.setString(2, email);
				statement.setString(3, password);
				statement.setInt(4, groupId);
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Cos sie nie powiodło");
			}

		}
	
	public void editUser(int userId, String name, String email, String password, int groupId){
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(EDIT_USER_QUERY);) {
			statement.setString(1, name);
			statement.setString(2, email);
			statement.setString(3, password);
			statement.setInt(4, groupId);
			statement.setInt(5, userId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

	}
	
	public void deleteUser(int userId){
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);) {
			statement.setInt(1, userId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
	}

}
