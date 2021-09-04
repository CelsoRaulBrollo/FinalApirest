package com.info.apirest.services;

import java.util.ArrayList;
import java.util.List;
import com.info.apirest.models.Producto;
import com.info.apirest.repositories.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    
    @Autowired
    ProductoRepo productoRepository; 
    
    public Producto guardarProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public ArrayList<Producto> obtenerTodosLosProductos() {
        return (ArrayList<Producto>) productoRepository.findAll(); 
    } 

    public List <Producto> buscarPorNombre(String nombre) throws Exception{
        try{
            List <Producto> producto = (List<Producto>) productoRepository.findByNombre(nombre);
            return producto;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    
    public List <Producto> buscarPorPublicado(Boolean publicado) throws Exception{
        try{
            List <Producto> producto = productoRepository.findByPublicado(publicado);
            return producto;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    
    public boolean eliminarProducto(Long id){
        try{
            productoRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}