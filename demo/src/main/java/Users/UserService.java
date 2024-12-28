package Users;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.*;
import jakarta.transaction.Transactional;

import java.util.*;

@ApplicationScoped
@Transactional
public class UserService {

    @Inject
    UserRepository userRepository;

    public User findUserById(Long id) {
      return  userRepository.findById(id);
    }

    public User deleteUserById(Long id) {
        User user = userRepository.findById(id);
        userRepository.delete(user);
        return user;
    }

    public List<User> findAllUsers() {
        return userRepository.listAll();
    }

    public User findUserById(int id) {
        return userRepository.findById((long) id);
    }


    public User updateOrSaveUser(User user) {
        if (user.getId() == 0) {
            userRepository.persist(user);
            return user;
        } else {
            User existingUser = userRepository.findById((long) user.getId());
            if (existingUser == null) {
                throw new IllegalArgumentException("User with ID " + user.getId() + " does not exist.");
            }

            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            userRepository.getEntityManager().merge(existingUser);
            return existingUser;
        }
    }


}
