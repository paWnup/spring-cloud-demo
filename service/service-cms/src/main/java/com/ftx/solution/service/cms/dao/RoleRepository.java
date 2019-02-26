package com.ftx.solution.service.cms.dao;

import com.ftx.solution.service.cms.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author puan
 * @date 2018-11-20 15:05
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * 根据角色名查找角色
     *
     * @param roleName 角色名
     * @return 角色
     */
    Role findByRoleNameLike(String roleName);

    /**
     * 根据角色名查找角色
     *
     * @param roleName 角色名
     * @return 角色
     */
    Role readByRoleName(String roleName);

}
