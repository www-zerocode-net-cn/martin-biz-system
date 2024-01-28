package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.Privilege;
import com.java2e.martin.biz.system.mapper.PrivilegeMapper;
import com.java2e.martin.biz.system.service.PrivilegeService;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统权限 服务实现类
 * </p>
 *
 * @author 狮少
 * @version 1.0
 * @date 2021-05-08
 * @describtion
 * @since 1.0
 */
@Service
public class PrivilegeServiceImpl extends MartinServiceImpl<PrivilegeMapper, Privilege> implements PrivilegeService {
    @Override
    protected void setEntity() {
        this.clz = Privilege.class;
    }
}
