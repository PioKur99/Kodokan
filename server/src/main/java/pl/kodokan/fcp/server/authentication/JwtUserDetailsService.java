package pl.kodokan.fcp.server.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.user.repo.UserDataRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserDataRepository userDataRepository;

    public JwtUserDetailsService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDataRepository.findUserDataByEmail(email).orElseThrow(() -> new UsernameNotFoundException("There is no username with email: " + email));
    }
}
