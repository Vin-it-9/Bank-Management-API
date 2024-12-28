package User;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.*;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class UserService {

    @Inject
    UserRepository userRepository;

    public User saveuser(User user) {
        userRepository.persist(user);
        return user;
    }


}
