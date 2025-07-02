package com.aplicacion.aplicacionfx.servicio;

import com.aplicacion.aplicacionfx.cliente.api.UsuarioApiClient;
import com.aplicacion.aplicacionfx.cliente.dto.UsuarioDTO;
import com.aplicacion.aplicacionfx.modelo.UsuarioUI;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class UsuarioServicio {
    private final UsuarioApiClient apiClient;

    public UsuarioServicio(){
        this.apiClient = new UsuarioApiClient();
    }

    // --- Métodos de Conversión ---

    // Convierte de UsuarioDTO a UsuarioUI
    private UsuarioUI convertToUsuarioUI(UsuarioDTO dto) {
        return new UsuarioUI(
                Long.toString(dto.getId()),
                dto.getNombre(),
                dto.getEmail(),
                dto.getPasswordHash(),
                dto.getRol(),
                dto.isActivo()
        );
    }

    // Convierte de UsuarioUI a UsuarioDTO
    private UsuarioDTO convertToUsuarioDTO(UsuarioUI ui) {

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                null,
                ui.getNombre(),
                ui.getEmail(),
                ui.getPasswordHash(),
                ui.getRol(),
                ui.isActivo()
        );
        if (ui.getId() != null){
           usuarioDTO.setId(Long.parseLong(ui.getId()));
        }
        return usuarioDTO;
    }

    public UsuarioUI registrarUsuario(UsuarioUI nuevoUsuario) throws Exception {
        UsuarioDTO usuarioDTO = convertToUsuarioDTO(nuevoUsuario);
        apiClient.createUsuario(usuarioDTO);
        return nuevoUsuario;
    }

    public ObservableList<UsuarioUI> obtenerTodosLosUsuarios() throws IOException {
        List<UsuarioDTO> usuariosDTO = apiClient.getAllUsuarios();

        List<UsuarioUI> usuariosUI = new ArrayList<>();
        for (UsuarioDTO u : usuariosDTO){
            usuariosUI.add(convertToUsuarioUI(u));
        }
        final ObservableList<UsuarioUI> observableList = FXCollections.observableArrayList();
        Platform.runLater(() -> observableList.setAll(usuariosUI)); // Asegura que la ObservableList se actualice en el hilo FX
        return observableList;
    }

    public UsuarioUI obtenerUsuarioPorId(String id) throws Exception{
        if (id == null || id.trim().isEmpty() || "null".equalsIgnoreCase(id.trim())){
            throw new IllegalArgumentException("El ID proporcionado no es valido para obtener un usuario");
        }
        Long idLong = null;
        try {
            idLong = Long.parseLong(id.trim());
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El ID proporionado no es un número valido: " + id, e);
        }
        Optional<UsuarioDTO> optionalUsuarioDTO;
        try {
            optionalUsuarioDTO = apiClient.getUsuarioById(idLong);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (optionalUsuarioDTO.isPresent()){
            UsuarioDTO usuarioDTO = optionalUsuarioDTO.get();
            UsuarioUI usuarioUI = convertToUsuarioUI(usuarioDTO);
            return usuarioUI;
        } else {
            return null;
        }
    }

    public UsuarioUI actualizarUsuario(UsuarioUI usuarioActualizado) throws Exception{
        UsuarioDTO usuarioDTO = convertToUsuarioDTO(usuarioActualizado);
        apiClient.updateUsuario(usuarioDTO);
        return usuarioActualizado;
    }

    public void eliminarUsuario(String id) throws Exception{
        if (id == null || id.trim().isEmpty() || "null".equalsIgnoreCase(id.trim())) {
            throw new IllegalArgumentException("El ID proporcionado no es válido para eliminar un usuario.");
        }
        Long idLong = null;
        try {
            idLong = Long.parseLong(id.trim()); // <-- ¡Usa parseLong o valueOf!
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El ID proporcionado no es un número válido: " + id, e);
        }
        apiClient.deleteUsuario(idLong);
    }
}
