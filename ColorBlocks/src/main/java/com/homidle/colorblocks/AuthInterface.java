package com.homidle.colorblocks;

public interface AuthInterface {
    String login(String username, String password);
    ColorBlockPojo add(String username, String password);

}
