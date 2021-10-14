package ru.university.app.university.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.university.app.university.models.UserUniversity;
import ru.university.app.university.repo.UsersRepo;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepo usersRepo;

    @Autowired
    public UserDetailsServiceImpl(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserUniversity userUniversity = usersRepo.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(userUniversity);
    }
}
