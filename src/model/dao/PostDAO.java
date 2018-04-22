package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Post;
import model.Section;
import model.Tag;
import model.User;
import model.db.DBManager;

public enum PostDAO implements IPostDAO{
	
	POST_DAO;
	
	private Connection con;

	PostDAO(){
		con=DBManager.DB_MANAGER.getConnection();
	}

	@Override
	public Collection<Post> getPostsByTag(Tag... tags) throws Exception {
		List<Post> posts= new ArrayList<>();
		StringBuilder tagSQL=new StringBuilder();
		
		for (int i =0; i<tags.length; i++) {
			if(i==tags.length-1) {
				tagSQL.append("?");
			}else {
				tagSQL.append("?,");
			}	
		}
		
		String sql="SELECT post_id FROM posts_have_tags WHERE tag_id IN ("+tagSQL+")";
	    PreparedStatement ps= con.prepareStatement(sql);
	    
	    for (int i = 0; i < tags.length; i++) {
			ps.setInt(i+1,tags[i].getId());
		}
	    
	    ResultSet rs=ps.executeQuery();
	    
	    while(rs.next()) {
	    	Post p= (Post) POST_DAO.getPostsById(rs.getInt("post_id"));
	    	posts.add(p);
	    }
		return posts;
	}

	@Override
	public Collection<Post> getPostsBySection(Section section) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Post> getPostsByOwner(User u) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Post> getFreshPosts() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Post> getHotPost() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Post> getTrendingPost() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePost(Post p) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePost(Post p) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post getPostsById(int id) throws SQLException {
		String sql = "SELECT image_url, title, date, user_id FROM posts WHERE id=? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
	    ResultSet rs = ps.executeQuery();
	    rs.next();
	    User owner = UserDAO.USER_DAO.getUserById(rs.getInt("user_id"));
	    Post p = new Post(owner)
	    		    .imageURL(rs.getString("image_url"))
	    		    .title(rs.getString("title"))
	    		    .date(rs.getTimestamp("date").toLocalDateTime());
	   return p;  
	}

	
}
