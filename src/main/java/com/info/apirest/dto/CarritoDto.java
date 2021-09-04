package com.info.apirest.dto;

import com.info.apirest.models.Detalle;

import java.util.ArrayList;
import java.util.List;

public class CarritoDto {

   private List<Detalle> detalle = new ArrayList<>();

   public CarritoDto() {}

 
   public List<Detalle> getDetalle() { return detalle; }

   public void setDetalle(List<Detalle> detalle) { this.detalle = detalle; } 
}
 