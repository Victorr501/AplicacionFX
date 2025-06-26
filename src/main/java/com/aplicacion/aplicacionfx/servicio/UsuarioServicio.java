package com.aplicacion.aplicacionfx.servicio;

import com.aplicacion.aplicacionfx.modelo.UsuarioUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UsuarioServicio {
    //Esto es una simulacion de una base de datos
    private static Map<String, UsuarioUI> usuariosRegistrados = new HashMap<>();
    private static AtomicLong idCounter = new AtomicLong(1); // Para asignar IDs unicos sumulados

    public UsuarioServicio(){
        if (usuariosRegistrados.isEmpty()){
            try {
                registrarUsuario(new UsuarioUI(null, "Admin User", "Admin@example.com", "Adiminstrador", true), "1234");
                registrarUsuario(new UsuarioUI(null, "Normal User", "Normal@example.com", "Usuario", true), "1234");
            } catch (Exception e) {
                System.err.println("Error al inicializar usuario de prueba: " + e.getMessage());
            }
        }
    }

    public UsuarioUI registrarUsuario(UsuarioUI nuevoUsuario, String password) throws Exception {
        if (nuevoUsuario == null){
            throw new IllegalArgumentException("El objeto usuario no pued ser nulo");
        }

        if (password == null || password.isEmpty()){
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        for (UsuarioUI u : usuariosRegistrados.values()){
            if (u.getEmail().equalsIgnoreCase(nuevoUsuario.getEmail())){
                throw new Exception("El email " + nuevoUsuario.getEmail() + " ya esta en uso");
            }
        }

        //Esto es para un ejemplo basico pero aqui habria que hashear la contraseña
        String hashedPassword = "HASHED_" + password;

        //Asignar un Id al nuevo usaurio(Siumulado)
        nuevoUsuario.setId("USR" + idCounter.getAndIncrement());

        //Simular guardar en memoria
        usuariosRegistrados.put(nuevoUsuario.getId(), nuevoUsuario);
        System.out.println("Servicio: Usuariao registrado y guardado en memoria:");

        return nuevoUsuario;
    }

    public ObservableList<UsuarioUI> obtenerTodosLosUsuarios(){
        return FXCollections.observableArrayList(usuariosRegistrados.values());
    }

    public UsuarioUI obtenerUsuarioPorId(String id){
        return usuariosRegistrados.get(id);
    }

    public UsuarioUI actualizarUsuario(UsuarioUI usuarioActualizado) throws Exception{
        if (usuarioActualizado == null || usuarioActualizado.getId() == null){
            throw new IllegalArgumentException("Usuario o ID nulo para actualizar");
        }

        if (!usuariosRegistrados.containsKey(usuarioActualizado.getId())){
            throw new Exception("Usuario con ID " + usuarioActualizado.getId() );
        }

        //No se actualiza la contraseaña seria un metodo separadao.
        usuariosRegistrados.put(usuarioActualizado.getId(), usuarioActualizado);
        System.out.println("Servicio: Usuario actualizado en memoria:");
        return usuarioActualizado;
    }

    public void eliminarUsuario(String id) throws Exception{
        if (!usuariosRegistrados.containsKey(id)){
            throw new Exception("Usuario con Id " + id + " no encotrado para la aliminacion");
        }
        usuariosRegistrados.remove(id);
        System.out.println("Servicio: Ussuario con Id " + id + " elemento de la memoria actualizado" );
    }
}
