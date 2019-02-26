package com.ftx.solution.management.example.dao;

import com.ftx.solution.management.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author puan
 * @date 2018-11-20 15:05
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findByUsernameLike(String username);

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    User readByUsername(String username);

    /**
     * 查询在制定时间之后创建的用户
     *
     * @param start 开始时间
     * @return 用户集合
     */
    List<User> getByCreateTimeLessThan(Date start);
}
