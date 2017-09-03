package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.model.Group;
import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

public class GroupDao {

	private static final String FIND_ALL_GROUPS_QUERY = "select * from Groups;";
	private static final String FIND_GROUP_NAME_QUERY = "select * from Groups where id = ?;";
	private static final String EDIT_GROUP_QUERY = "update Groups set name = ? where id = ?;" ;
	private static final String DELETE_GROUP_QUERY = "delete from Groups where id = ?;";
	private static final String ADD_GROUP_QUERY = "insert into Groups (name) values (?);" ;
	private static final String FIND_GROUP_BY_ID_QUERY = "select * from Groups where id = ?;";


	public List<Group> loadAll(){
		List<Group> groups = new ArrayList<>();
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_GROUPS_QUERY)){
			try(ResultSet result = statement.executeQuery()){
				while(result.next()){
					Group group = new Group();
					group.setId(result.getInt("id"));
					group.setName(result.getString("name"));
					groups.add(group);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groups;
	}
	
	public String getGroupName(int groupId){
		String name = null;
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_GROUP_NAME_QUERY)){
			statement.setInt(1, groupId);
			try(ResultSet result = statement.executeQuery()){
				result.next();
				name = result.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
	public void editGroup(int groupId, String name){
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(EDIT_GROUP_QUERY);) {
			statement.setString(1, name);
			statement.setInt(2, groupId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

	}
	
	public void deleteGroup(int groupId){
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(DELETE_GROUP_QUERY);) {
			statement.setInt(1, groupId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
	}
	
	public void addGroup(String name){
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(ADD_GROUP_QUERY);) {
			statement.setString(1, name);

			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

	}
	
	public Group findGroupById(int groupId){
		Group group = new Group();
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_GROUP_BY_ID_QUERY)){
			statement.setInt(1, groupId);
			try(ResultSet result = statement.executeQuery()){
				result.next();
				group.setId(result.getInt("id"));
				group.setName(result.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return group;
	}
	
	
	
}
