package pe.idat.rest.productos.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.rest.productos.model.Producto;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/productos")
public class ProductoController {
    private final List<Producto> productos= new ArrayList<>();
    private final AtomicLong contador= new AtomicLong();

    public ProductoController(){
        initData();
    }
    
    private void initData(){
        Producto Laptop = new Producto(contador.incrementAndGet(), "Laptop", "Laptop de alta gama", 1500,10);
        Producto CPU = new Producto(contador.incrementAndGet(), "CPU", "CPU de gama media", 800, 5);
        productos.add(Laptop);
        productos.add(CPU);           
    }

    @GetMapping("")    
    public ResponseEntity<List<Producto>> listar(){
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Producto> obtener(@PathVariable long id) {
        Producto p = productos.stream().filter(x -> id== x.getId()).findAny().orElse(null);
        if (p != null){
            return new ResponseEntity<Producto>(p, HttpStatus.OK);
        }else{
            return new  ResponseEntity<Producto>(p, HttpStatus.NOT_FOUND);
        }
    }
    

    @PostMapping("/")
    public ResponseEntity<Producto> registrar(@RequestBody Producto producto){

        Producto productoNuevo = new Producto(contador.incrementAndGet(), producto.getNombre(), producto.getDecripcion(), producto.getPrecio(), producto.getCantidad());
        productos.add(productoNuevo);
        return new ResponseEntity<>(productoNuevo, HttpStatus.CREATED);
    }

    @PutMapping("path/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable long id, @RequestBody Producto p) {
        Producto productoActualizado = null;

        for(Producto producto: productos){
            if(producto.getId() == id) {
                producto.setNombre(p.getNombre());
                producto.setDecripcion(p.getDecripcion());
                producto.setPrecio(p.getPrecio());                
                producto.setCantidad(p.getCantidad());
                productoActualizado = producto;
                break;
            }
        }
    
        return new ResponseEntity<>(productoActualizado,HttpStatus.OK);

    }


    @DeleteMapping("{id}")
    private ResponseEntity<Producto> eliminar(@PathVariable long id){
        Producto producto = productos.stream().filter(p ->id == p.getId()).findAny().orElse(null);
        if(producto!=null){
            productos.remove(producto);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
