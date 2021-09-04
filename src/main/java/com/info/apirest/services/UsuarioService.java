package com.info.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.info.apirest.dto.UsuarioDto;
import com.info.apirest.models.Usuario;
import com.info.apirest.repositories.UsuarioRepo;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    public UsuarioDto crearUsuario(Usuario nuevoUsuario){
        return new UsuarioDto(usuarioRepo.save(nuevoUsuario));
    } 

    public List<UsuarioDto> buscarUsuario(){
        return usuarioRepo.findAll().stream().map(UsuarioDto::new).collect(Collectors.toList());
    }
 
    public Usuario tomarUnUsuario(Long idUsuario){
        return usuarioRepo.getById(idUsuario);
    }

    public UsuarioDto updateUsuario(Usuario newUsuario){
        return new UsuarioDto(usuarioRepo.save(newUsuario));
    }

    public void eliminarUnUsuario(Usuario usuario){
        usuarioRepo.delete(usuario); 
    }

    public List<Usuario> usuarioPorCiudad(String ciudad){
        return usuarioRepo.findByCiudad(ciudad);
    }

    public List<Usuario> buscarUsuarioPorFechaCreacion(LocalDate fechaCreacion){
        return usuarioRepo.findByFechaDeCreacionAfter(fechaCreacion);
    }
}
