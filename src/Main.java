import jdbcapi.repository.UserRepository;
import jdbcapi.model.User;
import jdbcapi.service.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        userService.update(new User(1,null," ",27));

        List<User> all = userService.getAll();
        System.out.println(all);

    }
}
