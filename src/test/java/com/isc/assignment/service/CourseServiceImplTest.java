package com.isc.assignment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.isc.assignment.exception.CourseNotFoundException;
import com.isc.assignment.model.Course;
import com.isc.assignment.repository.CourseRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

class CourseServiceImplTest {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCourse() {
        // Arrange
        Course course = new Course("Java Programming", "Programming", 5, "Learn Java.");
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        // Act
        Course result = courseService.createCourse(course);

        // Assert
        assertNotNull(result);
        assertEquals("Java Programming", result.getName());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testGetCourseById_Exists() {
        // Arrange
        Course course = new Course("Java Programming", "Programming", 5, "Learn Java.");
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        // Act
        Optional<Course> result = courseService.getCourseById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(course, result.get());
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCourseById_NotExists() {
        // Arrange
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        CourseNotFoundException exception = assertThrows(CourseNotFoundException.class, () -> {
            courseService.getCourseById(1L);
        });
        assertEquals("No course with id 1 is available", exception.getMessage());
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCoursesByCategory() {
        // Arrange
        Course course = new Course("Java Programming", "Programming", 5, "Learn Java.");
        when(courseRepository.findAllByCategory("Programming")).thenReturn(Arrays.asList(course));

        // Act
        Iterable<Course> result = courseService.getCoursesByCategory("Programming");

        // Assert
        assertNotNull(result);
        assertEquals(1, ((Collection<Course>) result).size());
        verify(courseRepository, times(1)).findAllByCategory("Programming");
    }

    @Test
    void testGetCourses() {
        // Arrange
        Course course1 = new Course("Java Programming", "Programming", 5, "Learn Java.");
        Course course2 = new Course("Python Programming", "Programming", 5, "Learn Python.");
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2));

        // Act
        Iterable<Course> result = courseService.getCourses();

        // Assert
        assertNotNull(result);
        assertEquals(2, ((Collection<Course>) result).size());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void testUpdateCourse() {
        // Arrange
        Course existingCourse = new Course("Java Programming", "Programming", 5, "Learn Java.");
        Course updateData = new Course("Updated Course", "Programming", 5, "Updated description.");
        when(courseRepository.findById(1L)).thenReturn(Optional.of(existingCourse));
        when(courseRepository.save(any(Course.class))).thenReturn(existingCourse);

        // Act
        Course result = courseService.updateCourse(1L, updateData);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Course", result.getName());
        verify(courseRepository, times(1)).findById(1L);
        verify(courseRepository, times(1)).save(existingCourse);
    }

    @Test
    void testUpdateCourse_NotExists() {
        // Arrange
        Course updateData = new Course("Updated Course", "Programming", 5, "Updated description.");
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        CourseNotFoundException exception = assertThrows(CourseNotFoundException.class, () -> {
            courseService.updateCourse(1L, updateData);
        });
        assertEquals("No course with id 1 is available", exception.getMessage());
        verify(courseRepository, times(1)).findById(1L);
    }

//    @Test
//    void testDeleteCourseById() {
//        // Arrange
//        Course course = new Course("Java Programming", "Programming", 5, "Learn Java.");
//        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
//
//        // Act
//        courseService.deleteCourseById(1L);
//
//        // Assert
//        verify(courseRepository, times(1)).deleteById(1L);
//    }

    @Test
    void testDeleteCourseById_NotExists() {
        // Arrange
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        CourseNotFoundException exception = assertThrows(CourseNotFoundException.class, () -> {
            courseService.deleteCourseById(1L);
        });
        assertEquals("No course with id 1 is available", exception.getMessage());
        verify(courseRepository, never()).deleteById(anyLong());
    }

    @Test
    void testDeleteCourses() {
        // Act
        courseService.deleteCourses();

        // Assert
        verify(courseRepository, times(1)).deleteAll();
    }
}