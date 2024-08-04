package pers.ruizhi.gateway.course.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pers.ruizhi.gateway.course.domain.Assignment;
import pers.ruizhi.gateway.course.domain.AssignmentFindAllVo;

import java.util.List;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@Repository
public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {

    @Query(value = "select new pers.ruizhi.course.domain.AssignmentFindAllVo(a.id, a.name, a.dueTime) from Assignment a where a.courseId=?1")
    List<AssignmentFindAllVo> findAllByCourseId(@Param("courseId") Integer courseId);
}
