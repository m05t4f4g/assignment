package com.isc.assignment.repository;

import com.isc.assignment.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        courseRepository.deleteAll(); // Clear the repository before each test
    }

//    @Test
//    void testFindAllByCategory() {
//        // Arrange
//        Course course1 = new Course("Java Programming", "Programming", 5, "Learn Java.");
//        Course course2 = new Course("Python Programming", "Programming", 5, "Learn Python.");
//        Course course3 = new Course("History 101", "History", 4, "Introduction to History.");
//
//        courseRepository.save(course1);
//        courseRepository.save(course2);
//        courseRepository.save(course3);
//
//        // Act
//        Iterable<Course> programmingCourses = courseRepository.findAllByCategory("Programming");
//        Iterable<Course> historyCourses = courseRepository.findAllByCategory("History");
//
//        // Assert
//        assertNotNull(programmingCourses);
//        assertTrue(((List<Course>) programmingCourses).size() == 2); // should return 2 courses
//        assertNotNull(historyCourses);
//        assertTrue(((List<Course>) historyCourses).size() == 1); // should return 1 course
//        assertTrue(((List<Course>) programmingCourses).contains(course1));
//        assertTrue(((List<Course>) programmingCourses).contains(course2));
//        assertTrue(((List<Course>) historyCourses).contains(course3));
//    }

    @Test
    void testFindAllByCategory_NoCoursesFound() {
        // Act
        Iterable<Course> result = courseRepository.findAllByCategory("NonExistentCategory");

        // Assert
        assertNotNull(result);
        assertFalse(((List<Course>) result).iterator().hasNext()); // No courses should be found
    }

//    @Test
//    void testCruDOperations() {
//        // Arrange
//        Course course = new Course("Java Programming", "Programming", 5, "Learn Java.");
//
//        // Save the course
//        course = courseRepository.save(course);
//        assertNotNull(course.getId()); // Ensure it has an ID after saving
//
//        // Find by ID
//        Course foundCourse = courseRepository.findById(course.getId()).orElse(null);
//        assertNotNull(foundCourse);
//        assertEquals("Java Programming", foundCourse.getName());
//
//        // Delete the course
//        courseRepository.deleteById(course.getId());
//        assertFalse(courseRepository.findById(course.getId()).isPresent()); // Should no longer exist
//    }
}