package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.biz.system.entity.SysAnnouncement;
import com.java2e.martin.biz.system.mapper.SysAnnouncementMapper;
import com.java2e.martin.biz.system.service.SysAnnouncementService;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 公告表  服务实现
 *
 * @author 零代科技
 * @version 1.0
 * @date 2023-10-04
 * @describtion
 * @since 1.0
 */
@Service
public class SysAnnouncementServiceImpl extends MartinServiceImpl<SysAnnouncementMapper, SysAnnouncement> implements SysAnnouncementService {
    @Override
    protected void setEntity() {
        this.clz = SysAnnouncement.class;
    }
}
