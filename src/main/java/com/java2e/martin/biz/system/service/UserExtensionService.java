package com.java2e.martin.biz.system.service;

import com.java2e.martin.biz.system.dto.UserDto;
import com.java2e.martin.common.api.system.RemoteSystemUser;
import com.java2e.martin.common.bean.system.User;
import com.java2e.martin.common.core.api.R;
import com.java2e.martin.common.data.mybatis.service.MartinService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author 狮少
 * @date 2019-10-18
 */
@Transactional(rollbackFor = Exception.class)
public interface UserExtensionService extends MartinService<User>,RemoteSystemUser {

    /**
     * 获取当前登录用户的所有信息
     *
     * @return
     */
    R currentUser();

    R accountBasic();

    R accountUpdate(UserDto userDto);
}
