package com.CrudMongoDBAtlas.CrudMongoDBAtlas.service;

import com.CrudMongoDBAtlas.CrudMongoDBAtlas.dto.UserDto;
import com.CrudMongoDBAtlas.CrudMongoDBAtlas.entity.User;
import com.CrudMongoDBAtlas.CrudMongoDBAtlas.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;
    @InjectMocks
    @Spy
    private UserService userService;

    private User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @DisplayName("Test para guardar usuario")
    @Test
    public void testAddSaveUser() {

        User user = new User();

        user.builder()
                .userId(1038804944)
                .userName("Hoiverd")
                .userLastName("Valaencia Rqubio")
                .userEmail("hoiver7315@hotmail.com")
                .userAge(32)
                .userGender("M")
                .userPassword(436123)
                .build();

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.addUser(user);

        assertNotNull(result);
    }
    @Test
    public void testAddSaveUserWhenErroAddUserThenReturnException(){

        User user = new User();

        user.builder()
                .userId(1038804944)
                .userName("Hoiverd")
                .userLastName("Valaencia Rqubio")
                .userEmail("")
                .userAge(32)
                .userGender("M")
                .userPassword(436123)
                .build();

        when(userRepository.save(user)).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class,() -> userService.addUser(user));

    }
    @Test
    public void testFilterUsersByCriteria() {
        // Crear usuarios de ejemplo
        User user1 = User.builder()
                .userId(1038804944)
                .userName("Usuario1")
                .userLastName("Apellido1")
                .userEmail("correo1@example.com")
                .userAge(25)
                .userGender("M")
                .userPassword(436123)
                .build();
        User user = User.builder()
                .userId(1038804951)
                .userName("Usuario2")
                .userLastName("Apellido2")
                .userEmail("correo5561@example.com")
                .userAge(33)
                .userGender("F")
                .userPassword(46123)
                .build();

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

        // Configurar el comportamiento simulado del repositorio
        when(userRepository.findAll()).thenReturn(userList);

        // Llamar al método de filtro en el servicio
        List<UserDto> filteredUsers = userService.filterUsersByCriteria(null, "Apellido2", null, null, null);

        // Verificar que se obtuvieron los usuarios filtrados correctamente
        assertEquals(1, filteredUsers.size());
        assertEquals("Usuario2", filteredUsers.get(0).getUserName());
        assertEquals("Apellido2", filteredUsers.get(0).getUserLastName());
        assertEquals("correo5561@example.com", filteredUsers.get(0).getUserEmail());
        assertEquals(33, filteredUsers.get(0).getUserAge());
        assertEquals("F", filteredUsers.get(0).getUserGender());
    }
    @Test
    public void testUpdateUser() {
        // Crear un usuario de ejemplo para la solicitud de actualización
        User userRequest = new User();
        userRequest.setUserId(1);
        userRequest.setUserName("NuevoUsuario");
        userRequest.setUserLastName("NuevoApellido");
        userRequest.setUserEmail("nuevo.correo@example.com");
        userRequest.setUserAge(30);
        userRequest.setUserGender("F");
        userRequest.setUserPassword(4);

        // Crear un usuario existente
        User existingUser = new User();
        existingUser.setUserId(1);
        existingUser.setUserName("UsuarioExistente");
        existingUser.setUserLastName("ApellidoExistente");
        existingUser.setUserEmail("correo.existente@example.com");
        existingUser.setUserAge(25);
        existingUser.setUserGender("M");
        existingUser.setUserPassword(5);

        // Configurar el comportamiento simulado del repositorio
        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));


        // Llamar al método de actualización en el servicio
        User updatedUser = userService.UpdateUser(userRequest);

        // Verificar que los detalles del usuario se actualizaron correctamente
        assertEquals("NuevoUsuario", updatedUser.getUserName());
        assertEquals("NuevoApellido", updatedUser.getUserLastName());
        assertEquals("nuevo.correo@example.com", updatedUser.getUserEmail());
        assertEquals(30, updatedUser.getUserAge());
        assertEquals("F", updatedUser.getUserGender());
        assertEquals(4, updatedUser.getUserPassword()); // Asegúrate de que la contraseña sea correcta
    }

    @Test
    public void testDeleteUser() {
        // ID del usuario a eliminar
        Integer userIdToDelete = 1;

        // Llamar al método de eliminación en el servicio
        userService.deleterUser(userIdToDelete);

        // Verificar que el método deleteById se llamó con el ID correcto
        verify(userRepository).deleteById(userIdToDelete);
    }

}