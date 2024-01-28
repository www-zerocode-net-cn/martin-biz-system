package com.java2e.martin.biz.system.service;

import com.java2e.martin.common.bean.system.Code;
import com.java2e.martin.common.data.mybatis.service.MartinService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统代码生成表 服务类
 * </p>
 *
 * @author 狮少
 * @version 1.0
 * @date 2021-05-08
 * @describtion
 * @since 1.0
 */
@Transactional(rollbackFor = Exception.class)
public interface CodeService extends MartinService<Code> {

}
