package com.springboot.clienteapp.controller;

import com.springboot.clienteapp.models.entity.Role;
import com.springboot.clienteapp.models.entity.Usuario;
import com.springboot.clienteapp.models.service.IRoleService;
import com.springboot.clienteapp.models.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
public class HomeController {

	@Autowired
	private IUsuarioService usuarioServiceObj;

	@Autowired
	private IRoleService roleServiceObj;

	@Autowired
    private BCryptPasswordEncoder encoder;


	
	@GetMapping({"/index","/home","/"})
	public String index() {
		return "home";
	}

	@GetMapping("/registro")
	public String registro(@ModelAttribute("usuario") Usuario usuario){
		return "registro";
	}

	//@RequestMapping(value="validaregistro", method = RequestMethod.POST)
	@PostMapping("/validaregistro")
	public String validarRegistro(@Valid @ModelAttribute Usuario usuario, BindingResult result,
								   RedirectAttributes atributo){

		if(result.hasErrors()){
			System.out.println("existen errores en la creacion");
			return "/home";
		}

		usuario.setEnable(1);
		usuario.setPassword(encoder.encode(usuario.getPassword())); //convertir a contraseña encriptada
		usuarioServiceObj.guardar(usuario);

		//se crea objeto role para enlazar a los permisos que tendra el usuario.
		Role objRole= new Role();
		objRole.setRol("ROLE_USER");
		objRole.setUser_id(usuario);
		roleServiceObj.guardar(objRole);

		System.out.println("usuario guardado con exito");
		atributo.addFlashAttribute("success","Usuario guardado con exito");
		return "redirect:login";
	}



//*************************************************************************
//*****************************CAMBIO DE CONTRASEÑA************************
//*************************************************************************

	@GetMapping("/cambiarpassword")
    public String cambiarPassword(){
	    return "cambiarPassword";
    }



	@PostMapping("/validarcambiarpassword")
    public String validarCambiarPaswword(@RequestParam(value = "password") String password, RedirectAttributes atributo, Principal principal){
        Usuario usuarioLogueado = usuarioServiceObj.obtenerUsuario(principal.getName());

	    usuarioLogueado.setPassword(encoder.encode(password));
	    usuarioServiceObj.guardar(usuarioLogueado);
        atributo.addFlashAttribute("success","Contraseña cambiada con exito");

	    return "redirect:home";
    }


//*************************************************************************
//*****************************GESTION DE USUARIOS************************
//*************************************************************************

	@GetMapping("/mostrarusuarios")
	public String mostrarUsuarios(Model model){

		List<Usuario> listaUsuarios = usuarioServiceObj.listaUsuarios();
		model.addAttribute("usuarios", listaUsuarios);
		return "users/listarUsuarios";
	}

	@GetMapping("/crearusuarios")
	public String crearUsuarios(Model model){
		Usuario user = new Usuario();
		model.addAttribute("usuario",user);
		model.addAttribute("titulo","Crear nuevo Usuario");
		return "users/formUsuarios";
	}

	@PostMapping("/save")
	public String guardarUsuario(@Valid @ModelAttribute Usuario usuario){
		String password = encoder.encode(usuario.getPassword());
		usuario.setPassword(password);
		usuario.setEnable(1);
		usuarioServiceObj.guardar(usuario);
		System.out.println("datos usuario a registrar" + usuario);
		return "redirect:/mostrarusuarios";
	}


	@GetMapping("/editarusuarios/{id}")
	public String editarUsuarios(@PathVariable("id") Long id, Model model){
		Usuario usuarioToEdit = usuarioServiceObj.obtenerUsuarioPorId(id);
		model.addAttribute("usuario", usuarioToEdit);
		model.addAttribute("titulo","Editar Usuario");
		return "users/formUsuarios";
	}

	@GetMapping("/eliminarusuarios/{id}")
	public String eliminarUsuario(@PathVariable("id") long id){

		Role buscarRolAsociado = roleServiceObj.encontrarUserIdAsociado(id);
		System.out.println(buscarRolAsociado);
		roleServiceObj.eliminarRole(id);
		System.out.println("el id de usuarioa borrar es: " + id);
		usuarioServiceObj.eliminarUsuario(id);
		return "redirect:/mostrarusuarios";
	}


}
