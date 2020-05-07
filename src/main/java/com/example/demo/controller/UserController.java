package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserDaoService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;


import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
//@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDaoService service;



//    @GetMapping("/users")
//    public MappingJacksonValue getUserList() {
//        List<User> list = service.getUserList();
//        List<EntityModel<User>> result = new ArrayList<>();
//        //entitymodel<user>
//        list.forEach(user -> {
//            EntityModel<User> model = new EntityModel<>(user);
//
//            WebMvcLinkBuilder linkTo =
//                    linkTo(methodOn(this.getClass()).getUser(user.getId()));
//
//            model.add(linkTo.withRel("user-detail"));
//
//            result.add(model);
//        });
//
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
//        FilterProvider provider = new SimpleFilterProvider().addFilter("UserInfo", filter);
//
//        MappingJacksonValue mapping = new MappingJacksonValue(result);
//        mapping.setFilters(provider);
//
//        return mapping;
//
//    }
   @GetMapping("/users")
    public List<User> getUserList() {
        List<User> list = service.getUserList();
       return list;

    }


    // /users/1 사용자 ID
    // /users/2
    @GetMapping("users/{id}")
    public MappingJacksonValue getUser(@PathVariable(value = "id") Integer id) {
        User user = service.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate");
        FilterProvider provider = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(provider);

        return mapping;


    }

    @GetMapping("/admin/users/{id}")
    public MappingJacksonValue getUserByAdmin(@PathVariable(value = "id") Integer id) {
        User user = service.getUser(id);

        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider provider = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(provider);

        return mapping;
    }

    // SPRING BOOOT 2.1
    @GetMapping("/hateoas/users/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable(value = "id") Integer id) {
        User user = service.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        EntityModel<User> model = new EntityModel<>(user);

        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).getUserList());

        model.add(linkTo.withRel("all-users"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider provider = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(model);
        mapping.setFilters(provider);

        return mapping;

    }
    @PostMapping("/users")
    public User create(@RequestBody User user){
     return service.save(user);
    }

//    @PutMapping("/users/{id}")
//    public Resource<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
//        User updatedUser = service.update(id, user);
//
//    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable Integer id) {
        User user = service.deleteById(id);
        return user;
    }

}

