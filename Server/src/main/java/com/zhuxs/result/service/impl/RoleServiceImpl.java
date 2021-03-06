package com.zhuxs.result.service.impl;

import com.zhuxs.result.domain.UserDao;
import com.zhuxs.result.domain.entity.User;
import com.zhuxs.result.exception.ResultException;
import com.zhuxs.result.domain.RoleDao;
import com.zhuxs.result.domain.entity.Permission;
import com.zhuxs.result.domain.entity.Role;
import com.zhuxs.result.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shusesshou on 2017/9/25.
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Role addRole(Role role) {
        role = roleDao.save(role);
        return role;
    }

    @Override
    public List<Role> listRoles() {
        List<Role> roles = roleDao.findAll();
        return roles;
    }

    @Override
    public List<Role> getRolesByUserId(long userId) {
        if (!userDao.exists(userId)){
            throw new ResultException();
        }
        try{
             User user = userDao.findOne(userId);
             List<Role> roles = user.getRoles();
             return roles;
        }catch (Exception e){
            throw new ResultException();
        }
    }

    @Override
    public Role updatePermissionsById(long id, List<Permission> permissions){
        if(!roleDao.exists(id)){
            throw new ResultException();
        }
        try {
            Role role = roleDao.findOne(id);
            role.setPermissions(permissions);
            role = roleDao.save(role);
            return role;
        } catch (Exception e){
            throw new ResultException();
        }
    }

    @Override
    public void delRoleById(long id) {
        roleDao.delete(id);
    }
}
