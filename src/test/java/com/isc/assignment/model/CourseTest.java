package com.isc.assignment.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    void testCourseCreation() {
        // Arrange
        String name = "Java Programming";
        String category = "Programming";
        int rating = 5;
        String description = "An in-depth course on Java.";

        // Act
        Course course = new Course(name, category, rating, description);

        // Assert
        assertNotNull(course);
        assertEquals(name, course.getName());
        assertEquals(category, course.getCategory());
        assertEquals(rating, course.getRating());
        assertEquals(description, course.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Course course1 = new Course("Java Programming", "Programming", 5, "An in-depth course on Java.");
        Course course2 = new Course("Java Programming", "Programming", 5, "An in-depth course on Java.");
        Course course3 = new Course("Python Programming", "Programming", 4, "An in-depth course on Python.");

        // Act & Assert
        assertEquals(course1, course2); // Same name and category should be equal
        assertNotEquals(course1, course3); // Different names should not be equal

        // Hash code should be equal for equivalent objects
        assertEquals(course1.hashCode(), course2.hashCode());
        assertNotEquals(course1.hashCode(), course3.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        Course course = new Course("Java Programming", "Programming", 5, "An in-depth course on Java.");

        // Act
        String result = course.toString();

        // Assert
        assertTrue(result.contains("Java Programming"));
        assertTrue(result.contains("Programming"));
        assertTrue(result.contains("5"));
        assertTrue(result.contains("An in-depth course on Java."));
    }
}