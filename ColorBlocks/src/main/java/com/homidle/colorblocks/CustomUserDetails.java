package com.homidle.colorblocks;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetails implements UserDetailsService {
    private ColorBlockInterface colorBlockInterface;

    public CustomUserDetails(ColorBlockInterface colorBlockInterface) {
        this.colorBlockInterface = colorBlockInterface;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ColorBlockPojo colorBlockPojo = colorBlockInterface.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
        return new org.springframework.security.core.userdetails.User(
            colorBlockPojo.getUsername(),
            colorBlockPojo.getPassword(),
            new ArrayList<>()
        );
    }
}
