package com.info.apirest.services;

import com.info.apirest.dto.DetalleDto;
import com.info.apirest.models.Detalle;
import org.springframework.stereotype.Service;

@Service
public class DetalleService {


   public void savePreparation(Detalle requestDetail) {
      requestDetail.defaultPrecioUnitario();
   }

   public void updatePreparation(Detalle detalle, DetalleDto requestDetail) {
      detalle.setProducto(requestDetail.getProducto());
      detalle.defaultPrecioUnitario();
      detalle.setMonto(requestDetail.getMonto());
   }
}
  