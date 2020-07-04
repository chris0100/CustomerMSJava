package com.springboot.clienteapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model,
                        Principal principal, RedirectAttributes atributo,
                        @RequestParam(value = "logout", required = false) String logout){

        if(error != null){
            model.addAttribute("error", "ERROR DE ACCESO: Usuario y/o contraseña incorrectos");
        }

        //verifica si un usuario ya inicio sesion
        if(principal != null){
           atributo.addFlashAttribute("warning", "ATENCION: Ud ya ha iniciado sesión previamente");
           return "redirect:/index";
        }


        if(logout != null){
            model.addAttribute("success", "ATENCION: Ha finalizado sesión con exito");
        }
        return "login";
    }

}
