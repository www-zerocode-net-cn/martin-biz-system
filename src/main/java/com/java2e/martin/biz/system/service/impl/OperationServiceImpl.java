package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.Operation;
import com.java2e.martin.biz.system.mapper.OperationMapper;
import com.java2e.martin.biz.system.service.OperationService;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统操作 服务实现类
 * </p>
 *
 * @author 狮少
 * @version 1.0
 * @date 2021-05-08
 * @describtion
 * @since 1.0
 */
@Service
public class OperationServiceImpl extends MartinServiceImpl<OperationMapper, Operation> implements OperationService {
    @Override
    protected void setEntity() {
        this.clz = Operation.class;
    }
}
