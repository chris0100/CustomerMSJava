package com.springboot.clienteapp.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name="users")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name="usuario_sequ",
            sequenceName = "usuario_sequ",
            initialValue = 13,
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "usuario_sequ")
    @Column(name = "id", updatable = false)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    @Email
    private String username;

    @NotEmpty
    private String password;

    @Column(columnDefinition = "integer default 1")
    private int enable;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    private String telefono;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enable=" + enable +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
