package pers.ruizhi.course.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.ruizhi.course.domain.Assignment;
import pers.ruizhi.course.domain.AssignmentSimpleVo;

import java.util.List;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@Repository
public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {

    List<AssignmentSimpleVo> findAllByCourseId(Integer courseId);
}
