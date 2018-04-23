package model.dao;

import model.Tag;

public interface ITagDAO {

	void saveTag(Tag g) throws Exception;
	Tag getTagById(int id) throws Exception;
}
