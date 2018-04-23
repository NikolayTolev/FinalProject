package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.CommentManager;
import model.Comment;
import model.Post;
import model.User;
import model.dao.CommentDAO;
import model.dao.UserDAO;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		try {
			Post post = (Post) req.getAttribute("post");
			User user = UserDAO.USER_DAO.getUserByUsername((String)req.getSession().getAttribute("username"));
			String content = (String)req.getAttribute("content");
			Comment com = new Comment(post, user, content);
			CommentManager.COMMENT_MANAGER.writeComment(post, com);
		} catch (Exception e) {
			PrintWriter pr = resp.getWriter();
			pr.write(e.getMessage());
			req.getRequestDispatcher("main.jsp").include(req, resp);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Comment comment = (Comment) req.getAttribute("comment");
			CommentDAO.COMMENT_DAO.deleteComment(comment);
		} catch (Exception e) {
			PrintWriter pr = resp.getWriter();
			pr.write(e.getMessage());
			req.getRequestDispatcher("main.jsp").include(req, resp);
		}
	}
}
