package com.bellintegrator.myStub.repository;
import com.bellintegrator.myStub.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;

@Repository
public class UserRepository {

    private String jdbcurl = "jdbc:postgresql://127.0.0.1:5432/users";
//    private String jdbcurl = "jdbc:postgresql://postgres:5432/users";
    private String userName = "admin";
    private String userLogin = "pass";

    public User selectUser (String login) throws SQLException {
        User user = new User();
        Statement statement = null;
        Connection connection;
        String request = "SELECT * FROM users u join auth a on u.login=a.login WHERE u.login='%s';";
        try {
            connection = DriverManager.getConnection(jdbcurl, userName, userLogin );
            statement = connection.createStatement();
            String select = String.format(request, login);
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setDate(LocalDate.parse(resultSet.getString("date")));
                user.setEmail(resultSet.getString("email"));
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if(statement != null){
                statement.close();
            }
        }
        return user;
    }

    public String insertUser(User user) {
        int count = 0;
        String request = "INSERT INTO users (login, email) VALUES(?, ?);" +
                "INSERT INTO auth (login, password, date) VALUES(?, ?, ?);";
        try (Connection connection = DriverManager.getConnection(jdbcurl, userName, userLogin);
                PreparedStatement statement = connection.prepareStatement(request);
               ){
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setDate(5, Date.valueOf(LocalDate.now()));
            count = statement.executeUpdate();
            return "updated rows " + count;
        } catch (SQLException e) {

            return e.getMessage();
        }
    }


}
