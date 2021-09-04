package com.info.apirest.dto;

import com.info.apirest.models.Carrito;
import org.hibernate.validator.constraints.Length;

public class OrdenDto {

   private Carrito carrito;
   @Length(max = 200)
   private String observacion;


   public OrdenDto() {}
 

   public Carrito getCarrito() { return carrito; }

   public void setCarrito(Carrito carrito) {
      this.carrito = carrito;
   }
    
   
   public String getObservacion() { return observacion; }
   
   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }
}
