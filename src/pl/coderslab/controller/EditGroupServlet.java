package pl.coderslab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.model.Group;

/**
 * Servlet implementation class EditGroupServlet
 */
@WebServlet("/EditGroupServlet")
public class EditGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroupDao groupDao = new GroupDao();
		Group group = groupDao.findGroupById(Integer.parseInt(request.getParameter("groupId")));
		Cookie cookie = new Cookie("groupId", request.getParameter("groupId"));
		request.setAttribute("name", group.getName());
		response.addCookie(cookie);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/groupEdit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroupDao groupDao = new GroupDao();
		Cookie[] cookies = request.getCookies();
		int id = 0;
		for(Cookie c : cookies){
			if(c.getName().equals("groupId")){
				id = Integer.parseInt(c.getValue());
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		String name = request.getParameter("name");
		groupDao.editGroup(id, name);
		response.sendRedirect("http://localhost:8080/Warsztaty_3/AdminGroupsServlet");
	}

}
