package com.springboot.clienteapp.models.service;

import com.springboot.clienteapp.models.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    public void guardar(Usuario usuario);

    public Usuario obtenerUsuario(String username);

    public List<Usuario> listaUsuarios();

    public Usuario obtenerUsuarioPorId(Long id);

    void eliminarUsuario(long id);
}
