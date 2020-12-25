package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new User(resultSet.getLong("id"),
                resultSet.getString("username"),
                resultSet.getString("password"));
    };

    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", ROW_MAPPER);
    }

    public User findById(Long id) {

        List<User> temp = jdbcTemplate.query("select * from jpa.users where id = ?",
                new Object[]{id}, ROW_MAPPER);
        return temp.isEmpty() ? null : temp.get(0);
    }

    public User save(User user) {

        if (user.getId() == null && !existsByUsername(user.getUsername())) {
            jdbcTemplate.update("insert into jpa.users (username, password) values (?, ?)",
                    user.getUsername(), user.getPassword());
        } else {

            if (existsByUsername(user.getUsername()) &&
                    findByUsername((user.getUsername())).getId() != user.getId())
                return null;

            jdbcTemplate.update("update jpa.users set username = ?, password = ? where id = ?",
                    user.getUsername(), user.getPassword(), user.getId());
        }
        return findByUsername(user.getUsername());
    }

    public int delete(Long id) {
        return jdbcTemplate.update("delete from jpa.users where id = ?", id);
    }

    public User findByUsernameAndPassword(String username, String password) {

        List<User> temp = jdbcTemplate.query("select * from jpa.users where username = ? " +
                "and password = ?", new Object[]{username, password}, ROW_MAPPER);
        return temp.isEmpty() ? null : temp.get(0);
    }

    public User findByUsername(String username) {

        List<User> temp = jdbcTemplate.query("select * from jpa.users where username = ?",
                new Object[]{username}, ROW_MAPPER);
        return temp.isEmpty() ? null : temp.get(0);
    }

    public boolean existsById(Long id) {
        return findById(id) != null;
    }

    public boolean existsByUsername(String username) {
        return findByUsername(username) != null;
    }

}