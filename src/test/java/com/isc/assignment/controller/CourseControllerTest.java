package com.isc.assignment.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.isc.assignment.model.Course;
import com.isc.assignment.service.CourseService;

class CourseControllerTest {

    @InjectMocks
    private CourseController courseController;

    @Mock
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCourses() {
        // Arrange
        Course course1 = new Course("Java Programming", "Programming", 5, "Learn Java.");
        Course course2 = new Course("Python Programming", "Programming", 5, "Learn Python.");
        when(courseService.getCourses()).thenReturn(Arrays.asList(course1, course2));

        // Act
        Iterable<Course> result = courseController.getAllCourses();

        // Assert
        assertNotNull(result);
        assertEquals(2, ((Collection<Course>) result).size());
        verify(courseService, times(1)).getCourses();
    }

    @Test
    void testGetCourseById_Exists() {
        // Arrange
        Course course = new Course("Java Programming", "Programming", 5, "Learn Java.");
        when(courseService.getCourseById(1L)).thenReturn(Optional.of(course));

        // Act
        Optional<Course> result = courseController.getCourseById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(course, result.get());
        verify(courseService, times(1)).getCourseById(1L);
    }

    @Test
    void testGetCourseById_NotExists() {
        // Arrange
        when(courseService.getCourseById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Course> result = courseController.getCourseById(1L);

        // Assert
        assertFalse(result.isPresent());
        verify(courseService, times(1)).getCourseById(1L);
    }

    @Test
    void testGetCourseByCategory() {
        // Arrange
        Course course = new Course("Java Programming", "Programming", 5, "Learn Java.");
        when(courseService.getCoursesByCategory("Programming")).thenReturn(Arrays.asList(course));

        // Act
        Iterable<Course> result = courseController.getCourseByCategory("Programming");

        // Assert
        assertNotNull(result);
        assertEquals(1, ((Collection<Course>) result).size());
        verify(courseService, times(1)).getCoursesByCategory("Programming");
    }

    @Test
    void testCreateCourse() {
        // Arrange
        Course course = new Course("Java Programming", "Programming", 5, "Learn Java.");
        when(courseService.createCourse(any(Course.class))).thenReturn(course);

        // Act
        Course result = courseController.createCourse(course);

        // Assert
        assertNotNull(result);
        assertEquals(course, result);
        verify(courseService, times(1)).createCourse(course);
    }

    @Test
    void testUpdateCourse() {
        // Arrange
        Course course = new Course("Updated Course", "Updated Category", 4, "Updated Description");

        // Act
        courseController.updateCourse(1L, course);

        // Assert
        verify(courseService, times(1)).updateCourse(1L, course);
    }

    @Test
    void testDeleteCourseById() {
        // Act
        courseController.deleteCourseById(1L);

        // Assert
        verify(courseService, times(1)).deleteCourseById(1L);
    }

    @Test
    void testDeleteCourses() {
        // Act
        courseController.deleteCourses();

        // Assert
        verify(courseService, times(1)).deleteCourses();
    }
}