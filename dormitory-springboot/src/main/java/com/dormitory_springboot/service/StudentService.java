package com.dormitory_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dormitory_springboot.entity.Student;
import com.dormitory_springboot.form.SearchForm;
import com.dormitory_springboot.form.StudentForm;
import com.dormitory_springboot.vo.PageVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author admin
 * @since 2022-06-01
 */
public interface StudentService extends IService<Student> {
    public Boolean saveStudent(Student student);

    public PageVO list(Integer page, Integer size);

    public PageVO search(SearchForm searchForm);

    public Boolean update(StudentForm studentForm);

    public Boolean deleteById(Integer id);
}
