package cn.luxh.app.service;

import cn.luxh.app.domain.User;

/**
 * 用户业务接口
 * @author Luxh
 * 2012-8-31
 */
public interface UserService {
    
    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);
    
    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User findUserById(Integer id);
    
    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);
    
    /**
     * 根据ID删除用户
     * @param id
     */
    void deleteUserById(Integer id);
    
    
}