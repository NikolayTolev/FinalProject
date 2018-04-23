package model.dao;

import java.sql.Connection;

import model.Tag;
import model.db.DBManager;

public enum TagDAO implements ITagDAO {

	TAG_DAO;
	private Connection con;
	
	private TagDAO() {
		con = DBManager.DB_MANAGER.getConnection();
	}

	@Override
	public void saveTag(Tag g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tag getTagById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
