package com.aplicacion.aplicacionfx.cliente.api;


 // ¡Importa el DTO!
import com.aplicacion.aplicacionfx.cliente.dto.UsuarioDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; // Para la conversión

public class UsuarioApiClient {

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private static final String BASE_URL = "http://localhost:8080/api/usuarios";

    // Obtener todos los usuarios
    public List<UsuarioDTO> getAllUsuarios() throws IOException { // Devuelve List<UsuarioDTO>
        Request request = new Request.Builder()
                .url(BASE_URL)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                Type listType = new TypeToken<List<UsuarioDTO>>() {}.getType(); // Deserializa a List<UsuarioDTO>
                return gson.fromJson(response.body().string(), listType);
            }
            System.err.println("Error al obtener usuarios: " + response.code() + " - " + response.message());
            return Collections.emptyList();
        }
    }

    // Obtener un usuario por ID
    public Optional<UsuarioDTO> getUsuarioById(Long id) throws IOException { // Devuelve Optional<UsuarioDTO>
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + id)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return Optional.of(gson.fromJson(response.body().string(), UsuarioDTO.class));
            }
            System.err.println("Error al obtener usuario por ID " + id + ": " + response.code() + " - " + response.message());
            return Optional.empty();
        }
    }

    // Crear un usuario
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws IOException { // Recibe UsuarioDTO
        String json = gson.toJson(usuario);
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return gson.fromJson(response.body().string(), UsuarioDTO.class);
            }
            System.err.println("Error al crear usuario: " + response.code() + " - " + response.message());
            throw new IOException("Fallo al crear usuario: " + response.message());
        }
    }

    // Actualizar un usuario
    public UsuarioDTO updateUsuario(UsuarioDTO usuario) throws IOException { // Recibe UsuarioDTO
        if (usuario.getId() == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo para actualizar.");
        }
        String json = gson.toJson(usuario);
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(BASE_URL + "/" + usuario.getId())
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return gson.fromJson(response.body().string(), UsuarioDTO.class);
            }
            System.err.println("Error al actualizar usuario: " + response.code() + " - " + response.message());
            throw new IOException("Fallo al actualizar usuario: " + response.message());
        }
    }

    // Eliminar un usuario
    public boolean deleteUsuario(Long id) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + id)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return true;
            }
            System.err.println("Error al eliminar usuario: " + response.code() + " - " + response.message());
            return false;
        }
    }
}
