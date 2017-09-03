package pl.coderslab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.model.Solution;

/**
 * Servlet implementation class Solution
 */
@WebServlet(name = "/SolutionServlet", urlPatterns = { "/SolutionServlet" })
public class SolutionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolutionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SolutionDao solutionDao = new SolutionDao();
		Solution solution = solutionDao.findSolutionById(Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("taskId")));
		request.setAttribute("solution", solution.getSolition());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/solution.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
