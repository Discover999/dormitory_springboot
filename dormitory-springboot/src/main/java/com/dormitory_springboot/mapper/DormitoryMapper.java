package com.dormitory_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dormitory_springboot.entity.Dormitory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-06-01
 */
@Repository
@Mapper
public interface DormitoryMapper extends BaseMapper<Dormitory> {
    public void subAvailable(Integer id);

    public void addAvailable(Integer id);

    public Integer findAvailableDormitoryId();
}
