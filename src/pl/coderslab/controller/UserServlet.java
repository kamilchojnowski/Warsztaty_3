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
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "/UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		ShowDao showDao = new ShowDao();
		GroupDao groupDao = new GroupDao();
		SolutionDao solutionDao = new SolutionDao();
		User user = userDao.findUserById(Integer.parseInt(request.getParameter("userId")));
		String[] userStr = showDao.buildLines(user, groupDao.getGroupName(user.getUserGroupId()));
		List<Solution> solutions = solutionDao.findUserSolutions(user);
		List<String[]> list = new ArrayList<>();
		for(Solution solution : solutions){
			showDao.buildLines(solution, list);
		}
		request.setAttribute("user", userStr);
		request.setAttribute("solutions", list);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
