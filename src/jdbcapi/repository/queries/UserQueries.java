package jdbcapi.repository.queries;

public interface UserQueries {
    String FIND_ALL = "Select * from users";
    String INSERT = "INSERT INTO users VALUES (?,?,?,?)";
    String LAST_INDEX = "SELECT MAX(id) from users";
    String DELETE = "DELETE FROM USERS WHERE id = ?";
    String UPDATE = "UPDATE users SET name = ?, surname = ?, age = ? where id = ?";

}
