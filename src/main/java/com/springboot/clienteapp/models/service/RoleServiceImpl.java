package com.springboot.clienteapp.models.service;

import com.springboot.clienteapp.models.entity.Role;
import com.springboot.clienteapp.models.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleRepository roleRepositoryObj;

    @Override
    public void guardar(Role role) {
        roleRepositoryObj.save(role);
    }

    @Override
    public Role encontrarUserIdAsociado(long id) {
        return roleRepositoryObj.findById(id).get();
    }

    @Override
    public void eliminarRole(long id) {
        roleRepositoryObj.deleteById(id);
    }
}
