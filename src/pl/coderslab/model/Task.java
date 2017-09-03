package pl.coderslab.model;

import java.sql.Date;

public class Task {

	private int id;
	private String name;
	private String task;
	private Date date_task;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Date getDate_task() {
		return date_task;
	}
	public void setDate_task(Date date_task) {
		this.date_task = date_task;
	}
}
