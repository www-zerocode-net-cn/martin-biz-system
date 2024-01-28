package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.RolePrivilege;
import com.java2e.martin.biz.system.mapper.RolePrivilegeMapper;
import com.java2e.martin.biz.system.service.RolePrivilegeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色权限关系 服务实现类
 * </p>
 *
 * @author 狮少
 * @date 2019-10-18
 */
@Service
public class RolePrivilegeServiceImpl extends MartinServiceImpl<RolePrivilegeMapper, RolePrivilege> implements RolePrivilegeService {

    @Override
    protected void setEntity() {
        this.clz = RolePrivilege.class;
    }
}
