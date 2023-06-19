package jdbcapi.repository;

import jdbcapi.error.UserException;
import jdbcapi.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jdbcapi.repository.queries.UserQueries.*;

public class UserRepository {
    private final String URL = "jdbc:mysql://localhost:3306/jdbc";
    private final String USERNAME = "root";
    private final String PASSWORD = "1234";
    public List<User> find() {
        loadDbContext();

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);

                users.add(new User(id, name, surname, age));
            }
            return users;
        } catch (SQLException e) {
            throw new UserException("Exception while find all users, message: " + e.getMessage());
        }


    }
    public void create(User user){
        loadDbContext();

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getSurname());
            preparedStatement.setInt(4,user.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UserException("Exception while create an user, message: " + e.getMessage());
        }
    }

    public Integer getLastIndex(){
        loadDbContext();
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(LAST_INDEX);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new UserException("Exception while find all users, message: " + e.getMessage());
        }
    }

    public void update(User user){
        loadDbContext();

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSurname());
            preparedStatement.setInt(3,user.getAge());
            preparedStatement.setInt(4,user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new UserException("Exception while delete user, message: " + e.getMessage());
        }
    }

    public void delete(User user){
        loadDbContext();
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new UserException("Exception while delete user, message: " + e.getMessage());
        }
    }

    private void loadDbContext(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
    }
}
