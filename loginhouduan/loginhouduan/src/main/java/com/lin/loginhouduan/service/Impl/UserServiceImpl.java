package com.lin.loginhouduan.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.loginhouduan.entity.User;
import com.lin.loginhouduan.mapper.UserMapper;
import com.lin.loginhouduan.req.UserLoginReq;
import com.lin.loginhouduan.req.UserServeReq;
import com.lin.loginhouduan.resp.UserLoginResp;
import com.lin.loginhouduan.service.UserService;
import com.lin.loginhouduan.utils.CopyUtil;
import com.lin.loginhouduan.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private UserMapper userMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public void register(UserServeReq req) {

        User user = CopyUtil.copy(req, User.class);
        if(ObjectUtils.isEmpty(req.getId())){
            User userDb = selectByLoginName(req.getLoginName());
            if(ObjectUtils.isEmpty(userDb)){
                user.setId(snowFlake.nextId());
                userMapper.insert(user);
            }
        }
    }

    @Override
    public UserLoginResp login(UserLoginReq req) {

        User userDb = selectByLoginName(req.getLoginName());
        if(ObjectUtils.isEmpty(userDb)){
            //用户名不存在
            return null;
        }else {
            //登陆成功
            UserLoginResp userLoginResp = CopyUtil.copy(userDb, UserLoginResp.class);
            return userLoginResp;
        }

    }

    //查询loginName是否被注册
    public User selectByLoginName(String loginName){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getLoginName,loginName);
        List<User> userList = userMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(userList)){
            return null;
        } else{
            return userList.get(0);
        }
    }
}

