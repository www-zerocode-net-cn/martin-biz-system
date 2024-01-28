package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.Log;
import com.java2e.martin.biz.system.mapper.LogMapper;
import com.java2e.martin.biz.system.service.LogService;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author 狮少
 * @version 1.0
 * @date 2021-05-08
 * @describtion
 * @since 1.0
 */
@Service
public class LogServiceImpl extends MartinServiceImpl<LogMapper, Log> implements LogService {
    @Override
    protected void setEntity() {
        this.clz = Log.class;
    }
}
