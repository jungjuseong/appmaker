package com.clbee.appmaker.security;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
