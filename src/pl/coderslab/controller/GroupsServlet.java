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
import pl.coderslab.model.Group;

/**
 * Servlet implementation class GroupsServlet
 */
@WebServlet(name = "/GroupsServlet", urlPatterns = { "/GroupsServlet" })
public class GroupsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroupDao groupDao = new GroupDao();
		ShowDao showDao = new ShowDao();
		List<Group> groups = groupDao.loadAll();
		List<String[]> list = new ArrayList<>();
		for(Group group : groups){
			showDao.buildLines(group, list);
		}
		request.setAttribute("groups", list);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/groups.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
