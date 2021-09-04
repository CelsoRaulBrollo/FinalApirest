package com.info.apirest.repositories;

import com.info.apirest.models.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepo extends JpaRepository<Carrito, Long> {
}   