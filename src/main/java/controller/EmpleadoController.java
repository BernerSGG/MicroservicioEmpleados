package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import entity.Empleado;
import service.EmpleadoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    
    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
        return ResponseEntity.ok(nuevoEmpleado);
    }

 
    @GetMapping
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        return ResponseEntity.ok(empleadoService.listarEmpleados());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoService.obtenerEmpleadoPorId(id);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoActualizado) {
        Empleado empleado = empleadoService.actualizarEmpleado(id, empleadoActualizado);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        boolean eliminado = empleadoService.eliminarEmpleado(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}