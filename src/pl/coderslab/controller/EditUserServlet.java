package pl.coderslab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.UserDao;
import pl.coderslab.model.User;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		User user = userDao.findUserById(Integer.parseInt(request.getParameter("userId")));
		Cookie cookie = new Cookie("userId", request.getParameter("userId"));
		response.addCookie(cookie);
		request.setAttribute("name", user.getName());
		request.setAttribute("email", user.getEmail());
		request.setAttribute("password", user.getPassword());
		request.setAttribute("groupId", user.getUserGroupId());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/userEdit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		Cookie[] cookies = request.getCookies();
		int id = 0;
		for(Cookie c : cookies){
			if(c.getName().equals("userId")){
				id = Integer.parseInt(c.getValue());
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int groupId = Integer.parseInt(request.getParameter("groupId"));
		userDao.editUser(id, name, email, password, groupId);;
		response.sendRedirect("http://localhost:8080/Warsztaty_3/AdminUsersServlet");
	}

}
