package com.example.usermanager.service;

import com.example.usermanager.exception.UserNotFoundException;
import com.example.usermanager.model.User;
import com.example.usermanager.repository.UserRepository;
import com.example.usermanager.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User("Test User", "123 Test St", "555-1234", "test@example.com");
        testUser.setId(1L);
    }

    @Test
    void list_ShouldReturnAllUsers() {
        // Arrange
        List<User> expectedUsers = Arrays.asList(
                testUser,
                new User("Another User", "456 Test Ave", "555-5678", "another@example.com")
        );
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<User> actualUsers = userService.list();

        // Assert
        assertEquals(expectedUsers, actualUsers);
        verify(userRepository).findAll();
    }

    @Test
    void find_WithExistingId_ShouldReturnUser() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        // Act
        Optional<User> result = userService.find(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(testUser, result.get());
        verify(userRepository).findById(1L);
    }

    @Test
    void create_ShouldSaveAndReturnUser() {
        // Arrange
        User newUser = new User("New User", "789 New St", "555-9012", "new@example.com");
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        // Act
        User result = userService.create(newUser);

        // Assert
        assertEquals(newUser, result);
        verify(userRepository).save(newUser);
    }

    @Test
    void update_WithExistingId_ShouldUpdateAndReturnUser() {
        // Arrange
        User updatedUser = new User("Updated User", "Updated Address", "Updated Number", "updated@example.com");
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // Act
        User result = userService.update(1L, updatedUser);

        // Assert
        assertEquals(updatedUser, result);
        verify(userRepository).existsById(1L);
        verify(userRepository).save(updatedUser);
    }

    @Test
    void update_WithNonExistingId_ShouldThrowException() {
        // Arrange
        User updatedUser = new User("Updated User", "Updated Address", "Updated Number", "updated@example.com");
        when(userRepository.existsById(999L)).thenReturn(false);

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.update(999L, updatedUser);
        });
        verify(userRepository).existsById(999L);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void delete_WithExistingId_ShouldReturnTrue() {
        // Arrange
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(anyLong());

        // Act
        boolean result = userService.delete(1L);

        // Assert
        assertTrue(result);
        verify(userRepository).existsById(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void delete_WithNonExistingId_ShouldThrowException() {
        // Arrange
        when(userRepository.existsById(999L)).thenReturn(false);

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.delete(999L);
        });
        verify(userRepository).existsById(999L);
        verify(userRepository, never()).deleteById(anyLong());
    }
}