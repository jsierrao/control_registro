package registro.serviceImp;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static registro.model.Util.*;
import lombok.extern.slf4j.Slf4j;
import registro.model.Curso;
import registro.model.DocumentoIdentidad;
import registro.model.Estudiante;
import registro.model.EstudianteDto;
import registro.model.Util;
import registro.repositorio.CursoRepository;
import registro.repositorio.DocumentoRepository;
import registro.repositorio.EstudianteRepository;

import registro.response.Response;
import registro.service.EstudianteService;

@Service

public class EstudianteServiceImp implements EstudianteService {

	@Autowired
	EstudianteRepository repo;

	@Autowired
	CursoRepository curso;

	@Autowired
	DocumentoRepository docu;

	@Override
	public Estudiante crear(Estudiante estudiante) {
		estudiante.setCreateAt(new Date());
		return repo.save(estudiante);

	}

	@Override
	public Response findByEstado(String estado) {
		List<Estudiante> query = null;
		Response rsp = new Response();
		if ("activo".equals(estado) || "inactivo".equals(estado)) {
			query = repo.buscarEstado(estado);
			rsp.setCode(String.valueOf(HttpStatus.OK));
			rsp.setMessage("lista generada");
			rsp.setEstudiante(query);
		} else {
			rsp.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
			rsp.setMessage("estado no valido");
		}
		return rsp;
	}

	@Override
	public Response findByIdentidad( Integer numeroDocumento) {

		/*
		 * Response rsp = new Response(); if (tpDoc.equalsIgnoreCase(CC)) { tpDoc =
		 * "cedula ciudadania"; } else if (tpDoc.equalsIgnoreCase(CE)) { tpDoc =
		 * "cedula extranjeria"; } else if (tpDoc.equalsIgnoreCase(PSP)) { tpDoc =
		 * "pasaporte"; } else if (tpDoc.equalsIgnoreCase(RC)) { tpDoc =
		 * "registro civil"; } else if (tpDoc.equalsIgnoreCase(TI)) { tpDoc =
		 * "tarjeta de identidad"; } else { throw new
		 * IllegalArgumentException("tipo de docuemento invalido: " + tpDoc);
		 * 
		 * }
		 */
		
		Response rsp = new Response();
		if(numeroDocumento != null) {

		List<Estudiante> query = repo.buscarDocumento(numeroDocumento);

		rsp.setCode("200");
		rsp.setMessage("operacion exitosa");
		rsp.setEstudiante(query);}

		return rsp;

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

	@Override
	public Estudiante actualizarPorId(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public EstudianteDto dto() {
		List<Curso> cur = curso.findAll();
		List<DocumentoIdentidad> doc = docu.findAll();
		EstudianteDto stu = new EstudianteDto();
		stu.setCurso(cur);
		stu.setTipoDocumento(doc);

		return stu;
	}

	@Override
	public Estudiante porId(Long id) {
		
		return repo.findById(id).orElse(null);
	}

}
