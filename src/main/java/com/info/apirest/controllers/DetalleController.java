package com.info.apirest.controllers;

import com.info.apirest.dto.DetalleDto;
import com.info.apirest.models.Detalle;
import com.info.apirest.repositories.DetalleRepo;
import com.info.apirest.services.DetalleService;
import com.info.apirest.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/v1", consumes = MediaType.APPLICATION_JSON_VALUE)
public class DetalleController {

   @Autowired
   private DetalleRepo detalleRepo;

   @Autowired 
   private DetalleService detalleService; 

   @GetMapping(value = "/detalle/{detailId}")
   public ResponseEntity<?> getDetail(@PathVariable("detailId") Long detailId) {
      Optional<Detalle> detalle = detalleRepo.findById(detailId);
      if (detalle.isEmpty()) {
         throw new ResourceNotFound("¡El detalle solicitado no existe!");
      }
      return ResponseEntity.status(HttpStatus.OK).body(detalle);
   } 

   @GetMapping(value = "/detalle")
   public ResponseEntity<?> getAllDetails() {
      return ResponseEntity.status(HttpStatus.OK).body(detalleRepo.findAll());
   }

   @PostMapping(value = "/detalle")
   public ResponseEntity<?> createDetail(@Valid @RequestBody Detalle requestDetail) {
      detalleService.savePreparation(requestDetail);
      return ResponseEntity.status(HttpStatus.CREATED).body(detalleRepo.save(requestDetail));
   }

   @PutMapping(value = "/detalle/{detailId}")
   public ResponseEntity<?> updateDetail(@PathVariable("detailId") Long detailId,
                                         @Valid @RequestBody DetalleDto requestDetail) {
      Optional<Detalle> detalle = detalleRepo.findById(detailId);
      if (detalle.isEmpty()) {
         throw new ResourceNotFound("¡El detalle solicitado no exite!");
      }
      detalleService.updatePreparation(detalle.get(), requestDetail);
      return ResponseEntity.status(HttpStatus.OK).body(detalleRepo.save(detalle.get()));
   }

   @DeleteMapping(value = "/detalle/{detailId}")
   public ResponseEntity<?> deleteDetail(@PathVariable("detailId") Long detailId) {
      Optional<Detalle> detalle = detalleRepo.findById(detailId);
      if (detalle.isEmpty()) {
         throw new ResourceNotFound("¡El detalle solicitado no exite!");
      }
      detalleRepo.delete(detalle.get());
      return ResponseEntity.status(HttpStatus.OK).build();
   }
}