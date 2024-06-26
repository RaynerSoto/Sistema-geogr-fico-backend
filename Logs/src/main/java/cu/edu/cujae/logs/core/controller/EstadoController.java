package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.EstadoDto;
import cu.edu.cujae.logs.core.servicesInterfaces.EstadoServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estado")
public class EstadoController {
    @Autowired
    private EstadoServiceInterfaces estadoService;

    @GetMapping("/")
    public ResponseEntity<?> listarEstados() {
        try {
            return ResponseEntity.ok().body(estadoService.listarEstados().stream().map(EstadoDto::new).toList());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
