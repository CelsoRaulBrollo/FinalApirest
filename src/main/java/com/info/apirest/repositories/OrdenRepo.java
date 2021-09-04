package com.info.apirest.repositories;

import com.info.apirest.models.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepo extends JpaRepository<Orden, Long> {
}
   