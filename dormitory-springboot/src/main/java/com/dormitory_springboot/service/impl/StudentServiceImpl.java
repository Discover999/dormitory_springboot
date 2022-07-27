package com.dormitory_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dormitory_springboot.entity.Dormitory;
import com.dormitory_springboot.entity.Student;
import com.dormitory_springboot.form.SearchForm;
import com.dormitory_springboot.form.StudentForm;
import com.dormitory_springboot.mapper.DormitoryMapper;
import com.dormitory_springboot.mapper.StudentMapper;
import com.dormitory_springboot.service.StudentService;
import com.dormitory_springboot.util.CommonUtil;
import com.dormitory_springboot.vo.PageVO;
import com.dormitory_springboot.vo.StudentVO;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;

    @Override
    public Boolean saveStudent(Student student) {
        //添加学生数据
        student.setCreateDate(CommonUtil.createDate());
        int insert = this.studentMapper.insert(student);
        if (insert != 1) return false;
        //修改宿舍数据
        Dormitory dormitory = this.dormitoryMapper.selectById(student.getDormitoryId());
        if (dormitory.getAvailable() == 0) {
            return false;
        }
        dormitory.setAvailable(dormitory.getAvailable() - 1);
        int update = this.dormitoryMapper.updateById(dormitory);
        if (update != 1) return false;
        return true;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Student> studentPage = new Page<>(page, size);
        Page<Student> resultPage = this.studentMapper.selectPage(studentPage, null);
        List<Student> studentList = resultPage.getRecords();
        //VO转换
        List<StudentVO> studentVOList = new ArrayList<>();
        for (Student student : studentList) {
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student, studentVO);
            Dormitory dormitory = this.dormitoryMapper.selectById(student.getDormitoryId());
            studentVO.setDormitoryName(dormitory.getName());
            studentVOList.add(studentVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setData(studentVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<Student> studentPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<Student> resultPage = null;
        if (searchForm.getValue().equals("")) {
            resultPage = this.studentMapper.selectPage(studentPage, null);
        } else {
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = this.studentMapper.selectPage(studentPage, queryWrapper);
        }
        List<Student> studentList = resultPage.getRecords();
        //VO转换
        List<StudentVO> studentVOList = new ArrayList<>();
        for (Student student : studentList) {
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student, studentVO);
            Dormitory dormitory = this.dormitoryMapper.selectById(student.getDormitoryId());
            studentVO.setDormitoryName(dormitory.getName());
            studentVOList.add(studentVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setData(studentVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public Boolean update(StudentForm studentForm) {
        //更新学生信息
        Student student = new Student();
        BeanUtils.copyProperties(studentForm, student);
        int update = this.studentMapper.updateById(student);
        if (update != 1) return false;
        //更新宿舍数据
        if (!studentForm.getDormitoryId().equals(studentForm.getOldDormitoryId())) {
            //old+1，new-1
            try {
                this.dormitoryMapper.addAvailable(studentForm.getOldDormitoryId());
                this.dormitoryMapper.subAvailable(studentForm.getDormitoryId());
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean deleteById(Integer id) {
        //修改宿舍数据
        Student student = this.studentMapper.selectById(id);
        try {
            Dormitory dormitory = this.dormitoryMapper.selectById(student.getDormitoryId());
            if (dormitory.getType() > dormitory.getAvailable()) {
                this.dormitoryMapper.addAvailable(student.getDormitoryId());
            }
        } catch (Exception e) {
            return false;
        }
        //删除学生数据
        int delete = this.studentMapper.deleteById(id);
        if (delete != 1) return false;
        return true;
    }
}
