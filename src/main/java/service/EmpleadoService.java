package service;

import org.springframework.stereotype.Service;

import entity.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmpleadoService {
    private final List<Empleado> empleados = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

   
    public Empleado crearEmpleado(Empleado empleado) {
        empleado.setId(idGenerator.getAndIncrement());
        empleados.add(empleado);
        return empleado;
    }

    
    public List<Empleado> listarEmpleados() {
        return empleados;
    }

   
    public Optional<Empleado> obtenerEmpleadoPorId(Long id) {
        return empleados.stream().filter(empleado -> empleado.getId().equals(id)).findFirst();
    }

  
    public Empleado actualizarEmpleado(Long id, Empleado empleadoActualizado) {
        Optional<Empleado> empleadoOpt = obtenerEmpleadoPorId(id);
        if (empleadoOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            empleado.setNombre(empleadoActualizado.getNombre());
            empleado.setPuesto(empleadoActualizado.getPuesto());
            empleado.setSalario(empleadoActualizado.getSalario());
            return empleado;
        }
        return null;
    }

    
    public boolean eliminarEmpleado(Long id) {
        return empleados.removeIf(empleado -> empleado.getId().equals(id));
    }
}