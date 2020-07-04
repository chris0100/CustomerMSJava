package com.springboot.clienteapp.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @SequenceGenerator(
            name="role_seq",
            sequenceName = "role_seq",
            initialValue = 4,
            allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "role_seq")
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Usuario user_id;


    @NotEmpty
    private String rol;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Usuario getUser_id() {
        return user_id;
    }

    public void setUser_id(Usuario user_id) {
        this.user_id = user_id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", rol='" + rol + '\'' +
                '}';
    }
}
