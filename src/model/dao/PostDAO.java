package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public Collection<Post> getPostsBySection(Section section) throws SQLException {
		List<Post> posts= new ArrayList<>();
		String sql = "SELECT image_url, title, date, user_id FROM posts WHERE section_id=? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, section.getId());
	    ResultSet rs = ps.executeQuery();
	    
	    while(rs.next()) {
	    	User owner = UserDAO.USER_DAO.getUserById(rs.getInt("user_id"));
		    Post p = new Post(owner)
		    		    .imageURL(rs.getString("image_url"))
		    		    .title(rs.getString("title"))
		    		    .date(rs.getTimestamp("date").toLocalDateTime());
		    posts.add(p);
        }  
	    
	    return posts;  
	}

	@Override
	public Collection<Post> getPostsByOwner(User u) throws SQLException {
		List<Post> posts= new ArrayList<>();
		String sql = "SELECT image_url, title, date, user_id FROM posts WHERE user_id=? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, u.getId());
	    ResultSet rs = ps.executeQuery();
	    
	    while(rs.next()) {
	    	User owner = UserDAO.USER_DAO.getUserById(rs.getInt("user_id"));
		    Post p = new Post(owner)
		    		    .imageURL(rs.getString("image_url"))
		    		    .title(rs.getString("title"))
		    		    .date(rs.getTimestamp("date").toLocalDateTime());
		    posts.add(p);
        }  
	    
	    return posts;  
	}

	@Override
	public Collection<Post> getFreshPosts() throws SQLException {
		List<Post> posts= new ArrayList<>();
		String sql = "SELECT image_url, title, date, user_id FROM posts ORDERE BY date DESC ";
		Statement s = con.createStatement();
	    ResultSet rs = s.executeQuery(sql);
	    
	    while(rs.next()) {
	    	User owner = UserDAO.USER_DAO.getUserById(rs.getInt("user_id"));
		    Post p = new Post(owner)
		    		    .imageURL(rs.getString("image_url"))
		    		    .title(rs.getString("title"))
		    		    .date(rs.getTimestamp("date").toLocalDateTime());
		    posts.add(p);
        }  
	    
	    return posts;  
	}

	@Override
	public Collection<Post> getHotPost() throws SQLException {
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
	    String sql="INSERT INTO posts (image_url,title,user_id,section_id) VALUES (?,?,?,?) ";
		PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, p.getImageURL());
		ps.setString(2, p.getTitle());
		ps.setInt(3, p.getOwner().getId());
		ps.setInt(4, p.getSection().getId());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		p.id(rs.getInt("id"));
        
		//setting the tags
		for (int i = 0; i < p.getTags().size(); i++) {
			sql = "INSERT INTO posts_have_tags (post_id,tag_id) VALUES (?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, p.getId());
			ps.setInt(1, p.getTags().get(i).getId());
			ps.executeUpdate();
		}
		
	}

	@Override
	public void deletePost(Post p) throws SQLException {
		String sql = "DELETE FROM posts where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, p.getId());
	    ps.executeUpdate();
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

	@Override
	public void votePost(User u, Post p, int vote) throws Exception {
		String sql="UPDATE posts_have_votes SET vote=? WHERE post_id=? AND user_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, vote);
		ps.setInt(2, p.getId());
		ps.setInt(3, u.getId());
		
		if(ps.executeUpdate()==0) {
			sql="INSERT INTO posts_have_votes (user_id,post_id,vote) VALUES (?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, vote);
			ps.setInt(2, p.getId());
			ps.setInt(3, u.getId());
			ps.executeUpdate();
		}
	}

	
}
