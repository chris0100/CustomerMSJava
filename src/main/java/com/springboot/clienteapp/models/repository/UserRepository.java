package com.springboot.clienteapp.models.repository;

import com.springboot.clienteapp.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    void deleteById(int id);


}
