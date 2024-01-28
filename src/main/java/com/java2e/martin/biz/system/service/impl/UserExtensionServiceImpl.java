package com.java2e.martin.biz.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java2e.martin.biz.system.mapper.UserExtensionMapper;
import com.java2e.martin.biz.system.service.PrivilegeExtensionService;
import com.java2e.martin.biz.system.service.RoleService;
import com.java2e.martin.biz.system.service.UserExtensionService;
import com.java2e.martin.biz.system.service.UserRoleService;
import com.java2e.martin.common.api.dto.ProjectUserDto;
import com.java2e.martin.common.api.dto.UserDto;
import com.java2e.martin.common.bean.system.Role;
import com.java2e.martin.common.bean.system.User;
import com.java2e.martin.common.bean.system.UserRole;
import com.java2e.martin.common.bean.system.vo.PrivilegeVO;
import com.java2e.martin.common.bean.system.vo.UserRolePrivilegeVo;
import com.java2e.martin.common.core.api.ApiErrorCode;
import com.java2e.martin.common.core.api.R;
import com.java2e.martin.common.core.constant.SocialLoginConstants;
import com.java2e.martin.common.data.mybatis.service.impl.MartinServiceImpl;
import com.java2e.martin.common.security.userdetail.MartinUser;
import com.java2e.martin.common.security.util.SecurityContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.internal.model.assignment.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author 狮少
 * @date 2019-10-18
 * <p>
 */
@RestController
@Service
@Slf4j
public class UserExtensionServiceImpl extends MartinServiceImpl<UserExtensionMapper, User> implements UserExtensionService {
    @Autowired
    private PrivilegeExtensionService privilegeExtensionService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void setEntity() {
        this.clz = User.class;
    }

    @Override
    public R currentUser() {
        MartinUser user = SecurityContextUtil.getAccessUser();
        Map data = this.baseMapper.currentUser(user.getId());
        data.put("access", SecurityContextUtil.getAuthorities());
        return R.ok(data);
    }

    @Override
    public R accountBasic() {
        MartinUser user = SecurityContextUtil.getAccessUser();
        Map data = this.baseMapper.accountBasic(user.getId());
        return R.ok(data);
    }

    @Override
    public R accountUpdate(com.java2e.martin.biz.system.dto.UserDto userDto) {
        log.info("userDto: {}", userDto);
        if (StrUtil.isNotBlank(userDto.getPwd())) {
            String encodePwd = passwordEncoder.encode(userDto.getPwd());
            userDto.setPwd(encodePwd);
        }
        String userId = SecurityContextUtil.getAccessUser().getId();
        User user = new User();
        BeanUtil.copyProperties(userDto, user);
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper();
        wrapper.eq(User::getId, userId);
        this.baseMapper.update(user, wrapper);
        return R.ok("更新成功");
    }

    @Override
    public R<UserRolePrivilegeVo> loadUserByUsername(String username) {
        UserRolePrivilegeVo userRolePrivilegeVo = new UserRolePrivilegeVo();
        User user = this.getOne(Wrappers.<User>query().lambda().eq(User::getUsername, username));
        log.debug("{}", Convert.toStr(user));
        if (null == user) {
            return R.failed(ApiErrorCode.USER_NOT_FOUND);
        }
        userRolePrivilegeVo.setUser(user);
        List<UserRole> roleList = userRoleService.list(Wrappers.<UserRole>query().lambda().eq(UserRole::getUserId, user.getId()));
        if (CollectionUtil.isEmpty(roleList)) {
            log.error("{}", R.failed(ApiErrorCode.ROLE_NOT_FOUND));
            return R.failed(ApiErrorCode.ROLE_NOT_FOUND);
        }
        Map<String, List<UserRole>> roles = roleList.stream().collect(Collectors.groupingBy(UserRole::getRoleId));
        log.info("roles: {}", roles);
        Set<String> roleIds = roles.keySet();
        log.info("roleIds: {}", roleIds);
        userRolePrivilegeVo.setRoles(roleIds);
        Set<PrivilegeVO> authoritySet = privilegeExtensionService.getPrivilegeByRoles(roleIds);
        if (CollectionUtil.isEmpty(authoritySet)) {
            log.error("{}", R.failed(ApiErrorCode.PRIVILEGE_NOT_FOUND));
            return R.failed(ApiErrorCode.PRIVILEGE_NOT_FOUND);
        }
        userRolePrivilegeVo.setRestfulPrivilege(authoritySet);
        userRolePrivilegeVo.setAuthoritySet(authoritySet.stream().map((vo) -> vo.getAuthority()).collect(Collectors.toSet()));
        return R.ok(userRolePrivilegeVo);
    }

