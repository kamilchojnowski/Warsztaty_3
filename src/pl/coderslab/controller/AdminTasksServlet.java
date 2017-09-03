package pl.coderslab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.ShowDao;
import pl.coderslab.dao.TaskDao;
import pl.coderslab.model.Task;

/**
 * Servlet implementation class AdminTasksServlet
 */
@WebServlet("/AdminTasksServlet")
public class AdminTasksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTasksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShowDao showDao = new ShowDao();
		TaskDao taskDao = new TaskDao();
		List<Task> tasks = taskDao.getTasks();
		List<String[]> list = new ArrayList<>();
		for(Task task : tasks){
			showDao.buildLinesAdmin(task, list);
		}
		request.setAttribute("tasks", list);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/adminTasks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
