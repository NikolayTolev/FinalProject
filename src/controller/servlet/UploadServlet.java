package controller.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.ha.backend.CollectedInfo;

import model.Post;
import model.Section;
import model.dao.PostDAO;
import model.dao.UserDAO;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		Part filePart = request.getPart("file");
		String username = (String)request.getSession().getAttribute("username");
		File f = new File("C:\\Users\\HP\\Desktop\\test\\"+username+"-pic.png");
		if (!f.exists()) {
			f.createNewFile();
		}
		try (OutputStream os = new FileOutputStream(f);){
			InputStream is = filePart.getInputStream();
			int b = is.read();
			while(b != -1) {
				os.write(b);
				b = is.read();
			}
			Post p = new Post(UserDAO.USER_DAO.getUserByUsername(username));
			PostDAO.POST_DAO.savePost(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, resp);
	}

}
