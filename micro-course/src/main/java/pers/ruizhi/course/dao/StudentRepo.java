package pers.ruizhi.course.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.ruizhi.course.domain.Student;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    Student findByUserId(Integer userId);
}
