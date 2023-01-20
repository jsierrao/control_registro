package registro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import registro.model.DocumentoIdentidad;
import registro.service.DocumentoService;

@RestController
public class DocuController {

	
	@Autowired
	DocumentoService service;
	
	@GetMapping("/listas")
	public List<DocumentoIdentidad>lista(){
		return service.findAll();
	}
	
	@PostMapping("/crearDocuemnto")
	public DocumentoIdentidad crear(@RequestBody DocumentoIdentidad docu) {
		return service.crear(docu);
	}
	
	
}
