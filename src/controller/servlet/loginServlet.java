package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.manager.UserManager;
import model.dao.PostDAO;

@WebServlet("/login")
public class loginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			UserManager.USER_MANAGER.loginUser(username, password);
			request.getSession().setAttribute("username", username);
			request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
		} catch (Exception e) {
			PrintWriter resp = response.getWriter();
			resp.print(e.getMessage());
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
		
	}

}
