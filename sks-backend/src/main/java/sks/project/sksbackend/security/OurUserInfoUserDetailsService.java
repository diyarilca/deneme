package sks.project.sksbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sks.project.sksbackend.entities.User;
import sks.project.sksbackend.repositoryDataAccess.UserRepository;

import java.util.Optional;

public class OurUserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        return user.map(OurUserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı!"));
    }
}
