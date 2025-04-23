package com.kelaskoding.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kelaskoding.entity.UserInfo;
import com.kelaskoding.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService{
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserInfo user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userInfoRepository.save(user);
        return "User added successfully";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> user = userInfoRepository.findByName(username);
        if(user.isPresent()){
            return new UserInfoDetails(user.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
