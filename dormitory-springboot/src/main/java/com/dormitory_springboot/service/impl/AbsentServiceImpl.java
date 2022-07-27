package com.dormitory_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dormitory_springboot.entity.Absent;
import com.dormitory_springboot.entity.Building;
import com.dormitory_springboot.entity.Dormitory;
import com.dormitory_springboot.form.SearchForm;
import com.dormitory_springboot.mapper.*;
import com.dormitory_springboot.service.AbsentService;
import com.dormitory_springboot.vo.AbsentVO;
import com.dormitory_springboot.vo.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-06-01
 */
@Service
public class AbsentServiceImpl extends ServiceImpl<AbsentMapper, Absent> implements AbsentService {

    @Autowired
    private AbsentMapper absentMapper;
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DormitoryAdminMapper dormitoryAdminMapper;

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Absent> absentPage = new Page<>(page, size);
        Page<Absent> resultPage = this.absentMapper.selectPage(absentPage, null);
        List<AbsentVO> absentVOList = new ArrayList<>();
        for (Absent absent : resultPage.getRecords()) {
            AbsentVO absentVO = new AbsentVO();
            BeanUtils.copyProperties(absent, absentVO);
            absentVO.setBuildingName(this.buildingMapper.selectById(absent.getBuildingId()).getName());
            absentVO.setDormitoryName(this.dormitoryMapper.selectById(absent.getDormitoryId()).getName());
            absentVO.setStudentName(this.studentMapper.selectById(absent.getStudentId()).getName());
            absentVO.setDormitoryAdminName(this.dormitoryAdminMapper.selectById(absent.getDormitoryAdminId()).getName());
            absentVOList.add(absentVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(absentVOList);
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<Absent> absentPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<Absent> resultPage = null;
        if (searchForm.getValue().equals("")) {
            resultPage = this.absentMapper.selectPage(absentPage, null);
        } else {
            QueryWrapper<Absent> queryWrapper = new QueryWrapper<>();
            if (searchForm.getKey().equals("buildingName")) {
                QueryWrapper<Building> buildingQueryWrapper = new QueryWrapper<>();
                buildingQueryWrapper.like("name", searchForm.getValue());
                List<Building> buildingList = this.buildingMapper.selectList(buildingQueryWrapper);
                List<Integer> idList = new ArrayList<>();
                for (Building building : buildingList) {
                    idList.add(building.getId());
                }
                queryWrapper.in("building_id", idList);
            }
            if (searchForm.getKey().equals("dormitoryName")) {
                QueryWrapper<Dormitory> dormitoryQueryWrapper = new QueryWrapper<>();
                dormitoryQueryWrapper.like("name", searchForm.getValue());
                List<Dormitory> dormitoryList = this.dormitoryMapper.selectList(dormitoryQueryWrapper);
                List<Integer> idList = new ArrayList<>();
                for (Dormitory dormitory : dormitoryList) {
                    idList.add(dormitory.getId());
                }
                queryWrapper.in("dormitory_id", idList);
            }
            resultPage = this.absentMapper.selectPage(absentPage, queryWrapper);
        }
        List<AbsentVO> absentVOList = new ArrayList<>();
        for (Absent absent : resultPage.getRecords()) {
            AbsentVO absentVO = new AbsentVO();
            BeanUtils.copyProperties(absent, absentVO);
            absentVO.setBuildingName(this.buildingMapper.selectById(absent.getBuildingId()).getName());
            absentVO.setDormitoryName(this.dormitoryMapper.selectById(absent.getDormitoryId()).getName());
            absentVO.setStudentName(this.studentMapper.selectById(absent.getStudentId()).getName());
            absentVO.setDormitoryAdminName(this.dormitoryAdminMapper.selectById(absent.getDormitoryAdminId()).getName());
            absentVOList.add(absentVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(absentVOList);
        return pageVO;
    }
}
