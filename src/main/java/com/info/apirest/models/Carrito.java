package com.info.apirest.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrito { 

   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotBlank
   private String device;

   @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
   
   @CreationTimestamp
   private LocalDate fechaCreacion;
   @Transient
   private String cliente;
   @Transient
   private BigDecimal total;

   @NotNull
    private boolean estado;

   @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Detalle> detalle = new ArrayList<>();

   public Carrito() {}
 


   public Long getId() { return id; }
 
   public String getDevice() { return device; }

   public Usuario getUsuario() { return usuario; }

   public LocalDate getFechaCreacion() { return fechaCreacion; }

   public String getCliente() { return usuario.getNombre().concat(" ").concat(usuario.getApellido()); }

   public BigDecimal getTotal() {
      BigDecimal finalTotal = BigDecimal.ZERO;
      for (Detalle detalle: this.detalle) {
         finalTotal = finalTotal.add(detalle.getSubtotal());
      }
      return finalTotal;
   }

   public boolean isEstado() { return estado; }
   
   public List<Detalle> getDetalle() { return detalle; }
   
   

   public void setId(Long id) {
      this.id = id;
   }

   public void setDevice(String device) {
      this.device = device;
  }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }
  
   public void setFechaCreacion(LocalDate fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   
   public void setEstado(boolean estado) {
      this.estado = estado;
  }
    
   public void setDetalle(List<Detalle> detalle) {
      this.detalle = detalle;
   }
}
