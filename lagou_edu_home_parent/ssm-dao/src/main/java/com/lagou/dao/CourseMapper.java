package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;

import java.util.List;


public interface CourseMapper {

    /*
        多条件课程列表查询
     */
    public List<Course> findCourseByCondition(CourseVo courseVo);

    /*
        新增课程讯息
     */
    public void saveCourse(Course course);

    /*
        新增讲师讯息
     */
    public void saveTeacher(Teacher teacher);

    /*
        回显课程讯息(根据ID查询对应的课程讯息以及关联的讲师讯息)
     */
    public CourseVo findCourseById(Integer id);

    /*
        更新课程讯息
     */
    public void updateCourse(Course course);

    /*
        更新讲师讯息
     */
    public void  updateTeacher(Teacher teacher);

    /*
        课程状态管理
     */
    public void updateCourseStatus(Course course);

}
