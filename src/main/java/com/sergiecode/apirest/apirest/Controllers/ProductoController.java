package com.sergiecode.apirest.apirest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergiecode.apirest.apirest.Repositories.ProductoRepository;//importamos el repositorio que vamos a usar

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.sergiecode.apirest.apirest.Entities.Producto;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



//aqui pondremos TODAS las direcciones para poder comunicanos con un cliente, aqui encontraremo el CRUD

@RestController//API REST 
@RequestMapping("/productos")//si una persona llama al puerto que escucha "/productos" podria ingresar a nuestra base de datos productos
/**
 * con las dos lineas de codigo anteriores "@RequestMapping("/productos") y @RestController" estamos habilitando que "/productos" en la url pueda ser leida por un cliente 
 * como por ejemplo postman 
 */
public class ProductoController {

    //Lo primero es injectar una stancia del repositorio, lo cual se resulve con la siguiente etiqueta, decorador: "@Autowired"
    //con lo que tendremos nuestra coneccion con la base de datos atravez de este mimo repositorio 
    @Autowired
    private ProductoRepository productoRepository;//ESTE REPOSITORIO DIRECTAMENTE SERA LA CONECCION CON LA BASE DE DATOS 


    //CREAMOS EL CRUD:

    //METODO GET
    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();//mediante el repositorio decorado con "@Autowired" haremos uso del metodo findAll(), nos encontrara y devolvera
        //todos los objetos de tipo producto que encuentre en la base datos (entidad o tabal producto)
    }

    //METODO GET INDIVIDUAL
    @GetMapping("/{id}")//@PathVariable long ID => resive el identificador unico de tipo long para hayar el objeto
    public Producto getProductByID(@PathVariable Long id){
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID "+id));
        /**return productoRepository.findById(ID).orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID "+ID));
         * con lo anterior completamos l devulucion del "@PathVariable long ID" donde 
         * primero busca mediante el repositorio el objeto con dicho ID => "productoRepository.findById(ID)"
         * segundo en caso de no encontrarlo (error) se ejecutara una funcion fecha => .orElseThrow("funcion flecha"()->)
         * y tercero mediante la funcion RuntimeException() devolveremos un mensaje para dar a conocer y manejar el error resultante => "new RuntimeException("No se encontro el producto con el ID "+ID))"
         */
    }
    

    //  METODO POST (CREATE)
    @PostMapping//@RequestBody Producto producto => resive un request de tipo Producto y le llamamos producto
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);//grabamos en la base de datos el producto recibido, y ademas lo devolvemos cuando haya sido grabado, y ya tenga el id autogenerado
    }   //NOTA: .SAVE() actualiza la base de datos
    
    // METODO PUT (UPDATE)
    @PutMapping("/{id}")//@PathVariable long ID => resive el identificador unico de tipo long para hayar el objeto
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto actualizacionProducto){// en este caso se nombra el producto resivido como "detalles del producto"
        Producto productoActualizado = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID "+id));
        //En este caso definimos una variable llamada productoActualizado de tipo Producto, en la cual almacenaremos el producto buscado por el ID solicitado
        //para poder actualizarlo posteriormente 

        /**Seguido a haber encontrado el producto con el ID solicitado
         * procedemos a setear los valores que se ingresarian mediante el producto ingresado a este metodo ("actualizacionProducto")
         */
        productoActualizado.setNombre(actualizacionProducto.getNombre());
        productoActualizado.setPrecio(actualizacionProducto.getPrecio());

        return productoRepository.save(productoActualizado);
        //por ultimo guardamos los cambios atravez del repositorio y devolvemos el producto ya actualizado
    }
        
    // METODO DELETE (DELETE)
    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID "+id));

        productoRepository.delete(producto);
        //usamos el metodo .delete() del repositorio que hemos creado para eliminar el producto que hemos definido
        return "El producto identificado por el ID: "+id+" se ha eliminado correctamente.";
    }

}
