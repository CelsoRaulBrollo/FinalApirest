package com.info.apirest.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity 
@Table(name = "orden")
public class Orden {

   @Id
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY, targetEntity = Usuario.class)
   @JoinColumn(name = "FK_usuario")
   private Usuario usuario;
   @OneToMany(mappedBy = "orden", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Detalle.class)
   private List<Detalle> detalle = new ArrayList<>();

   @CreationTimestamp
   private LocalDate orderDate;
   private String observacion;

   @Transient
   private String cliente;
   @Transient 
   private BigDecimal total;


   public Orden() {}



   public Long getId() { return id; }

   public List<Detalle> getDetalle() { return detalle; }

   public LocalDate getOrderDate() { return orderDate; }

   public String getObservacion() { return observacion; }

   public String getCliente() { return this.usuario.getNombre().concat(" ").concat(this.usuario.getApellido()); }

   public BigDecimal getTotal() {
      BigDecimal finalTotal = BigDecimal.ZERO;
      for (Detalle detalle: this.detalle) {
         finalTotal = finalTotal.add(detalle.getSubtotal());
      }
      return finalTotal;
   }

   public void setId(Long id) { this.id = id; }

   public void setUsuario(Usuario usuario) { this.usuario = usuario; }

   public void setDetalle(List<Detalle> detalle) { this.detalle = detalle; }

   public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

   public void setObservacion(String observacion) { this.observacion = observacion; }
}
