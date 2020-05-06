package com.example.demo.service;

import com.example.demo.entity.User;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TestUserDaoService {
    UserDaoService service = new UserDaoService();

    @Test
    public void testUserList(){
        List<User> list = service.getUserList();
        Assertions.assertTrue(list.size()==3,"초기 사용자는 3명입니다.");
        Assertions.assertEquals(3,list.size(),"초기 사용자는 3명입니다.");
       // Assertions.assertNotEquals(2,list.size()==3,"초기 사용자는 3명입니다.");
    }
    @Test
    public void test사용자정보확인(){
        User user = service.getUserList().get(0);
        Assertions.assertTrue(user.getId()==1);
    }
    @Test
    public void test사용자조회(){
        User user = service.getUser(Integer.valueOf(1));
        assertNotNull(user);
        assertEquals(1,user.getId(),"사용자 Id가 잘못되었습니다.");
    }

}
