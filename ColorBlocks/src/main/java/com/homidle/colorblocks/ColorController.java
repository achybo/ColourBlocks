package com.homidle.colorblocks;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColorController {
    private final AuthInterface authInterface;

    public ColorController(AuthInterface authInterface) {
        this.authInterface = authInterface;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        return new AuthResponse(authInterface.login(request.username(), request.password()));
    }

    @PostMapping("/add")
    public UserResponse add(@Valid @RequestBody AuthRequest request) {
        ColorBlockPojo created = authInterface.add(request.username(), request.password());
        return new UserResponse(created.getUsername());
    }
}
