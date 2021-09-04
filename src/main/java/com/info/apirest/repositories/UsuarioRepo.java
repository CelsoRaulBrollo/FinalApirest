package com.info.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;

import com.info.apirest.models.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

    @Query("FROM Usuario WHERE ciudad LIKE 'Resistencia'")
    List <Usuario> findByCiudad(String ciudad);

    @Query("FROM Usuario WHERE fechaCreacion >= ?1")
    List <Usuario> findByFechaDeCreacionAfter(LocalDate fechaCreacion);
}
  