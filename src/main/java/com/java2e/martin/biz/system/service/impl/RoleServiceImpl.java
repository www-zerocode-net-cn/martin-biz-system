package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.Role;
import com.java2e.martin.biz.system.mapper.RoleMapper;
import com.java2e.martin.biz.system.service.RoleService;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author 狮少
 * @version 1.0
 * @date 2021-05-08
 * @describtion
 * @since 1.0
 */
@Service
public class RoleServiceImpl extends MartinServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    protected void setEntity() {
        this.clz = Role.class;
    }
}
