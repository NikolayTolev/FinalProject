package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.UserManager;
import model.User;
import util.exceptions.RegisterException;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String pass = request.getParameter("password");
			String pass2 = request.getParameter("password2");
			if (!pass.equals(pass2)) {
				throw new RegisterException("passwords should match");
			}
			
			User user = new User(	request.getParameter("firstName"), request.getParameter("lastName"), 
									request.getParameter("username"), request.getParameter("password"), 
									request.getParameter("email"));
			UserManager.USER_MANAGER.registerUser(user);
			request.getSession().setAttribute("username", user.getUsername());
			request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
		} catch (RegisterException | SQLException e) {
			PrintWriter pr = response.getWriter();
			pr.write(e.getMessage());
			request.getRequestDispatcher("register.jsp").include(request, response);
		}
	}

}
