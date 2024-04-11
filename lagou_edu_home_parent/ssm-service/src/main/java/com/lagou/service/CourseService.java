package com.lagou.service;


import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /*
        多条件课程列表查询
    */
    public List<Course> findCourseByCondition(CourseVo courseVo);

    /*
        新增课程以及讲师讯息
     */
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /*
        根据ID查询课程讯息
     */
    public CourseVo findCourseById(int id);

    /*
        更新课程以及讲师讯息
     */
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /*
        课程状态变更
     */
    public void updateCourseStatus(int courseId, int status);

}
