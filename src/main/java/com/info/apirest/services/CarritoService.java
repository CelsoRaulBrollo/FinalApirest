package com.info.apirest.services;

import com.info.apirest.dto.CarritoDto;
import com.info.apirest.models.Carrito;
import com.info.apirest.models.Detalle;
import com.info.apirest.models.Usuario;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {

   
   public void savePreparation(Usuario usuario, Carrito requestCart) {
      requestCart.setUsuario(usuario);
      for (Detalle detalle: requestCart.getDetalle()) {
         detalle.defaultPrecioUnitario();
         detalle.setCarrito(requestCart);
      }
      usuario.addCarrito(requestCart);
   }
 

   public void updatePreparation(Carrito carrito, CarritoDto requestCart) {
      for (Detalle detalle: requestCart.getDetalle()) {
         detalle.defaultPrecioUnitario();
         detalle.setCarrito(carrito);
         if (detalle.getMonto().equals(0)) { requestCart.getDetalle().remove(detalle); }
      }
      carrito.setDetalle(requestCart.getDetalle());
   }
}
