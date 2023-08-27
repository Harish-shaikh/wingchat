package wingchat.wingchat.SecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import wingchat.wingchat.Entity.User;
import wingchat.wingchat.Repositorys.UserRepository;

@Component
public class CustumUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.userdetails(username);
        if (user == null) {
            System.out.println("User not found with username: " + username);
        }
        CustumUserDetail custumUserDetail = new CustumUserDetail(user);
        return custumUserDetail;
    }

}