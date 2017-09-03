package pl.coderslab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.dao.ShowDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.User;

/**
 * Servlet implementation class GroupUsersServlet
 */
@WebServlet(name = "/GroupUsersServlet", urlPatterns = {"/GroupUsersServlet"})
public class GroupUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		ShowDao showDao = new ShowDao();
		GroupDao groupDao = new GroupDao();
		List<User> users = userDao.findUsersByGroup(Integer.parseInt(request.getParameter("groupId")));
		List<String[]> list = new ArrayList<>();
		for(User user : users){
			showDao.buildLinesUsers(user, list);
		}
		request.setAttribute("list", list);
		request.setAttribute("name", groupDao.getGroupName(Integer.parseInt(request.getParameter("groupId"))));
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/groupUsers.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
