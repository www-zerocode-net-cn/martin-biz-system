package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.SocialDetails;
import com.java2e.martin.biz.system.mapper.SocialDetailsMapper;
import com.java2e.martin.biz.system.service.SocialDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统社交账号 服务实现类
 * </p>
 *
 * @author 狮少
 * @date 2019-10-18
 */
@Service
public class SocialDetailsServiceImpl extends MartinServiceImpl<SocialDetailsMapper, SocialDetails> implements SocialDetailsService {

    @Override
    protected void setEntity() {
        this.clz = SocialDetails.class;
    }
}
