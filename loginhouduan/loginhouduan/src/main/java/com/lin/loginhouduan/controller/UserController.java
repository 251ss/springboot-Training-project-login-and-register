package com.lin.loginhouduan.controller;


import com.lin.loginhouduan.req.UserLoginReq;
import com.lin.loginhouduan.req.UserServeReq;
import com.lin.loginhouduan.resp.CommonResp;
import com.lin.loginhouduan.resp.UserLoginResp;
import com.lin.loginhouduan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public CommonResp register(@RequestBody UserServeReq req){

        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        userService.register(req);
        return resp;
    }

    @PostMapping("login")
    public CommonResp login(@RequestBody  UserLoginReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
       UserLoginResp userLoginResp =  userService.login(req);
       resp.getContent(userLoginResp);
       return resp;
    }

}
