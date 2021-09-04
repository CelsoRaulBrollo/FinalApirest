package com.info.apirest.repositories;

import java.util.List;
import com.info.apirest.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long> {

   List<Producto> findByNombre(String nombre);

   List<Producto> findByPublicado(boolean publicado);
}   