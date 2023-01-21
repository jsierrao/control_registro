package registro.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import registro.model.Estudiante;
import registro.response.Response;
import registro.service.EstudianteService;

@RestController
public class EstudianteController {

	@Autowired
	private EstudianteService service;

	@GetMapping("/lista")
	public ResponseEntity<Response> lista() {
		Response rsp = service.findAll();
		return new ResponseEntity<>(rsp, HttpStatus.OK);

	}

	@PostMapping("/crear")
	public ResponseEntity<Response> registrar(@RequestBody Estudiante estudiante) {
		Response rsp = new Response();
		estudiante = service.crear(estudiante);
		rsp.setCode("200");
		rsp.setMessage("registro exitoso");
		return new ResponseEntity<>(rsp, HttpStatus.OK);

	}

	@PutMapping("/actualizar")
	public Estudiante actualizar(@RequestBody Estudiante estudiante) {
		return service.actualizar(estudiante);
	}

	@GetMapping("/listardocumento")
	public ResponseEntity<Response> listPorIdentidad(
			@RequestParam(value = "numeroDocumento", required = true) Integer numeroDocumento) {
		Response rsp = service.findByIdentidad(numeroDocumento);

		return new ResponseEntity<>(rsp, HttpStatus.OK);

	}
	
	@GetMapping("/listarestado")
	public ResponseEntity<Response>listPorEstado(@RequestParam(value = "estado",required = true)String estado){
		Response rsp = service.findByEstado(estado);
		return ResponseEntity.ok(rsp);
		
	}
	
	

}
