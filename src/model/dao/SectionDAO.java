package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Section;
import model.db.DBManager;

public enum SectionDAO implements ISectionDAO {

	SECTION_DAO;
	
	private Connection con;

	SectionDAO(){
		con=DBManager.DB_MANAGER.getConnection();
	}

	@Override
	public List<Section> getAll() throws Exception {
		String sql = "SELECT id, name FROM genders;";
		Statement s = con.createStatement();
		ResultSet result = s.executeQuery(sql);
		List<Section> sections = new ArrayList<>();
		while(result.next()) {
			sections.add(new Section(result.getInt("id"), result.getString("name")));
		}
		return sections;
	}

	

}
