package com.info.apirest.controllers;

import com.info.apirest.dto.CarritoDto;
import com.info.apirest.exception.ResourceNotFound;
import com.info.apirest.models.Carrito; 
import com.info.apirest.models.Usuario;
import com.info.apirest.repositories.CarritoRepo;
import com.info.apirest.repositories.UsuarioRepo;
import com.info.apirest.services.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/v1/carrito")
public class CarritoController {

   @Autowired
   private CarritoRepo carritoRepo;
   @Autowired 
   private UsuarioRepo usuarioRepo; 

   @Autowired
   private CarritoService cartService;


   @GetMapping(value = "/usuario/{id}/{cartId}")
   public ResponseEntity<?> getCart(@PathVariable("id") Long id, @PathVariable("cartId") Long cartId) {
      Optional<Usuario> usuario = usuarioRepo.findById(id);
      Optional<Carrito> carrito = carritoRepo.findById(cartId);
      if (usuario.isEmpty() || !usuario.get().getCarrito().contains(carrito.get())) {
         throw new ResourceNotFound("¡No se pudo encontrar el carrito!");
      }
      return ResponseEntity.status(HttpStatus.OK).body(carrito);
   }

   @GetMapping(value = "/usuario/{id}")
   public ResponseEntity<?> getAllCarts(@PathVariable("id") Long id) {
      Optional<Usuario> usuario = usuarioRepo.findById(id);
      if (usuario.isEmpty()) {
         throw new ResourceNotFound("¡No se pudo encontrar el carrito!");
      }
      return ResponseEntity.status(HttpStatus.OK).body(usuario.get().getCarrito());
   }

   @PostMapping(value = "/usuario/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> createCart(@PathVariable("id") Long id,
                                       @Valid @RequestBody Carrito requestCart) {
      Optional<Usuario> usuario = usuarioRepo.findById(id);
      if (usuario.isEmpty()) {
         throw new ResourceNotFound("¡No se pudo encontrar el carrito!");
      }
      cartService.savePreparation(usuario.get(), requestCart);
      return ResponseEntity.status(HttpStatus.CREATED).body(carritoRepo.save(requestCart));
   }

   @PutMapping(value = "/usuario/{id}/{cartId}")
   public ResponseEntity<?> updateCart(@PathVariable("id") Long id, @PathVariable("cartId") Long cartId,
                                       @Valid @RequestBody CarritoDto requestCart) {
      Optional<Usuario> usuario = usuarioRepo.findById(id);
      Optional<Carrito> carrito = carritoRepo.findById(cartId);
      if (usuario.isEmpty() || carrito.isEmpty() || !usuario.get().getCarrito().contains(carrito.get())) {
         throw new ResourceNotFound("¡No se pudo modificar el carrito solicitado!");
      }
      cartService.updatePreparation(carrito.get(), requestCart);
      return ResponseEntity.status(HttpStatus.OK).body(carritoRepo.save(carrito.get()));
   }

   @DeleteMapping(value = "/usuario/{id}/{cartId}")
   public String deleteCart(@PathVariable("id") Long id, @PathVariable("cartId") Long cartId) {
      Optional<Usuario> usuario = usuarioRepo.findById(id);
      Optional<Carrito> carrito = carritoRepo.findById(cartId);
      if (usuario.isEmpty() || carrito.isEmpty() || !usuario.get().getCarrito().contains(carrito.get())) {
         throw new ResourceNotFound("¡No se pudo eliminar el carrito solicitado!");
      }
      usuario.get().getCarrito().remove(carrito.get());
      carritoRepo.delete(carrito.get());
      return "Se elimino el carrito con id " + cartId;
   }
}
