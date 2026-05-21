package com.homidle.colorblocks;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorBlockService{
    private final ColorBlockInterface colorBlockInterface;

    public ColorBlockService(ColorBlockInterface colorBlockInterface) {
        this.colorBlockInterface = colorBlockInterface;
    }
public Optional<ColorBlockPojo> findByUsername(String username){
        return colorBlockInterface.findByUsername(username);
}

}
