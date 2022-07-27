package com.dormitory_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dormitory_springboot.entity.Dormitory;
import com.dormitory_springboot.entity.Moveout;
import com.dormitory_springboot.entity.Student;
import com.dormitory_springboot.form.SearchForm;
import com.dormitory_springboot.mapper.DormitoryMapper;
import com.dormitory_springboot.mapper.MoveoutMapper;
import com.dormitory_springboot.mapper.StudentMapper;
import com.dormitory_springboot.service.MoveoutService;
import com.dormitory_springboot.util.CommonUtil;
import com.dormitory_springboot.vo.MoveoutVO;
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
public class MoveoutServiceImpl extends ServiceImpl<MoveoutMapper, Moveout> implements MoveoutService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private MoveoutMapper moveoutMapper;

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Student> studentPage = new Page<>(page, size);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", "入住");
        Page<Student> resultPage = this.studentMapper.selectPage(studentPage, queryWrapper);
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
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", "入住");
        if (searchForm.getValue().equals("")) {
            resultPage = this.studentMapper.selectPage(studentPage, queryWrapper);
        } else {
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
    public Boolean moveout(Integer id, String reason) {
        Moveout moveout = new Moveout();
        moveout.setStudentId(id);
        Student student = this.studentMapper.selectById(id);
        moveout.setDormitoryId(student.getDormitoryId());
        moveout.setReason(reason);
        moveout.setCreateDate(CommonUtil.createDate());
        int insert = this.moveoutMapper.insert(moveout);
        if (insert != 1) return false;
        student.setState("迁出");
        int update = this.studentMapper.updateById(student);
        if (update != 1) return false;
        try {
            this.dormitoryMapper.addAvailable(student.getDormitoryId());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public PageVO moveoutList(Integer page, Integer size) {
        Page<Moveout> moveoutPage = new Page<>(page, size);
        Page<Moveout> resultPage = this.moveoutMapper.selectPage(moveoutPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        List<MoveoutVO> moveoutVOList = new ArrayList<>();
        for (Moveout moveout : resultPage.getRecords()) {
            MoveoutVO moveoutVO = new MoveoutVO();
            BeanUtils.copyProperties(moveout, moveoutVO);
            Student student = this.studentMapper.selectById(moveout.getStudentId());
            Dormitory dormitory = this.dormitoryMapper.selectById(moveout.getDormitoryId());
            moveoutVO.setStudentName(student.getName());
            moveoutVO.setDormitoryName(dormitory.getName());
            moveoutVOList.add(moveoutVO);
        }
        pageVO.setData(moveoutVOList);
        return pageVO;
    }

    @Override
    public PageVO moveoutSearch(SearchForm searchForm) {
        Page<Moveout> moveoutPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<Moveout> resultPage = null;
        if (searchForm.getValue().equals("")) {
            resultPage = this.moveoutMapper.selectPage(moveoutPage, null);
        } else {
            QueryWrapper<Moveout> queryWrapper = new QueryWrapper<>();
            if (searchForm.getKey().equals("studentName")) {
                QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
                studentQueryWrapper.like("name", searchForm.getValue());
                List<Student> studentList = this.studentMapper.selectList(studentQueryWrapper);
                List<Integer> idList = new ArrayList<>();
                for (Student student : studentList) {
                    idList.add(student.getId());
                }
                queryWrapper.in("student_id", idList);
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
            resultPage = this.moveoutMapper.selectPage(moveoutPage, queryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        List<MoveoutVO> moveoutVOList = new ArrayList<>();
        for (Moveout moveout : resultPage.getRecords()) {
            MoveoutVO moveoutVO = new MoveoutVO();
            BeanUtils.copyProperties(moveout, moveoutVO);
            Student student = this.studentMapper.selectById(moveout.getStudentId());
            Dormitory dormitory = this.dormitoryMapper.selectById(moveout.getDormitoryId());
            moveoutVO.setStudentName(student.getName());
            moveoutVO.setDormitoryName(dormitory.getName());
            moveoutVOList.add(moveoutVO);
        }
        pageVO.setData(moveoutVOList);
        return pageVO;
    }
}
