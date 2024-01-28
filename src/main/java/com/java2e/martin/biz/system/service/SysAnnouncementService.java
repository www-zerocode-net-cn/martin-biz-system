package com.java2e.martin.biz.system.service;

import com.java2e.martin.biz.system.entity.SysAnnouncement;
import com.java2e.martin.common.data.mybatis.service.MartinService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公告表  服务
 *
 * @author 零代科技
 * @version 1.0
 * @date 2023-10-04
 * @describtion
 * @since 1.0
 */
@Transactional(rollbackFor = Exception.class)
public interface SysAnnouncementService extends MartinService<SysAnnouncement> {

}
