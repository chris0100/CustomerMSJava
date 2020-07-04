package com.springboot.clienteapp;

import com.springboot.clienteapp.util.LoginSuccessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(securedEnabled = true) //para cuando se asignan permisos desde metodos
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private LoginSuccessMessage successMessage;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/index","/home","/css/**","/js/**","/images/**","/registro","/validaregistro","/mostrarUsuarios").permitAll()
//                .antMatchers("/views/clientes").hasAnyRole("USER")
//                .antMatchers("/views/clientes/create").hasAnyRole("ADMIN")
//                .antMatchers("/views/clientes/save").hasAnyRole("ADMIN")
//                .antMatchers("/views/clientes/edit/**").hasAnyRole("ADMIN")
//                .antMatchers("/views/clientes/delete/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .successHandler(successMessage)
                    .loginPage("/login")
                .permitAll()//indica que aparezca el formulario de login
                .and()
                .logout().permitAll();
    }



    @Autowired
    public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception{
        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("SELECT username, password, enable FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT u.username, r.rol FROM roles r INNER JOIN users u ON r.users_id=u.id WHERE u.username=?");
    }
}
