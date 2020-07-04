package com.springboot.clienteapp.models.service;

import com.springboot.clienteapp.models.entity.Usuario;
import com.springboot.clienteapp.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    UserRepository userRepositoryObj;

    @Override
    public Usuario obtenerUsuario(String username) {
        return userRepositoryObj.findByUsername(username);
    }

    @Override
    public void guardar(Usuario usuario) {
        userRepositoryObj.save(usuario);
    }

    @Override
    public List<Usuario> listaUsuarios() {
        return (List<Usuario>) userRepositoryObj.findAll();
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return userRepositoryObj.findById(id).get();
    }

    @Override
    public void eliminarUsuario(long id) {
        userRepositoryObj.deleteById(id);
    }
}
