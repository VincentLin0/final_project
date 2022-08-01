//package com.mastercs.demo.services;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.mastercs.demo.bean.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//public class SpringUser implements UserDetails {
//  private static final long serialVersionUID = 1L;
//
//  private static User user;
//  private static Collection<? extends GrantedAuthority> authorities;
//
//  public SpringUser(User user)
//  {
//    SpringUser.user = user;
//    authorities = user.getRoles().stream()
//            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
//            .collect(Collectors.toList());
//  }
//
//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    return authorities;
//  }
//
//  public Long getId() {
//    return user.getId();
//  }
//
//  public String getEmail() {
//    return user.getEmail();
//  }
//
//  @Override
//  public String getPassword() {
//    return user.getPassword();
//  }
//
//  @Override
//  public String getUsername() {
//    return user.getUsername();
//  }
//
//  @Override
//  public boolean isAccountNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isAccountNonLocked() {
//    return true;
//  }
//
//  @Override
//  public boolean isCredentialsNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isEnabled() {
//    return true;
//  }
//
//}
