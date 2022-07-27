package com.dormitory_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dormitory_springboot.entity.DormitoryAdmin;
import com.dormitory_springboot.form.RuleForm;
import com.dormitory_springboot.form.SearchForm;
import com.dormitory_springboot.vo.PageVO;
import com.dormitory_springboot.vo.ResultVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author admin
 * @since 2022-06-01
 */
public interface DormitoryAdminService extends IService<DormitoryAdmin> {
    public ResultVO login(RuleForm ruleForm);

    public PageVO list(Integer page, Integer size);

    public PageVO search(SearchForm searchForm);
}
