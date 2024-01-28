package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.User;
import com.java2e.martin.biz.system.mapper.UserMapper;
import com.java2e.martin.biz.system.service.UserService;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author 狮少
 * @version 1.0
 * @date 2021-05-08
 * @describtion
 * @since 1.0
 */
@Service
public class UserServiceImpl extends MartinServiceImpl<UserMapper, User> implements UserService {
    @Override
    protected void setEntity() {
        this.clz = User.class;
    }
}
