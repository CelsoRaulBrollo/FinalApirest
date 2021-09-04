package com.info.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import com.info.apirest.models.Usuario;
import com.info.apirest.services.UsuarioService;

@RestController
public class UsuarioController {

    @Autowired 
    private UsuarioService usuarioService;

    @GetMapping("api/v1/usuario")
    public ResponseEntity<?> getUsuario(){
        return new ResponseEntity<>(usuarioService.buscarUsuario(), HttpStatus.OK);
    }

    @PostMapping("api/v1/usuario")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.crearUsuario(usuario), HttpStatus.CREATED);
    }

    @DeleteMapping("api/v1/usuario/{id}/eliminar")
    public String eliminarUsuario(@PathVariable ("id") Long id){
        Usuario usuario = usuarioService.tomarUnUsuario(id);
        if (usuario.getNombre().isBlank()){
            return "No se pudo eliminar el usuario con id " + id;
        }
        usuarioService.eliminarUnUsuario(usuario); 
        return "Se eliminó el usuario con id " + id;
    }
 
    @GetMapping("api/v1/usuario/buscar/ciudad")
    public ResponseEntity<?> buscarUsuarioCiudad(String ciudad){
        return new ResponseEntity<>(usuarioService.usuarioPorCiudad(ciudad),HttpStatus.OK);
    }

    @GetMapping("api/v1/usuario/buscar")
    public ResponseEntity<?> buscarUsuarioFecha(@RequestParam(name = "fechaCreacion", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaCreacion){
        return new ResponseEntity<>(usuarioService.buscarUsuarioPorFechaCreacion(fechaCreacion),HttpStatus.OK);
    }
    
    @PutMapping("api/v1/usuario/{id}/modificar")
    public ResponseEntity<?> modificarUsuario (@RequestBody Usuario usuario, @PathVariable ("id") Long id){
        Usuario usuarioNuevo = usuarioService.tomarUnUsuario(id);
        usuarioNuevo.setId(id);
        usuarioNuevo.setNombre(usuario.getNombre());
        usuarioNuevo.setApellido(usuario.getApellido());
        usuarioNuevo.setEmail(usuario.getEmail());
        usuarioNuevo.setPassword(usuario.getPassword());
        usuarioNuevo.setFechaCreacion(usuario.getFechaCreacion());
        usuarioNuevo.setCiudad(usuario.getCiudad());
        usuarioNuevo.setProvincia(usuario.getProvincia());
        usuarioNuevo.setPais(usuario.getPais());
    
        return new ResponseEntity<>(usuarioService.updateUsuario(usuarioNuevo),HttpStatus.CREATED);
    }
}
