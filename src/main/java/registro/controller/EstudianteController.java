package registro.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import registro.model.Estudiante;
import registro.model.EstudianteDto;
import registro.response.Response;
import registro.service.EstudianteService;

@CrossOrigin(originPatterns = { "http://localhost:4200" })
@RestController
@Slf4j
public class EstudianteController {

	@Autowired
	private EstudianteService service;

	@GetMapping("/estudianteslistar")
	public List<Estudiante> lista() {
		return service.findAll();

	}

	@GetMapping("/lista/page/{page}")
	public Page<Estudiante> lista(@PathVariable Integer page) {
		Pageable pag = PageRequest.of(page, 4);
		return (Page<Estudiante>) service.findAll(pag);

	}

	@PostMapping("/estudiantesregistro")
	public ResponseEntity<Response> registrar(@RequestBody Estudiante estudiante) {
		Response rsp = new Response();
		Estudiante estudiante1 = new Estudiante();
		estudiante1 = service.crear(estudiante);
		rsp.setCode("200");
		rsp.setMessage("registro exitoso");
		rsp.setEstudiantes(estudiante1);

		return new ResponseEntity<>(rsp, HttpStatus.OK);

	}

	@PutMapping("/estudiantes/{id}")
	public Estudiante actualizar(@RequestBody Estudiante estudiante, @PathVariable Long id) {
		log.info("validaicon por id : {}", service.actualizarPorId(id));
		Estudiante stu = service.actualizarPorId(id);
		stu.setPrimerNombre(estudiante.getPrimerNombre());
		stu.setSegundoNombre(estudiante.getSegundoNombre());
		stu.setPrimerApellido(estudiante.getPrimerApellido());
		stu.setSegundoApellido(estudiante.getSegundoApellido());
		stu.setTipoDocumento(estudiante.getTipoDocumento());
		stu.setNumeroDocumento(estudiante.getNumeroDocumento());
		stu.setCurso(estudiante.getCurso());
		stu.setEstado(estudiante.getEstado());
		stu.setCreateAt(new Date());
		return service.crear(stu);
	}

	@GetMapping("/estudiantesporidentidad")
	public Response listPorIdentidad(
			@RequestParam(value = "numeroDocumento", required = true) Integer numeroDocumento) {
		return service.findByIdentidad( numeroDocumento);
	}

	@GetMapping("/estudiantesporestado")
	public ResponseEntity<Response> listPorEstado(@RequestParam(value = "estado", required = true) String estado) {
		Response rsp = service.findByEstado(estado);
		return ResponseEntity.ok(rsp);
	}
	
	@GetMapping("/estudiantesconsulta")
	public ResponseEntity<EstudianteDto> consulta(){
		return new ResponseEntity<>(service.dto(),HttpStatus.OK);
	}
	
	@GetMapping("/estudiantesporid/{id}")
	public Estudiante buscarPorId(@PathVariable Long id) {
		return service.porId(id);
	}

}
