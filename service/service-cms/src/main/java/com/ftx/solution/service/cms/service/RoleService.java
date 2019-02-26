package com.ftx.solution.service.cms.service;

import com.ftx.solution.common.base.exception.BusinessException;
import com.ftx.solution.service.cms.dao.RoleRepository;
import com.ftx.solution.service.cms.entity.Role;
import com.ftx.solution.service.cms.vo.CreateRoleVo;
import com.ftx.solution.service.cms.vo.GetRoleVo;
import com.ftx.solution.service.cms.vo.UpdateRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author puan
 * @date 2018-11-20 15:11
 **/
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 创建角色
     *
     * @param roleVo 角色值对象
     * @return 角色ID
     */
    @Transactional(rollbackFor = Exception.class)
    public Long create(CreateRoleVo roleVo) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVo, role);
        roleRepository.save(role);
        return role.getId();
    }

    /**
     * 创建角色
     *
     * @param role 角色对象
     * @return 角色ID
     */
    @Transactional(rollbackFor = Exception.class)
    public Long create(Role role) {
        roleRepository.save(role);
        return role.getId();
    }

    /**
     * 根据ID查找角色
     *
     * @param id 角色ID
     * @return 角色
     */
    public GetRoleVo getRoleById(Long id) {
        Role role = roleRepository.getOne(id);
        if (role == null) {
            throw new BusinessException("角色不存在！");
        }
        GetRoleVo roleVo = new GetRoleVo();
        BeanUtils.copyProperties(roleVo, roleVo);
        return roleVo;
    }

    /**
     * 根据ID查找角色
     *
     * @param roleName 角色名
     * @return 角色
     */
    public GetRoleVo getRoleByRolename(String roleName) {
        Role role = roleRepository.findByRoleNameLike(roleName);
        if (role == null) {
            throw new BusinessException("角色不存在！");
        }
        GetRoleVo roleVo = new GetRoleVo();
        BeanUtils.copyProperties(role, roleVo);
        return roleVo;
    }

    /**
     * 根据ID删除角色
     *
     * @param id 角色ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleById(Long id) {
        roleRepository.delete(id);
    }

    /**
     * 根据ID更新角色信息
     *
     * @param roleVo 角色值对象
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleById(UpdateRoleVo roleVo) {
        Role role = roleRepository.getOne(roleVo.getId());
        if (role == null) {
            throw new BusinessException("角色不存在！");
        }
        BeanUtils.copyProperties(roleVo, role, "id", "updateTime", "createTime");
        roleRepository.save(role);
    }
}
