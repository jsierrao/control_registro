package registro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import registro.model.Estudiante;
import registro.model.EstudianteDto;
import registro.model.Mapeador;
import registro.response.Response;
import registro.service.EstudianteService;

@CrossOrigin(originPatterns = { "http://localhost:4200" })
@RestController

public class EstudianteController {

	@Autowired
	Mapeador map;

	@Autowired
	private EstudianteService service;

	@GetMapping("/estudianteslistar")
	public List<Estudiante> lista() {
		return service.findAll();

	}

	@SuppressWarnings("unchecked")
	@GetMapping("/lista/page/{page}")
	public Page<Estudiante> lista(@PathVariable Integer page) {
		Pageable pag = PageRequest.of(page, 4);
		return (Page<Estudiante>) service.findAll(pag);

	}

	@PostMapping("/estudiantesregistro")
	public ResponseEntity<Response> registrar(@RequestBody Estudiante estudiante) {
		Response rsp = new Response();
		service.crear(estudiante);
		rsp.setCode("200");
		rsp.setMessage("registro exitoso");
		rsp.setEstudiantes(estudiante);

		return new ResponseEntity<>(rsp, HttpStatus.OK);

	}

	@PutMapping("/estudiantes/{id}")
	public Estudiante actualizarPorId(@RequestBody Estudiante estu, @PathVariable Long id) {

		return service.actualizar(estu, id);

	}

	@GetMapping("/estudiantesporidentidad")
	public Response listPorIdentidad(
			@RequestParam(value = "numeroDocumento", required = true) Integer numeroDocumento) {
		return service.findByIdentidad(numeroDocumento);
	}

	@GetMapping("/estudiantesporestado")
	public ResponseEntity<Response> listPorEstado(@RequestParam(value = "estado", required = true) String estado) {
		Response rsp = service.findByEstado(estado);
		return ResponseEntity.ok(rsp);
	}

	@GetMapping("/estudiantesconsulta")
	public ResponseEntity<EstudianteDto> consulta() {
		return new ResponseEntity<>(service.dto(), HttpStatus.OK);
	}

	@GetMapping("/estudiantesporid/{id}")
	public Estudiante buscarPorId(@PathVariable Long id) {
		return service.porId(id);
	}

	@DeleteMapping("/eliminar/{id}")
	public void eliminacion(@PathVariable Long id) {
		Estudiante stu = service.porId(id);
		service.eliminar(stu);

	}

}
