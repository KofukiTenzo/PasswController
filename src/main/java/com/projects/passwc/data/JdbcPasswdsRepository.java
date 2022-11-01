package com.projects.passwc.data;

import com.projects.passwc.Passwds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcPasswdsRepository implements PasswdsRepository{

    private JdbcOperations jdbc;

    @Autowired
    public JdbcPasswdsRepository(JdbcOperations jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public List<Passwds> findRecentPasswds() {
        return jdbc.query(
                "select id, resource_n, passwd, creation_date" +
                        " from Passwds" +
                        " order by creation_date desc limit 20",
                new PasswdsRowMapper());
    }

    @Override
    public List<Passwds> findPasswds(long max, int count) {
        return jdbc.query(
                "select id, resource_n, passwd, creation_date" +
                        " from Passwds" +
                        " where id < ?" +
                        " order by creation_date desc limit 20",
                new PasswdsRowMapper(), max);
    }

    @Override
    public Passwds findOne(long id) {
        return jdbc.queryForObject(
                "select id, resource_n, passwd, creation_date" +
                        " from Passwds" +
                        " where id = ?",
                new PasswdsRowMapper(), id);
    }

    @Override
    public void save(Passwds passwds) {
        jdbc.update(
                "insert into Passwds (resource_n, passwd, creation_date)" +
                        " values (?, ?, ?)",
                passwds.getResource_n(),
                passwds.getPasswd(),
                passwds.getCreation_date());
    }

    private static class PasswdsRowMapper implements RowMapper<Passwds> {
        public Passwds mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Passwds(
                    rs.getLong("id"),
                    rs.getString("resource_n"),
                    rs.getString("passwd"),
                    rs.getDate("creation_date"));
        }
    }
}
