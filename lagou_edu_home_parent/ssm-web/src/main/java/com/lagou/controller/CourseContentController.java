package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId) {

        // 调用service
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "章节及课时内容查询成功", list);
        return responseResult;
    }

    /*
       回显章节对应的课程讯息
    */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId) {
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询课程讯息成功", course);
        return responseResult;
    }

    /*
        新增及更新章节讯息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection) {

        // 判断是否携带了章节ID
        if (courseSection.getCourseId() == null) {
            // 新增
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true, 200, "新增章节成功", null);
        } else {
            // 更新
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true, 200, "更新章节成功", null);
        }
    }

    /*
       修改章节状态
    */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status) {
        courseContentService.updateSectionStatus(id, status);

        // 数据响应
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status", status);

        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态成功", map);
        return responseResult;
    }


    /*
        新增及更新课时讯息
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson) {
        try {
            // 判断是否携带了ID
            if (courseLesson.getId() == null) {
                // 新增
                courseContentService.saveLesson(courseLesson);
                return new ResponseResult(true, 200, "新增课时成功", null);
            } else {
                // 更新
                courseContentService.updateLesson(courseLesson);
                return new ResponseResult(true, 200, "更新课时成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
