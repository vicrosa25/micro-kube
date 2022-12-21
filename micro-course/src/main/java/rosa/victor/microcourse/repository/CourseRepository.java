package rosa.victor.microcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rosa.victor.microcourse.model.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
