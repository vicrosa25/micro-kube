package rosa.victor.microcourse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rosa.victor.microcourse.model.User;
import rosa.victor.microcourse.model.entity.Course;
import rosa.victor.microcourse.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findByID(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Optional<User> assignUser(User user, Long courseId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> createUser(User user, Long courseId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> removeFromCourse(User user, Long courseId) {
        return Optional.empty();
    }
}
