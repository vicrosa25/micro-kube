package rosa.victor.microcourse.service;

import rosa.victor.microcourse.model.User;
import rosa.victor.microcourse.model.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll();
    Optional<Course> findByID(Long id);
    Course save(Course course);
    void delete(Long id);

    Optional<User> assignUser(User user, Long courseId);
    Optional<User> createUser(User user, Long courseId);
    Optional<User> removeFromCourse(User user, Long courseId);
}
