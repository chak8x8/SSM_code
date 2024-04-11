package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /*
        多条件课程列表查询
    */
    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        List<Course> list = courseMapper.findCourseByCondition(courseVo);
        return list;
    }

    /*
        新增课程以及讲师讯息
     */
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {

        // 封装课程讯息
        Course course = new Course();

        BeanUtils.copyProperties(course, courseVo);

        // 补全课程讯息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        // 保存课程
        courseMapper.saveCourse(course);

        // 获取新插入数据的ID值
        int id = course.getId();

        // 封装讲师讯息
        Teacher teacher = new Teacher();

        BeanUtils.copyProperties(teacher, courseVo);

        // 补全讲师讯息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);

        // 保存讲师讯息
        courseMapper.saveTeacher(teacher);
    }


    /*
        根据ID查询课程讯息
     */
    @Override
    public CourseVo findCourseById(int id) {
        return courseMapper.findCourseById(id);
    }

    /*
        更新课程以及讲师讯息
     */
    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        // 封裝课程讯息
        Course course = new Course();
        BeanUtils.copyProperties(course, courseVo);

        // 补全讯息
        Date date = new Date();
        course.setUpdateTime(date);

        // 更新课程讯息
        courseMapper.updateCourse(course);

        // 封裝讲师讯息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVo);

        // 补全讯息
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);

        // 更新课程讯息
        courseMapper.updateTeacher(teacher);
    }

    /*
        课程状态变更
     */
    @Override
    public void updateCourseStatus(int courseId, int status) {

        // 1.封裝数据
        Course course = new Course();
        course.setId(courseId);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        // 2.调用Mapper
        courseMapper.updateCourseStatus(course);
    }



}
