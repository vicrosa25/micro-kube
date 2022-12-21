package rosa.victor.microcourse.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rosa.victor.microcourse.model.entity.Course;
import rosa.victor.microcourse.service.CourseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> list() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Course> course = courseService.findByID(id);

        if (course.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(course.get());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return getErrors(result);
        }

        Course savedCourse = courseService.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return getErrors(result);
        }

        Optional<Course> oldCourse = courseService.findByID(id);

        if (oldCourse.isEmpty())
            return ResponseEntity.notFound().build();

        Course dataBaseCourse = oldCourse.get();
        dataBaseCourse.setName(course.getName());


        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(dataBaseCourse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Course> course = courseService.findByID(id);

        if (course.isEmpty())
            return ResponseEntity.notFound().build();

        courseService.delete(id);

        return ResponseEntity.noContent().build();
    }


    private static ResponseEntity<Map<String, String>> getErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }


}
