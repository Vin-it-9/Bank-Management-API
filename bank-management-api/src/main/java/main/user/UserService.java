package main.user;


import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;


@Singleton
@Transactional
public class UserService {

   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> saveall(List<User> users) {
        return userRepository.saveAll(users);
    }


    public User changepassword(long id, String password) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setPassword(password);
                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }

    public User findbyemail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
         return user;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User update(Long id, User user) {

        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }



}
