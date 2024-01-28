package com.java2e.martin.biz.system.service;

import com.java2e.martin.common.bean.system.SocialDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import com.java2e.martin.common.data.mybatis.service.MartinService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统社交账号 服务类
 * </p>
 *
 * @author 狮少
 * @date 2019-10-18
 */
@Transactional(rollbackFor = Exception.class)
public interface SocialDetailsService extends MartinService<SocialDetails> {

}
