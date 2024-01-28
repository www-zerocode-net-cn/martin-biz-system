package com.java2e.martin.biz.system.service;

import com.java2e.martin.common.bean.system.Privilege;
import com.java2e.martin.common.data.mybatis.service.MartinService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统权限 服务类
 * </p>
 *
 * @author 狮少
 * @version 1.0
 * @date 2021-05-08
 * @describtion
 * @since 1.0
 */
@Transactional(rollbackFor = Exception.class)
public interface PrivilegeService extends MartinService<Privilege> {

}
