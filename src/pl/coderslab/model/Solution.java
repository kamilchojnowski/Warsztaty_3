package pl.coderslab.model;

import java.sql.Date;

public class Solution {
	
	private String solition;
	private int task_id;
	private int user_id;
	private Date date;

	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getSolition() {
		return solition;
	}
	public void setSolition(String solition) {
		this.solition = solition;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString(){
		return String.format("Solution #%d.%d. Date %tF.\nSolution: %s\n", this.task_id, this.user_id, this.date, this.solition);
	}
	
	public String toString(String task, String user){
		return String.format("Solution #%d.%d. Task: %s, Solved by: %s, Date:%t\nSolution: %s\n", this.task_id, this.user_id, task, user, this.date, this.solition);
	}
	

}
