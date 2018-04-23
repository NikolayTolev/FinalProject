package model.dao;

import java.util.List;

import model.Section;

public interface ISectionDAO {
	
	public List<Section> getAll() throws Exception;

}
