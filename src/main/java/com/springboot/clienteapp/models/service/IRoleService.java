package com.springboot.clienteapp.models.service;

import com.springboot.clienteapp.models.entity.Role;

public interface IRoleService {

    public void guardar(Role role);

    Role encontrarUserIdAsociado(long id);

    public void eliminarRole(long id);
}
