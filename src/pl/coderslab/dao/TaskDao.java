package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.model.Solution;
import pl.coderslab.model.Task;
import pl.coderslab.utils.DbUtil;

public class TaskDao {
	
	private static final String FIND_ALL_TASKS_QUERY = "select * from Tasks;";
	private static final String FIND_TASK_BY_SOLUTION_QUERY = "select * from Tasks where id =?;";
	private static final String ADD_TASK_QUERY = "insert into Tasks (name, task, date_task) values (?, ?, ?);" ;
	private static final String FIND_TASK_BY_ID_QUERY = "select * from Tasks where id = ?;";
	private static final String EDIT_TASK_QUERY = "update Tasks set name = ?, task = ?, date_task = ? where id = ?;" ;
	private static final String DELETE_TASK_QUERY = "delete from Tasks where id = ?;";
	
	public List<Task> getTasks(){
		List<Task> tasks = new ArrayList<>();
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_TASKS_QUERY)){
			try(ResultSet result = statement.executeQuery()){
				while(result.next()){
					Task task = new Task();
					task.setId(result.getInt("id"));
					task.setName(result.getString("name"));
					task.setTask(result.getString("task"));
					task.setDate_task(result.getDate("date_task"));
					tasks.add(task);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tasks;
	}
	
	public Task findTaskBySolution(Solution solution){
		Task task = new Task();
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_TASK_BY_SOLUTION_QUERY)){
			statement.setInt(1, solution.getTask_id());
			try(ResultSet result = statement.executeQuery()){
				result.next();
				task.setId(result.getInt("id"));
				task.setName(result.getString("name"));
				task.setTask(result.getString("task"));
				task.setDate_task(result.getDate("date_task"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}
	
	public void addTask(String name, String task, Date date){
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(ADD_TASK_QUERY);) {
			statement.setString(1, name);
			statement.setString(2, task);
			statement.setDate(3, date);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

	}
	
	public Task findTaskById(int taskId){
		Task task = new Task();
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_TASK_BY_ID_QUERY)){
			statement.setInt(1, taskId);
			try(ResultSet result = statement.executeQuery()){
				result.next();
				task.setId(result.getInt("id"));
				task.setName(result.getString("name"));
				task.setTask(result.getString("task"));
				task.setDate_task(result.getDate("date_task"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}
	
	public void editTask(int taskId, String name, String task, Date date){
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(EDIT_TASK_QUERY);) {
			statement.setString(1, name);
			statement.setString(2, task);
			statement.setDate(3, date);
			statement.setInt(4, taskId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

	}
	
	public void deleteTask(int taskId){
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(DELETE_TASK_QUERY);) {
			statement.setInt(1, taskId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
	}

}
