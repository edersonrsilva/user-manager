package com.example.usermanager.controller;

import com.example.usermanager.exception.UserNotFoundException;
import com.example.usermanager.model.User;
import com.example.usermanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User("Test User", "123 Test St", "555-1234", "test@example.com");
        testUser.setId(1L);
    }

    @Test
    void getAllUsers_ShouldReturnAllUsers() {
        // Arrange
        List<User> expectedUsers = Arrays.asList(
                testUser,
                new User("Another User", "456 Test Ave", "555-5678", "another@example.com")
        );
        when(userService.list()).thenReturn(expectedUsers);

        // Act
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, response.getBody());
        verify(userService).list();
    }

    @Test
    void getUserById_WithExistingId_ShouldReturnUser() {
        // Arrange
        when(userService.find(1L)).thenReturn(Optional.of(testUser));

        // Act
        ResponseEntity<User> response = userController.getUserById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
        verify(userService).find(1L);
    }

    @Test
    void getUserById_WithNonExistingId_ShouldReturnNotFound() {
        // Arrange
        when(userService.find(999L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<User> response = userController.getUserById(999L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService).find(999L);
    }

    @Test
    void createUser_ShouldReturnCreatedUser() {
        // Arrange
        User newUser = new User("New User", "789 New St", "555-9012", "new@example.com");
        when(userService.create(any(User.class))).thenReturn(newUser);

        // Act
        ResponseEntity<User> response = userController.createUser(newUser);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newUser, response.getBody());
        verify(userService).create(newUser);
    }

    @Test
    void updateUser_WithExistingId_ShouldReturnUpdatedUser() {
        // Arrange
        User updatedUser = new User("Updated User", "Updated Address", "Updated Number", "updated@example.com");
        when(userService.update(eq(1L), any(User.class))).thenReturn(updatedUser);

        // Act
        ResponseEntity<User> response = userController.updateUser(1L, updatedUser);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
        verify(userService).update(eq(1L), any(User.class));
    }

    @Test
    void updateUser_WithNonExistingId_ShouldThrowException() {
        // Arrange
        User updatedUser = new User("Updated User", "Updated Address", "Updated Number", "updated@example.com");
        when(userService.update(eq(999L), any(User.class)))
                .thenThrow(new UserNotFoundException(999L));

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userController.updateUser(999L, updatedUser);
        });
        verify(userService).update(eq(999L), any(User.class));
    }

    @Test
    void deleteUser_WithExistingId_ShouldReturnNoContent() {
        // Arrange
        doReturn(true).when(userService).delete(1L);

        // Act
        ResponseEntity<Void> response = userController.deleteUser(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService).delete(1L);
    }

    @Test
    void deleteUser_WithNonExistingId_ShouldThrowException() {
        // Arrange
        doThrow(new UserNotFoundException(999L)).when(userService).delete(999L);

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userController.deleteUser(999L);
        });
        verify(userService).delete(999L);
    }
}