package com.info.apirest.repositories;

import com.info.apirest.models.Detalle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepo extends JpaRepository<Detalle, Long> {

}
   