package controller.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Post;
import model.dao.PostDAO;

/**
 * Servlet implementation class PictureServlet
 */
@WebServlet("/getPic")
public class PictureServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String user = (String)req.getSession().getAttribute("username");
		System.out.println(user);
		try {
			Collection<Post> posts = PostDAO.POST_DAO.getFreshPosts();
			req.getSession().setAttribute("posts", posts);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		File f = new File("C:\\Users\\HP\\Desktop\\test\\"+user+"-pic.png");
//		try(InputStream is = new FileInputStream(f); OutputStream os = resp.getOutputStream();) {
//			int b = is.read();
//			while(b != -1) {
//				os.write(b);
//				b = is.read();
//			}
//		} 	
	}

}
