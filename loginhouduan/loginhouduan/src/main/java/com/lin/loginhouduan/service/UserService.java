package com.lin.loginhouduan.service;

import com.lin.loginhouduan.req.UserLoginReq;
import com.lin.loginhouduan.req.UserServeReq;
import com.lin.loginhouduan.resp.UserLoginResp;

public interface UserService {
     void register(UserServeReq req);

     UserLoginResp login(UserLoginReq req);
}
