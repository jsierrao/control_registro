package registro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import registro.model.Curso;
import registro.service.CursoService;

@RestController
public class CursoController {
	
	@Autowired
	CursoService service;
	
	@GetMapping("/listar")
	public List<Curso>listar(){
		return service.findAll();
	}
	
	
	@PostMapping("/crearcurso")
	public Curso crear(@RequestBody Curso curso) {
		return service.crear(curso);
	}

}
