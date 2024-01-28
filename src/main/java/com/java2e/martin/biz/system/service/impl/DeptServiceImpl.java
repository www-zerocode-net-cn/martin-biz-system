package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.Dept;
import com.java2e.martin.biz.system.mapper.DeptMapper;
import com.java2e.martin.biz.system.service.DeptService;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统部门 服务实现类
 * </p>
 *
 * @author 狮少
 * @version 1.0
 * @date 2021-05-08
 * @describtion
 * @since 1.0
 */
@Service
public class DeptServiceImpl extends MartinServiceImpl<DeptMapper, Dept> implements DeptService {
    @Override
    protected void setEntity() {
        this.clz = Dept.class;
    }
}
