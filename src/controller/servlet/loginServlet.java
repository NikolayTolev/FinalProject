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

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			UserManager.getInstance().loginUser(username, password);
			request.getSession().setAttribute("username", username);
			request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
		} catch (Exception e) {
			PrintWriter resp = response.getWriter();
			resp.write(e.getMessage());
			request.getRequestDispatcher("login.jsp").include(request, response);
		}
		
	}

}
