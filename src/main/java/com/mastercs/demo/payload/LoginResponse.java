package com.mastercs.demo.payload;

import java.util.List;

public class LoginResponse {
    private Long id;
    private String username;
    private String email;
    private String accessToken;
    List<String> roles;
    public LoginResponse(Long id, String username, String email, List<String> roles,String accessToken)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.accessToken = accessToken;
    }

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }
    public void setId(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public List<String> getRoles() {
        return roles;
    }

}
