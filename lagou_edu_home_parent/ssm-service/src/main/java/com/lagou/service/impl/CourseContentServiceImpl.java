package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    private CourseContentMapper courseContentMapper;

    /*
       根据课程ID查询关联的章节讯息及章节讯息关联的课时讯息
    */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return list;
    }

    /*
        回显章节对应的课程讯息
     */
    @Override
    public Course findCourseByCourseId(int courseId) {
        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    /*
        新增章节讯息
     */
    @Override
    public void saveSection(CourseSection courseSection) {
        // 1.补全讯息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        // 2.调用courseContentMapper方法
        courseContentMapper.saveSection(courseSection);
    }

    /*
        更新章节讯息
     */
    @Override
    public void updateSection(CourseSection courseSection) {
        // 1.补全讯息
        Date date = new Date();
        courseSection.setUpdateTime(date);

        // 2.调用courseContentMapper方法
        courseContentMapper.updateSection(courseSection);
    }

    /*
        修改章节状态
     */
    @Override
    public void updateSectionStatus(int id, int status) {
        // 1.封裝数据
        CourseSection courseSection = new CourseSection();
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        courseSection.setId(id);

        // 2.调用Mapper
        courseContentMapper.updateSectionStatus(courseSection);
    }


    /*
        新增课时讯息
     */
    @Override
    public void saveLesson(CourseLesson courseLesson) {
        // 1.补全讯息
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);

        // 2.调用courseContentMapper方法
        courseContentMapper.saveLesson(courseLesson);
    }

    /*
        更新课时讯息
     */
    @Override
    public void updateLesson(CourseLesson courseLesson) {
        // 1.补全讯息
        Date date = new Date();
        courseLesson.setUpdateTime(date);

        // 2.调用courseContentMapper方法
        courseContentMapper.updateLesson(courseLesson);
    }
}
