package com.ftx.solution.service.cms.service;

import com.ftx.solution.common.base.exception.BusinessException;
import com.ftx.solution.service.cms.dao.UserRepository;
import com.ftx.solution.service.cms.entity.User;
import com.ftx.solution.service.cms.vo.CreateUserVo;
import com.ftx.solution.service.cms.vo.GetUserVo;
import com.ftx.solution.service.cms.vo.UpdateUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author puan
 * @date 2018-11-20 15:11
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 创建用户
     *
     * @param userVo 用户值对象
     * @return 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    public Long create(CreateUserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 创建用户
     *
     * @param user 用户对象
     * @return 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    public Long create(User user) {
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 根据ID查找用户
     *
     * @param id 用户ID
     * @return 用户
     */
    public GetUserVo getUserById(Long id) {
        User user = userRepository.getOne(id);
        if (user == null) {
            throw new BusinessException("用户不存在！");
        }
        GetUserVo userVo = new GetUserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    /**
     * 根据ID查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    public GetUserVo getUserByUsername(String username) {
        User user = userRepository.findByUsernameLike(username);
        if (user == null) {
            throw new BusinessException("用户不存在！");
        }
        GetUserVo userVo = new GetUserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    /**
     * 根据ID删除用户
     *
     * @param id 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserById(Long id) {
        userRepository.delete(id);
    }

    /**
     * 根据ID更新用户信息
     *
     * @param userVo 用户值对象
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateUserById(UpdateUserVo userVo) {
        User user = userRepository.getOne(userVo.getId());
        if (user == null) {
            throw new BusinessException("用户不存在！");
        }
        BeanUtils.copyProperties(userVo, user, "id", "updateTime", "createTime");
        userRepository.save(user);
    }
}