    @Override
    public R<Set<PrivilegeVO>> loadSecurity() {
        return R.ok(privilegeExtensionService.getAllPrivilege());
    }

    @Override
    public User getUserByWechatOpenid(Map params) {
        String column = (String) params.get(SocialLoginConstants.OPENID_COLUMN);
        String openid = (String) params.get(SocialLoginConstants.OPENID);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column, openid);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public R<User> register(User user) {
        //配置初始密码
        user.setPwd(passwordEncoder.encode(SocialLoginConstants.INIT_PASSWORD));
        String userId = IdUtil.fastSimpleUUID();
        user.setId(userId);
        log.info("userId: {}", userId);
        this.baseMapper.insert(user);
        LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>();
        roleWrapper.eq(Role::getRoleCode, SocialLoginConstants.ROLE_NEW_REGISTER);
        String roleId = null;
        Role dbRole = roleService.getOne(roleWrapper);
        if (ObjectUtil.isNull(dbRole)) {
            roleId = IdUtil.fastSimpleUUID();
            Role tmpRole = new Role();
            tmpRole.setId(roleId);
            tmpRole.setRoleName(SocialLoginConstants.ROLE_NEW_REGISTER);
            tmpRole.setRoleCode(SocialLoginConstants.ROLE_NEW_REGISTER);
            roleService.save(tmpRole);
        } else {
            roleId = dbRole.getId();
        }
        log.info("roleId: {}", dbRole);
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleService.save(userRole);
        return R.ok(user);
    }

    @Override
    public R users(ProjectUserDto projectUserDto) {
        log.info("projectUserDto: {}", projectUserDto);
        if (StrUtil.isBlank(projectUserDto.getRoleId())) {
            log.error("roleId 为空");
            return R.failed("roleId 为空");
        }
        log.info("projectUserDto: {}", projectUserDto);
        Page<User> userPage = new Page<>(projectUserDto.getCurrent(), projectUserDto.getPageSize());
        IPage result = this.baseMapper.getUsers(userPage, projectUserDto);
        return R.ok(result);
    }

    @Override
    public R userRegister(@Validated UserDto userDto) {
        log.info("userDto: {}", userDto);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.or().eq(User::getUsername, userDto.getUsername());
        wrapper.or().eq(User::getEmail, userDto.getEmail());
        wrapper.or().eq(User::getPhone, userDto.getPhone());
        List<User> userList = this.baseMapper.selectList(wrapper);
        log.info("existUser: {}", userList);
        if (CollUtil.isEmpty(userList)) {
            User user = new User();
            BeanUtil.copyProperties(userDto, user);
            user.setPwd(passwordEncoder.encode(userDto.getPwd()));
            this.baseMapper.insert(user);
            Integer count = this.baseMapper.bindRole(user.getId());
            log.info("count: {}", count);
            return R.ok("用户注册成功!");
        } else {
            return R.failed(StrUtil.format("用户名「{}」或邮箱「{}」或手机号「{}」已经存在", userDto.getUsername(), userDto.getEmail(), userDto.getPhone()));
        }
    }

    @Override
    public R getApprovalUser(List<String> roleIds) {
        log.info("roleIds: {}", roleIds);
        if (CollUtil.isEmpty(roleIds)) {
            log.error("roleIds 为空");
            return R.failed("roleIds 为空");
        }
        List<User> result = this.baseMapper.getApprovalUser(roleIds);
        return R.ok(result);
    }

    @Override
    public R totalUser() {
        Integer total = this.baseMapper.selectCount(new QueryWrapper<>());
        return R.ok(total);
    }

}
