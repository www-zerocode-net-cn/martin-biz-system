package com.java2e.martin.biz.system.service.impl;

import com.java2e.martin.common.bean.system.Menu;
import com.java2e.martin.biz.system.mapper.MenuMapper;
import com.java2e.martin.biz.system.service.MenuService;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author 狮少
 * @version 1.0
 * @date 2021-05-08
 * @describtion
 * @since 1.0
 */
@Service
public class MenuServiceImpl extends MartinServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    protected void setEntity() {
        this.clz = Menu.class;
    }
}
