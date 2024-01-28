package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.UserRole;
import com.java2e.martin.biz.system.mapper.UserRoleMapper;
import com.java2e.martin.biz.system.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户角色关系 服务实现类
 * </p>
 *
 * @author 狮少
 * @date 2019-10-18
 */
@Service
public class UserRoleServiceImpl extends MartinServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    protected void setEntity() {
        this.clz = UserRole.class;
    }
}
