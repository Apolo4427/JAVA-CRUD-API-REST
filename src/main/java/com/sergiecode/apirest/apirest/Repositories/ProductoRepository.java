package com.sergiecode.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiecode.apirest.apirest.Entities.Producto;

// hacemos que herede de JpaRepository<Producto, Long> donde indicamos => <"la clase", "tipo de dato del ID">
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
