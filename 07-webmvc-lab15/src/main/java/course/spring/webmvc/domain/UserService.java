package course.spring.webmvc.domain;

import course.spring.webmvc.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(String id);
    User findByUsername(String username);
    User createUser(User user);
    User updateUser(User user);
    User deleteById(String id);
    long count();
}
