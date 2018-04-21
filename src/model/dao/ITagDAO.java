package model.dao;

import model.Tag;

public interface ITagDAO {

	void saveTag(Tag g);
	Tag getTagById(int id);
}
