package com.info.apirest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Detalle {
 
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne
   @JoinColumn(name = "FK_producto")
   private Producto producto; 
   @JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Carrito.class)
   @JoinColumn(name = "FK_carrito")
   private Carrito carrito;
   @JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY, targetEntity = Orden.class)
   @JoinColumn(name = "FK_orden")
   private Orden orden;

   @Positive
   private Integer monto;
   @Transient
   private BigDecimal subtotal;
   @Positive
   private BigDecimal precioUnitario;


   public Detalle () {}



   public Long getId() { return id; }

   public Producto getFK_producto() { return producto; }

   public Integer getMonto() { return monto; }

   public BigDecimal getSubtotal() { return precioUnitario.multiply(BigDecimal.valueOf(monto)); }

   public BigDecimal getPrecioUnitario() { return precioUnitario; }


   public void setId(Long id) {
      this.id = id;
   }

   public void setProducto(Producto producto) {
      this.producto = producto;
   }

   public void setMonto(Integer monto) {
      this.monto = monto;
   }

   public void setPrecioUnitario(BigDecimal precioUnitario) {
      this.precioUnitario = precioUnitario;
   }

   public void setCarrito(Carrito carrito) { this.carrito = carrito; }

   public void setOrder(Orden orden) { this.orden = orden; }

   // Tercer Metodo

   public void defaultPrecioUnitario() {
      this.precioUnitario = this.producto.getPrecioUnitario();
   }
}
