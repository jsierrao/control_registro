package registro.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import registro.model.Estudiante;
import registro.response.Response;
import registro.service.EstudianteService;

@RestController
public class EstudianteController {
	
	
	@Autowired
	private EstudianteService service;
	
    @GetMapping("/lista")
	public ResponseEntity<List<Estudiante>> lista(){
    	List<Estudiante>stu = service.findAll();
    	if(stu.isEmpty()) {
    		return ResponseEntity.noContent().build();
    	}else {
    		return ResponseEntity.ok(stu);
    	}
	
	}
    
    @PostMapping("/crear")
    public ResponseEntity<Estudiante> registrar(@RequestBody Estudiante estudiante) {
    	Estudiante stu = service.crear(estudiante);
    	Response rsp = new Response();
    	rsp.setCode("200");
    	rsp.setMessage("proceso exitoso");
		return ResponseEntity.status(HttpStatus.CREATED).body(stu);
    	
    }
    
    @PutMapping("/actualizar")
    public Estudiante actualizar(@RequestBody Estudiante estudiante){
    	return service.actualizar(estudiante);
    }
	
	

}
