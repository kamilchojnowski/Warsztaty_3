package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.TaskDao;
import pl.coderslab.model.Task;

/**
 * Servlet implementation class EditTaskServlet
 */
@WebServlet("/EditTaskServlet")
public class EditTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TaskDao taskDao = new TaskDao();
		Task task = taskDao.findTaskById(Integer.parseInt(request.getParameter("taskId")));
		Cookie cookie = new Cookie("taskId", request.getParameter("taskId"));
		response.addCookie(cookie);
		request.setAttribute("name", task.getName());
		request.setAttribute("task", task.getTask());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/taskEdit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TaskDao taskDao = new TaskDao();
		Cookie[] cookies = request.getCookies();
		int id = 0;
		for(Cookie c : cookies){
			if(c.getName().equals("taskId")){
				id = Integer.parseInt(c.getValue());
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		String name = request.getParameter("name");
		String task = request.getParameter("task");
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		taskDao.editTask(id, name, task, date);
		response.sendRedirect("http://localhost:8080/Warsztaty_3/AdminTasksServlet");
	}

}
