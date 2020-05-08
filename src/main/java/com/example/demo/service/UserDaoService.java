package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
 //implements(class -> interface 상속 ),
 // extends(class ->class 상속)
// interface -> class(X)
// extends(interface -> interface)
@Component
public class UserDaoService  implements IUserService {
    private static List<User> list = new ArrayList<>();

    private static int usersCount;

    static {
        list.add(new User(1,"kenneth",new Date(),"test1","700101-1111111"));
        list.add(new User(2,"Alice",new Date(),"test2","800101-1111111"));
        list.add(new User(3,"Elena",new Date(),"test3","900101-1111111"));
    }

    @Override
    public List<User> getUserList() {
        return list ;
    }

    @Override
    public User getUser(Integer id) {
        for (User user: list) {
            if (id.equals(user.getId())){
                return user;
            }
        }
        return null;
    }

     @Override
     public User createUser(User newUser) {
        if (newUser.getId() == null){
            //newUser.setId(list.size() +1);
            newUser.setId(list.get(list.size()-1).getId() +1);
        }
        list.add(newUser);
         return newUser;
     }

     @Override
     public User modifyUser(User modifyUser) {
         Iterator<User> iterator= list.iterator();
         while (iterator.hasNext()){
             User user =iterator.next();
             if (user.getId() == modifyUser.getId()){
                 user.setName(modifyUser.getName());
                 user.setJoinDate(modifyUser.getJoinDate());
                 user.setPassword(modifyUser.getPassword());
                 user.setSsn(modifyUser.getSsn());
                 return user;
             }
         }
         return null;
     }
      //List -> ordering
     //set -> set ordering , not duplicate
     //map (key, value) not ordering ,duplicate

     @Override
     public User removeUser(Integer id) {
       Iterator<User> iterator= list.iterator();
        while (iterator.hasNext()){
            User user =iterator.next();
            if (user.getId() == id){
                iterator.remove();
            return user;
            }
        }
        return null;
     }

     //    public User save(User user) {
//
//        list.add(user);
//        return user;
//    }
//
//
//    public User deleteById(Integer id) {
//        return null;
//
//    }

//    public User update(Integer id, User user) {
//
//    }
}
