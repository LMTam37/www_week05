package vn.edu.iuh.fit.week_05.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import vn.edu.iuh.fit.week_05.models.User;

public interface UserService extends UserDetailsService {
    User save(User user);
}
