package com.springboot.clienteapp.models.repository;

import com.springboot.clienteapp.models.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    //Role findByUser_id(int ud);

    //void deleteByUsers_id(int usersId);

}
