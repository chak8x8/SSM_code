package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /*
        根据课程ID查询关联的章节讯息及章节讯息关联的课时讯息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
        回显章节对应的课程讯息
     */
    public Course findCourseByCourseId(int courseId);

    /*
        新增章节讯息
     */
    public void saveSection(CourseSection courseSection);

    /*
        更新章节讯息
     */
    public void updateSection(CourseSection courseSection);

    /*
        修改章节状态
     */
    public void updateSectionStatus(CourseSection courseSection);

    /*
        新增课时讯息
     */
    public void saveLesson(CourseLesson courseLesson);

    /*
       更新课时讯息
    */
    public void updateLesson(CourseLesson courseLesson);
}
