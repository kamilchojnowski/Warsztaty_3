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

public class SolutionDao {
	
	private static final String FIND_ALL_SOLUTIONS_QUERY = "select * from Solutions order by Solutions.date_solution desc;";
	private static final String FIND_LATEST_SOLUTIONS_QUERY = "select * from Solutions order by Solutions.date_solution desc limit ?;";
	private static final String FIND_SOLUTION_BY_ID_QUERY = "select * from Solutions where id_user = ? and id_task = ?;";
	private static final String FIND_USER_SOLUTIONS_QUERY = "select * from Solutions where id_user = ?;";


	
	public List<Solution> loadAll(){
		List<Solution> solutions = new ArrayList<>();
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_SOLUTIONS_QUERY)){
			try(ResultSet result = statement.executeQuery()){
				while(result.next()){
					Solution solution = new Solution();
					solution.setSolition(result.getString("solution"));
					solution.setTask_id(result.getInt("id_task"));
					solution.setUser_id(result.getInt("id_user"));
					solution.setDate(result.getDate("date_solution"));
					solutions.add(solution);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return solutions;
	}
	
	public List<Solution> loadAll(int limit){
		List<Solution> solutions = new ArrayList<>();
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_LATEST_SOLUTIONS_QUERY)){
			statement.setInt(1, limit);
			try(ResultSet result = statement.executeQuery()){
				while(result.next()){
					Solution solution = new Solution();
					solution.setSolition(result.getString("solution"));
					solution.setTask_id(result.getInt("id_task"));
					solution.setUser_id(result.getInt("id_user"));
					solution.setDate(result.getDate("date_solution"));
					solutions.add(solution);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return solutions;
	}
	
	public Solution findSolutionById(int userId, int taskId){
		Solution solution = new Solution();
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_SOLUTION_BY_ID_QUERY)){
			statement.setInt(1, userId);
			statement.setInt(2, taskId);
			try(ResultSet result = statement.executeQuery()){
				result.next();
				solution.setSolition(result.getString("solution"));
				solution.setTask_id(result.getInt("id_task"));
				solution.setUser_id(result.getInt("id_user"));
				solution.setDate(result.getDate("date_solution"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return solution;
	}
	
	public List<Solution> findUserSolutions(User user){
		List<Solution> solutions = new ArrayList<>();
		try(Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_USER_SOLUTIONS_QUERY)){
			statement.setInt(1, user.getId());
			try(ResultSet result = statement.executeQuery()){
				while(result.next()){
					Solution solution = new Solution();
					solution.setSolition(result.getString("solution"));
					solution.setTask_id(result.getInt("id_task"));
					solution.setUser_id(result.getInt("id_user"));
					solution.setDate(result.getDate("date_solution"));
					solutions.add(solution);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return solutions;
	}
	
	

}
