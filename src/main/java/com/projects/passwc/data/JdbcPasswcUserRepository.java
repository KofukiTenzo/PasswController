package com.projects.passwc.data;

import com.projects.passwc.PasswcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcPasswcUserRepository implements PasswcUserRepository {

    private JdbcOperations jdbc;

    @Autowired
    public JdbcPasswcUserRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public PasswcUser save(PasswcUser passwcUser) {
        jdbc.update(
                "insert into PasswcUser (username, passwd, email)" +
                        " values (?, ?, ?)",
                passwcUser.getUsername(),
                passwcUser.getPasswd(),
                passwcUser.getEmail());
        return passwcUser;
    }

    @Override
    public PasswcUser findByUsername(String username) {
//        return jdbc.queryForObject(
//                "select id, username from PasswcUser where username = ?",
//                new UserRowMapper(), username);
        return jdbc.queryForObject("select id, username, null, email from PasswcUser where username = ?",
                new UserRowMapper(), username);
    }

    private static class UserRowMapper implements RowMapper<PasswcUser> {
        public PasswcUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PasswcUser(
                    rs.getLong("id"),
                    rs.getString("username"),
                    null,
                    rs.getString("email"));
        }
    }
}
