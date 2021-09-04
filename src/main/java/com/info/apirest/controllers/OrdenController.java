package com.info.apirest.controllers;

import com.info.apirest.dto.OrdenDto;
import com.info.apirest.exception.ResourceNotFound;
import com.info.apirest.models.Orden;
import com.info.apirest.models.Usuario;
import com.info.apirest.repositories.OrdenRepo;
import com.info.apirest.repositories.UsuarioRepo;
import com.info.apirest.services.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/v1/ordenes", consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrdenController {

   @Autowired
   private OrdenRepo ordenRepo;
   @Autowired
   private UsuarioRepo usuarioRepo;

   @Autowired
   private OrdenService ordenService;

   @GetMapping(value = "/usuario/{id}")
   public ResponseEntity<?> getOrders(@PathVariable("id") Long id) {
      return ResponseEntity.status(HttpStatus.OK).body(ordenRepo.findAll());
   }

   @GetMapping(value = "/usuario/{id}/{orderId}")
   public ResponseEntity<?> getOrder(@PathVariable("id") Long id, @PathVariable("orderId") Long orderId) {
      Optional<Usuario> usuario = usuarioRepo.findById(id);
      Optional<Orden> order = ordenRepo.findById(orderId);
      if (usuario.isEmpty() || order.isEmpty() || !usuario.get().getOrders().contains(order.get())) {
         throw new ResourceNotFound("¡Los datos de la compra no coinciden!");
      }
      return ResponseEntity.status(HttpStatus.OK).body(order);
   }

   @PostMapping(value = "/usuario/{id}")
   public ResponseEntity<?> createOrder(@PathVariable("id") Long id, @Valid @RequestBody OrdenDto ordenDto) {
      Optional<Usuario> usuario = usuarioRepo.findById(id);
      if (usuario.isEmpty()) {
         throw new ResourceNotFound("¡No se encontro el usuario solicitado!");
      }
      Orden orden = ordenService.createOrder(ordenDto, usuario.get());
      return ResponseEntity.status(HttpStatus.CREATED).body(ordenRepo.save(orden));
   }

   @PutMapping(value = "/usuario/{id}/{orderId}")
   public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @PathVariable("orderId") Long orderId, @Valid @RequestBody OrdenDto ordenDto) {
      Optional<Usuario> usuario = usuarioRepo.findById(id);
      Optional<Orden> order = ordenRepo.findById(orderId);
      if (usuario.isEmpty() || order.isEmpty() || !usuario.get().getOrders().contains(order.get())) {
         throw new ResourceNotFound("¡No existe el recurso solicitado!");
      }
      return ResponseEntity.status(HttpStatus.OK).body(order);
   }
 
   @DeleteMapping(value = "/usuario/{id}/{orderId}")
   public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id, @PathVariable("orderId") Long orderId) {
      Optional<Usuario> usuario = usuarioRepo.findById(id);
      Optional<Orden> order = ordenRepo.findById(orderId);
      if (usuario.isEmpty() || order.isEmpty() || !usuario.get().getOrders().contains(order.get())) {
         throw new ResourceNotFound("¡No existe el recurso solicitado!");
      }
      ordenRepo.deleteById(orderId);
      return ResponseEntity.status(HttpStatus.OK).build();
   }

}
