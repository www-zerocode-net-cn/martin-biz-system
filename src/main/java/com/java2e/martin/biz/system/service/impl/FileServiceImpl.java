package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.File;
import com.java2e.martin.biz.system.mapper.FileMapper;
import com.java2e.martin.biz.system.service.FileService;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统文件 服务实现类
 * </p>
 *
 * @author 狮少
 * @version 1.0
 * @date 2021-05-08
 * @describtion
 * @since 1.0
 */
@Service
public class FileServiceImpl extends MartinServiceImpl<FileMapper, File> implements FileService {
    @Override
    protected void setEntity() {
        this.clz = File.class;
    }
}
