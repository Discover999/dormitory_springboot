package com.dormitory_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dormitory_springboot.entity.SystemAdmin;
import com.dormitory_springboot.form.RuleForm;
import com.dormitory_springboot.vo.ResultVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author admin
 * @since 2022-06-01
 */
public interface SystemAdminService extends IService<SystemAdmin> {
    public ResultVO login(RuleForm ruleForm);
}
