package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.DeptUser;
import com.java2e.martin.biz.system.mapper.DeptUserMapper;
import com.java2e.martin.biz.system.service.DeptUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户部门关系 服务实现类
 * </p>
 *
 * @author 狮少
 * @date 2019-10-18
 */
@Service
public class DeptUserServiceImpl extends MartinServiceImpl<DeptUserMapper, DeptUser> implements DeptUserService {

    @Override
    protected void setEntity() {
        this.clz = DeptUser.class;
    }
}
