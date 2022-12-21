package rosa.victor.microcourse.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import rosa.victor.microcourse.model.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
    private String name;

    @Transient
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    private List<CourseUser> courseUsers;

    public Course() {
        courseUsers = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addCourseUser(CourseUser courseUser) {
        courseUsers.add(courseUser);
    }

    public void removeCourseUser(CourseUser courseUser) {
        courseUsers.remove(courseUser);
    }
}
