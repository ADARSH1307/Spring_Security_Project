package com.ust.Ust_Ptojects.service;

import com.ust.Ust_Ptojects.model.Person;
import com.ust.Ust_Ptojects.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = repository.findByUsername(username);
        return user.map(GroupUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException(username + " Not found"));
    }
}
