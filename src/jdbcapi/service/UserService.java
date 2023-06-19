package jdbcapi.service;

import jdbcapi.model.User;
import jdbcapi.repository.UserRepository;

import java.util.List;
import java.util.Objects;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public List<User> getAll(){
        return userRepository.find();
    }
    public void create(User user){
        if (Objects.nonNull(user.getName()) || Objects.nonNull(user.getSurname()) || Objects.nonNull(user.getAge())){
            System.out.println("Name or surname or age arguments can not be empty");
            return;
        }
        if (Objects.isNull(user.getName()) || Objects.isNull(user.getSurname())){
            System.out.println("Name or surname arguments can not be empty");
            return;
        }
        Integer lastIndex = userRepository.getLastIndex();
        user.setId(lastIndex+1);
        userRepository.create(user);
    }
    public void delete(User user){
        userRepository.delete(user);
    }

    public void update(User user){
        if (Objects.nonNull(user.getName()) || Objects.nonNull(user.getSurname()) || Objects.nonNull(user.getAge())){
            System.out.println("Name or surname or age arguments can not be empty");
            return;
        }
        if (Objects.isNull(user.getName()) || Objects.isNull(user.getSurname()) || Objects.isNull(user.getAge())){
            System.out.println("Name or surname or age arguments can not be null");
            return;
        }

        userRepository.update(user);
    }
}
