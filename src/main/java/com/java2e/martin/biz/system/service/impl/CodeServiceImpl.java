package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.Code;
import com.java2e.martin.biz.system.mapper.CodeMapper;
import com.java2e.martin.biz.system.service.CodeService;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统代码生成表 服务实现类
 * </p>
 *
 * @author 狮少
 * @version 1.0
 * @date 2021-05-08
 * @describtion
 * @since 1.0
 */
@Service
public class CodeServiceImpl extends MartinServiceImpl<CodeMapper, Code> implements CodeService {
    @Override
    protected void setEntity() {
        this.clz = Code.class;
    }
}
