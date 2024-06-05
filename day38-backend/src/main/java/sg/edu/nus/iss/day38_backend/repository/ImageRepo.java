package sg.edu.nus.iss.day38_backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    // SQL string to read from & write to database
    public static final String SQL_INSERT_PICTURE = "insert into pictures (pic_id, content, mime) VALUES (?, ?, ?)";

    public static final String SQL_GET_PICTURE_BY_ID = "select * from pictures where pic_id = ?";
}
