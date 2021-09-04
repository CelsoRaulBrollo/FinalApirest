package com.info.apirest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity 
public class Usuario {

    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
   @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Carrito.class)
   private List<Carrito> carrito = new ArrayList<>();
   @JsonIgnore
   @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Orden.class)
   private List<Orden> ordens = new ArrayList<>();


    @NotBlank(message = "Ingrese un nombre")
    private String nombre;

    @NotBlank(message = "Ingrese un apellido")
    private String apellido;

    @NotBlank(message = "Ingrese un email")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Ingrese una contraseña")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @CreationTimestamp
    private LocalDate fechaCreacion = LocalDate.now();

    private String ciudad;
    private String provincia;
    private String pais;
    
    
    public Usuario(Long id){ 
        this.id = id;
    }

    public Usuario() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    
   public List<Carrito> getCarrito() { 
       return carrito; 
    }

    public void setCarts(List<Carrito> carrito) { 
        this.carrito = carrito; 
    }
    
    public void addCarrito(Carrito carrito) { 
        this.carrito.add(carrito); 
    }


    public List<Orden> getOrders() { 
        return ordens; 
    }

    public void setOrders(List<Orden> ordens) { 
        this.ordens = ordens; 
    }

    public void addOrder(Orden orden) { 
        this.ordens.add(orden); 
    }
}

