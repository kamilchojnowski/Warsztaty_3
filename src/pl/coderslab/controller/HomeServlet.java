package pl.coderslab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.ShowDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.TaskDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Solution;


@WebServlet("/")
public class HomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private String limit;
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		limit = config.getInitParameter("limit");
		System.out.println(limit);
		super.init(config);
	}

    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SolutionDao solutionDao = new SolutionDao();
		TaskDao taskDao = new TaskDao();
		UserDao userDao = new UserDao();
		ShowDao showDao = new ShowDao();
		List<Solution> solutions = solutionDao.loadAll(5);
		List<String[]> list = new ArrayList<>();
		for(Solution solution : solutions){
			showDao.buildLines(solution, taskDao.findTaskBySolution(solution), userDao.findUserBySolution(solution), list);
		}
		request.setAttribute("list", list);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
