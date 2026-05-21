package com.homidle.colorblocks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorBlockInterface extends JpaRepository<ColorBlockPojo, Long> {
    Optional <ColorBlockPojo> findByUsername(String username);
}
