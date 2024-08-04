package pers.ruizhi.gateway.course.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.ruizhi.gateway.course.domain.Course;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {

}
