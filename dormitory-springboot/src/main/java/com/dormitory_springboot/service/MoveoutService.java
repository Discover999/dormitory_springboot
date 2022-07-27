package com.dormitory_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dormitory_springboot.entity.Moveout;
import com.dormitory_springboot.form.SearchForm;
import com.dormitory_springboot.vo.PageVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author admin
 * @since 2022-06-01
 */
public interface MoveoutService extends IService<Moveout> {
    public PageVO list(Integer page, Integer size);

    public PageVO search(SearchForm searchForm);

    public Boolean moveout(Integer id, String reason);

    public PageVO moveoutList(Integer page, Integer size);

    public PageVO moveoutSearch(SearchForm searchForm);
}
