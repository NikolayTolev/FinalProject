package model.dao;

import java.util.Collection;

import model.Post;
import model.Section;
import model.Tag;
import model.User;

public interface IPostDAO {

	Collection<Post> getPostsByTag(Tag ... tags) throws Exception;
	Collection<Post> getPostsBySection(Section section) throws Exception;
	Collection<Post> getPostsByOwner(User u) throws Exception;
	Collection<Post> getFreshPosts() throws Exception;
	Collection<Post> getHotPost() throws Exception;
	Collection<Post> getTrendingPost() throws Exception;
    void savePost(Post p);
    void deletePost(Post p);
}