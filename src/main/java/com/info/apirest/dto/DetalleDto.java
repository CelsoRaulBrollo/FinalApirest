package com.info.apirest.dto;

import com.info.apirest.models.Producto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class DetalleDto {

   @NotNull 
   @NotEmpty
   private Producto producto;
   @Positive
   private Integer monto;
   @Positive
   private BigDecimal precioUnitario; 


   public DetalleDto() {} 


   public Producto getProducto() { return producto; }
   
   public void setProducto(Producto producto) {
      this.producto = producto;
   }

  
   public Integer getMonto() { return monto; }

    public void setMonto(Integer monto) {
      this.monto = monto;
   }
   
   
   public BigDecimal getPrecioUnitario() { return precioUnitario; }

   public void setPrecioUnitario(BigDecimal precioUnitario) {
      this.precioUnitario = precioUnitario;
   }
}
