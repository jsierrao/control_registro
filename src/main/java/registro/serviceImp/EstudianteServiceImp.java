package registro.serviceImp;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import registro.model.Curso;
import registro.model.DocumentoIdentidad;
import registro.model.Estudiante;
import registro.repositorio.EstudianteRepository;
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
	public List<Estudiante> findByEstado(String estado) {
		return null;
	}

	@Override
	public List<Estudiante> findByIdentidad(DocumentoIdentidad identidad) {
		return Collections.emptyList();
	}

	@Override
	public Estudiante actualizar(Estudiante estudiante) {
		if (repo.findById(estudiante.getId()).isPresent()) {
			
			Estudiante stu = new Estudiante();
			stu.setPrimerNombre(estudiante.getPrimerNombre());
			stu.setSegundoNombre(estudiante.getSegundoNombre());
			stu.setPrimerApellido(estudiante.getPrimerApellido());
			stu.setSegundoApellido(estudiante.getSegundoApellido());
			stu.setNumeroIdentidad(estudiante.getNumeroIdentidad());
			stu.setCurso(estudiante.getCurso());
			stu.setEstado(estudiante.getEstado());
			stu.setCreateAt(new Date());
			repo.save(stu);
			repo.delete(estudiante);
			return stu;
		}
		return estudiante;

	}

	@Override
	public List<Estudiante> findAll() {

		return repo.findAll();
	}

}
