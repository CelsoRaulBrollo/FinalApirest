package com.info.apirest.services;

import com.info.apirest.dto.OrdenDto;
import com.info.apirest.models.Carrito;
import com.info.apirest.models.Orden;
import com.info.apirest.models.Usuario;
import com.info.apirest.repositories.CarritoRepo;
import com.info.apirest.repositories.OrdenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenService {

   @Autowired
   private OrdenRepo ordenRepo;
   @Autowired
   private CarritoRepo carritoRepo;


   public Orden createOrder(OrdenDto ordenDto, Usuario usuario) {
      Orden orden = new Orden();
      orden.setId(ordenDto.getCarrito().getId());
      orden.setObservacion(ordenDto.getObservacion());
      orden.setDetalle(ordenDto.getCarrito().getDetalle());
      orden.setUsuario(usuario); 

      updateUserCartsAndUserOrders(usuario, orden, ordenDto.getCarrito());

      orden.getDetalle().forEach(detalle -> {
         detalle.setOrder(orden);
      });
      return ordenRepo.save(orden);
   }

   public void updateUserCartsAndUserOrders(Usuario usuario, Orden orden, Carrito requestCart) {
      usuario.getCarrito().remove(requestCart);
      Carrito carrito = carritoRepo.getById(orden.getId());
//      cartRepository.delete(cart);
      carrito.setUsuario(null);
   }
}
 