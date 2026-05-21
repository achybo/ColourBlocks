package com.homidle.colorblocks;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImplAuthInterface implements AuthInterface{

    private ColorBlockInterface colorBlockInterface;
    private ColorJwtUtil colorJwtUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ImplAuthInterface(ColorBlockInterface colorBlockInterface, ColorJwtUtil colorJwtUtil, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.colorBlockInterface = colorBlockInterface;
        this.colorJwtUtil = colorJwtUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String login(String username, String password) {
        ColorBlockPojo colorBlockPojo = colorBlockInterface.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        if (bCryptPasswordEncoder.matches(password, colorBlockPojo.getPassword())){

            return colorJwtUtil.generateToken(username);
        }
        throw new UnauthorizedException("invalid username or password");
    }

    @Override
    public ColorBlockPojo add(String username, String password) {
        if (colorBlockInterface.findByUsername(username).isPresent()) {
            throw new ConflictException("user already exists");
        }
        ColorBlockPojo colorBlockPojo = new ColorBlockPojo();
        colorBlockPojo.setUsername(username);
        colorBlockPojo.setPassword(bCryptPasswordEncoder.encode(password));
        return colorBlockInterface.save(colorBlockPojo);
    }

}
