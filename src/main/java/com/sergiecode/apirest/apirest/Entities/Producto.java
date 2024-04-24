package com.sergiecode.apirest.apirest.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity//indicamos que es una entidad
public class Producto {

    @Id//indicamos que el valor id sera considerado como el identificador unico de cada objeto instanciado en esta clase 
    @GeneratedValue(strategy = GenerationType.IDENTITY)//indicamos una estrategia de creacion para darle a conocer al programa que sera un a integracion de tipo IDENTIDAD
    //con lo cual se generaran ids de manera auto incremental, de tal forma que la base de datos no reciva ids manueles, si no que se maneje de forma automatica
    private Long id;
    private String nombre;
    private double precio;



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
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    

}
