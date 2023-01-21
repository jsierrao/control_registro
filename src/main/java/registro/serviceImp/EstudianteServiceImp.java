package registro.serviceImp;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import registro.model.Estudiante;

import registro.repositorio.EstudianteRepository;

import registro.response.Response;
import registro.service.EstudianteService;



@Service
public class EstudianteServiceImp implements EstudianteService {

	@Autowired
	EstudianteRepository repo;
	
	

	@Override
	public Estudiante crear(Estudiante estudiante) {
		estudiante.setCreateAt(new Date());
		return repo.save(estudiante);

	} 

	@Override
	public Response findByEstado(String estado) {
		List<Estudiante>query = null;
		Response rsp = new Response();
		if("activo".equals(estado) || "inactivo".equals(estado)) {
			query = repo.buscarEstado(estado);
			rsp.setCode(String.valueOf(HttpStatus.OK));
			rsp.setMessage("lista generada");
			rsp.setEstudiante(query);
		}else {
			rsp.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
			rsp.setMessage("estado no valido");
		}
		return rsp;
	}

	
	
	@Override
	public Response findByIdentidad(Integer numeroDocumento) {
		List<Estudiante>query = null;
		Response rsp = new Response();
		if(numeroDocumento != null) {
			query = repo.buscarDocumento(numeroDocumento);
			rsp.setCode("200");
			rsp.setMessage("operacion exitosa");
			rsp.setEstudiante(query);
		}else {
			rsp.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
		}
		return rsp;
	}

	@Override
	public Estudiante actualizar(Estudiante estudiante) {
		if (repo.findById(estudiante.getId()).isPresent()) {

			Estudiante stu = new Estudiante();
			stu.setPrimerNombre(estudiante.getPrimerNombre());
			stu.setSegundoNombre(estudiante.getSegundoNombre());
			stu.setPrimerApellido(estudiante.getPrimerApellido());
			stu.setSegundoApellido(estudiante.getSegundoApellido());
			stu.setNumeroDocumento(estudiante.getNumeroDocumento());
			stu.setCurso(estudiante.getCurso());
			stu.setEstado(estudiante.getEstado());
			stu.setCreateAt(new Date());
			repo.save(stu);
			return stu;
		}
		return estudiante;

	}

	@Override
	public List<Estudiante> findAll() {
		Response rsp = new Response();
		List<Estudiante> queryEstudiante = null;
		queryEstudiante = repo.findAll();
		if (queryEstudiante.isEmpty()) {
			ResponseEntity.noContent().build();

		} else {

			rsp.setCode("200");
			rsp.setMessage("lista generada");
			rsp.setEstudiante(queryEstudiante);

		}
		return queryEstudiante;
		

	}

	@Override
	@Transactional(readOnly = true)
	public Page<Estudiante> findAll(Pageable page) {
		return repo.findAll(page);
		 
	}







}
