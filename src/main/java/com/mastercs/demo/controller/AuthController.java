package com.mastercs.demo.controller;


import com.mastercs.demo.bean.EnumRole;
import com.mastercs.demo.bean.Role;
import com.mastercs.demo.bean.User;
import com.mastercs.demo.config.Result;
import com.mastercs.demo.payload.LoginDto;
import com.mastercs.demo.payload.SignupDto;
import com.mastercs.demo.repository.RoleRepository;
import com.mastercs.demo.repository.UserRepository;
import com.mastercs.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;

import static java.awt.SystemColor.window;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class AuthController {

/*  @Autowired
  AuthenticationManager authenticationManager;*/

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;


  @PostMapping("/login")
  public Result<?> authenticateUser(@Valid @RequestBody LoginDto loginRequest, HttpServletRequest request) {

    User userInfo = userRepository.findUserByUsername(loginRequest.getUsername());
    if(userInfo != null){
      //login success
      if (loginRequest.getPassword().equals(userInfo.getPassword())) {
          //create token 
        String token = JwtUtils.generateToken(userInfo);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("id",userInfo.getId());
        map.put("username",userInfo.getUsername());
        map.put("email",userInfo.getEmail());
        //map.put("userQuestion",userInfo.getUserQuestion());
        map.put("roles",userInfo.getRoles());
        map.put("token",token);
        return Result.success(map);
      }else{
        return Result.error("401", "username or password does not match with record");
      }
    }
    //return userInfo and token
    return Result.error("401","login fail");

  }


  @PostMapping("/signup")
  public Result<?> registerUser(@Valid @RequestBody SignupDto signUpRequest) {

    if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
      return Result.error("Error: Email is already in use!");
    }
    if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
      return Result.error("Error: Username is already in use!");
    }

    User user = new User();
    //TODO 密码先用明文
    user.setPassword(signUpRequest.getPassword());
    user.setUsername(signUpRequest.getUsername());
    user.setEmail(signUpRequest.getEmail());

    HashSet<Role> roles = new HashSet<>();
    //default ROLE_USER
    Role role = roleRepository.findByName(EnumRole.ROLE_USER);
    roles.add(role);
    user.setRoles(roles);
    userRepository.save(user);

    return Result.success("signup success");
  }


}
