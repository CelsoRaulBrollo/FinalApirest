package com.info.apirest.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import com.info.apirest.models.Producto;
import com.info.apirest.repositories.ProductoRepo;
import com.info.apirest.repositories.UsuarioRepo;
import com.info.apirest.services.ProductoService;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/producto")

public class ProductoController {

    @Autowired
    ProductoService productoService;

    @Autowired
    ProductoRepo productoRepository;

    @Autowired
    UsuarioRepo usuarioRepository;

    @PostMapping()
    public Producto guardarNuevoProducto(@RequestBody Producto producto) {
        producto.setFechaDeCreacion(LocalDate.now());
        return this.productoService.guardarProducto(producto);
    }

    @GetMapping("/todos")
    public ArrayList<Producto> obtenerProductos(){
        return productoService.obtenerTodosLosProductos();
    }

    @GetMapping("/nombre")
    public ResponseEntity<?> buscarPorNombreCualquiera(@RequestParam String nombre){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorNombre(nombre));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/estado")
    public ResponseEntity<?> buscarPorEstadoProducto(@RequestParam Boolean publicado){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorPublicado(publicado));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping(value = "/{id}/modificar")
    public ResponseEntity<?> updateProducto(@PathVariable Long id,@RequestBody Producto productoModificado){

        Producto productoExistente;
        
        Optional<Producto> postONull = productoRepository.findById(id);

        if (postONull.isPresent()) {
            productoExistente = postONull.get();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String nombre = productoModificado.getNombre();
        String descripcion = productoModificado.getDescripcion();
        String contenido = productoModificado.getContenido();
        Boolean publicado = productoModificado.isPublicado();

        if (nombre != null) {
            productoExistente.setNombre(nombre);
        }

        if (descripcion != null) {
            productoExistente.setDescripcion(descripcion);
        }

        if (contenido != null) {
            productoExistente.setContenido(contenido);
        }

        if (publicado != null) {
            productoExistente.setPublicado(publicado);
        } 
        
        return new ResponseEntity<>(productoRepository.save(productoExistente), HttpStatus.OK);
    }    

    @DeleteMapping(value = "/{id}/eliminar")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.productoService.eliminarProducto(id);
        if(ok){
            return "Se eliminó el producto con id " + id;
        }else {
            return "No se pudo eliminar el producto con id " + id;
        }
    }
    
}