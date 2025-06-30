package com.aplicacion.aplicacionfx.servicio;

import com.aplicacion.aplicacionfx.cliente.api.UsuarioApiClient;
import com.aplicacion.aplicacionfx.cliente.dto.UsuarioDTO;
import com.aplicacion.aplicacionfx.modelo.UsuarioUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;
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
                Long.toString(dto.getId());
                dto.getNombre(),
                dto.getEmail(),
                dto.getPasswordHash(),
                dto.getRol(),
                dto.isActivo()
        );
    }

    // Convierte de UsuarioUI a UsuarioDTO
    private UsuarioDTO convertToUsuarioDTO(UsuarioUI ui) {
        return new UsuarioDTO(
                Long.parseLong(ui.getId()),
                ui.getNombre(),
                ui.getEmail(),
                ui.getPasswordHash(),
                ui.getRol(),
                ui.isActivo()
        );
    }

    public UsuarioUI registrarUsuario(UsuarioUI nuevoUsuario, String password) throws Exception {
        return null;
    }

    public ObservableList<UsuarioUI> obtenerTodosLosUsuarios(){
        return null;
    }

    public UsuarioUI obtenerUsuarioPorId(String id){
        return null;
    }

    public UsuarioUI actualizarUsuario(UsuarioUI usuarioActualizado) throws Exception{
        return null;
    }

    public void eliminarUsuario(String id) throws Exception{

    }
}
