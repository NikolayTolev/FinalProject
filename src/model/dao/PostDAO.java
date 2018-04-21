package model.dao;

import java.sql.Connection;
import java.util.Collection;

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
		// TODO Auto-generated method stub
		return null;
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
	public Collection<Post> getTrendingPost() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePost(Post p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePost(Post p) {
		// TODO Auto-generated method stub
		
	}

	
}
