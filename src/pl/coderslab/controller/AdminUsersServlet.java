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
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.User;

/**
 * Servlet implementation class AdminUsersServlet
 */
@WebServlet("/AdminUsersServlet")
public class AdminUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShowDao showDao = new ShowDao();
		UserDao userDao = new UserDao();
		List<User> users = userDao.findAll();
		List<String[]> list = new ArrayList<>();
		for(User user : users){
			showDao.buildLinesAdmin(user, list);
		}
		request.setAttribute("users", list);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/adminUsers.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
