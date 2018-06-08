package com.example.demo.data;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.data.domain.FileDetails;

@Repository
public class FileDetailsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<FileDetails> all() {
		return jdbcTemplate.query("SELECT * FROM File_Details", new FileDetailsRowMapper());
	}
	
	public boolean add(FileDetails r) {
		if(r == null)
			throw new NullPointerException();
		return jdbcTemplate.update(
			    "INSERT INTO File_Details (title, descrption, creationDate, file_name) VALUES (?, ?, ?, ?)",
			    r.getTitle(), r.getDescrption(), r.getCreationDate(), r.getFileName()
			) > 0;
	}

	static class FileDetailsRowMapper implements RowMapper<FileDetails> {

		@Override
		public FileDetails mapRow(ResultSet resultSet, int i) throws SQLException {
			FileDetails userDetails = new FileDetails();
			userDetails.setId(resultSet.getInt("id"));
			userDetails.setTitle(resultSet.getString("title"));
			userDetails.setDescrption(resultSet.getString("descrption"));
			final Date date = resultSet.getDate("creationDate");
			if(date != null)
				userDetails.setCreationDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			userDetails.setFileName(resultSet.getString("file_name"));
			return userDetails;
		}
	}

}
