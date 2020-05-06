package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> list = new ArrayList<>();

    private static int usersCount;

    static {
        list.add(new User(1,"kenneth",new Date(),"test1","700101-1111111"));
        list.add(new User(2,"Alice",new Date(),"test2","800101-1111111"));
        list.add(new User(3,"Elena",new Date(),"test3","900101-1111111"));
    }

    public List<User> getUserList() {
        return list ;
    }

    public User getUser(Integer id) {
        for (User user: list) {
            if (id.equals(user.getId())){
                return user;
            }
        }
        return null;
    }

}
