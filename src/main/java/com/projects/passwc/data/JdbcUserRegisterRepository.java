package com.projects.passwc.data;

import com.projects.passwc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcUserRegisterRepository implements UserRegisterRepository {

    private JdbcOperations jdbc;

    @Autowired
    public JdbcUserRegisterRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public User save(User user) {
        jdbc.update(
                "insert into user (username, password, email)" +
                        " values (?, ?, ?)",
                user.getUsername(),
                user.getPassword(),
                user.getEmail());
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return jdbc.queryForObject(
                "select id, username, email, null from user where username = ?",
                new UserRowMapper(),
                username);
    }

    private static class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    null
            );
        }
    }
}
