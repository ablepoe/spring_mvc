package cn.luxh.app.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.luxh.app.domain.User;
import cn.luxh.app.repository.UserRepository;
import cn.luxh.app.service.UserService;

/**
 * 用户业务服务实现类
 * @author Luxh
 * 2012-8-31
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    
    
    @Autowired
    private UserRepository userRepository;//注入UserRepository

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly=true)
    public User findUserById(Integer id) {
        return userRepository.findOne(id);
    }

    
    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        userRepository.delete(id);
    }

}