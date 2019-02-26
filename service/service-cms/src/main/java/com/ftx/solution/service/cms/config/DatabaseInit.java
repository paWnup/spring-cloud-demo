package com.ftx.solution.service.cms.config;

import com.ftx.solution.service.cms.dao.RoleRepository;
import com.ftx.solution.service.cms.dao.UserRepository;
import com.ftx.solution.service.cms.entity.Role;
import com.ftx.solution.service.cms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author puan
 * @date 2018-12-07 10:22
 **/
//@Component
public class DatabaseInit implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void run(String... strings) {
        if (userRepository.readByUsername("admin") == null) {
            createUser();
        }
        createRole();
    }

    private void createUser() {
        User user = new User();
        user.setRealName("超级管理员");
        user.setUsername("admin");
        user.setPassword("123456");
        userRepository.save(user);
    }

    private void createRole() {
        Role role = new Role();
        role.setRoleName("超级管理员");
        role.setRoleCode("admin");
        roleRepository.save(role);
    }

}
