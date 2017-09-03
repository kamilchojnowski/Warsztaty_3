package pl.coderslab.dao;

import java.util.List;

import pl.coderslab.model.Group;
import pl.coderslab.model.Solution;
import pl.coderslab.model.Task;
import pl.coderslab.model.User;

public class ShowDao {
		
	public List<String[]> buildLines(Solution solution, Task task, User user, List<String[]> list){
		String[] str = new String[4];
		str[0] = task.getName();
		str[1] = user.getName();
		str[2] = solution.getDate().toString();
		str[3] = "<a href=\"/Warsztaty_3/SolutionServlet?taskId=" + solution.getTask_id() + "&userId=" + solution.getUser_id() + "\">Rozwiazanie</a>";
		list.add(str);
		return list;
	} 
	
	public List<String[]> buildLines(Group group, List<String[]> list){
		String[] str = new String[3];
		str[0] = group.getId() + "";
		str[1] = group.getName();
		str[2] = "<a href=\"/Warsztaty_3/GroupUsersServlet?groupId=" + group.getId() + "\">Uzytkownicy</a>";
		list.add(str);
		return list;
	}
	
	public List<String[]> buildLinesUsers(User user, List<String[]> list){
		String[] str = new String[3];
		str[0] = user.getId() + "";
		str[1] = user.getName();
		str[2] = "<a href=\"/Warsztaty_3/UserServlet?userId=" + user.getId() + "\">Szczegoly</a>";
		list.add(str);
		return list;
	}
	
	public String[] buildLines(User user, String groupName){
		String[] str = new String[5];
		str[0] = user.getId() + "";
		str[1] = user.getName();
		str[2] = user.getEmail();
		str[3] = groupName;
		return str;
	}
	
	public List<String[]> buildLines(Solution solution, List<String[]> list){
		String[] str = new String[3];
		TaskDao taskDao = new TaskDao();
		str[0] = taskDao.findTaskBySolution(solution).getName();
		str[1] = solution.getDate().toString();
		str[2] = "<a href=\"/Warsztaty_3/SolutionServlet?taskId=" + solution.getTask_id() + "&userId=" + solution.getUser_id() + "\">Rozwiazanie</a>";
		list.add(str);
		return list;
	} 
	
	public List<String[]> buildLinesAdmin(Group group, List<String[]> list){
		String[] str = new String[4];
		if(group.getId() == 1){
			str[0] = group.getId() + "";
			str[1] = group.getName();
			list.add(str);
			return list;
		}
		str[0] = group.getId() + "";
		str[1] = group.getName();
		str[2] = "<a href=\"/Warsztaty_3/EditGroupServlet?groupId=" + group.getId() + "\">Edytuj</a>";
		str[3] = "<a href=\"/Warsztaty_3/DeleteGroupServlet?groupId=" + group.getId() + "\">Usun</a>";
		list.add(str);
		return list;
	}
	
	public List<String[]> buildLinesAdmin(User user, List<String[]> list){
		GroupDao groupDao = new GroupDao();
		String[] str = new String[5];
		if(user.getUserGroupId() == 1){
			str[0] = user.getName();
			str[1] = user.getEmail();
			str[2] = "admin";
			str[3] = "<a href=\"/Warsztaty_3/EditUserServlet?userId=" + user.getId() + "\">Edytuj</a>";
			list.add(str);
			return list;
		}
		str[0] = user.getName();
		str[1] = user.getEmail();
		str[2] = groupDao.getGroupName(user.getUserGroupId());
		str[3] = "<a href=\"/Warsztaty_3/EditUserServlet?userId=" + user.getId() + "\">Edytuj</a>";
		str[4] = "<a href=\"/Warsztaty_3/DeleteUserServlet?userId=" + user.getId() + "\">Usun</a>";
		list.add(str);
		return list;
	}
	
	public List<String[]> buildLinesAdmin(Task task, List<String[]> list){
		String[] str = new String[5];
		str[0] = task.getName();
		str[1] = task.getTask();
		str[2] = task.getDate_task().toString();
		str[3] = "<a href=\"/Warsztaty_3/EditTaskServlet?taskId=" + task.getId() + "\">Edytuj</a>";
		str[4] = "<a href=\"/Warsztaty_3/DeleteTaskServlet?taskId=" + task.getId() + "\">Usun</a>";
		list.add(str);
		return list;
	}
}
