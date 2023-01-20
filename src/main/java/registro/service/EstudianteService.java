package registro.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import registro.model.DocumentoIdentidad;

import registro.model.Estudiante;

public interface EstudianteService {

	public Estudiante crear(Estudiante estudiante);

	
	@Query(name = "")
	public List<Estudiante> findByEstado(String estado);

	public List<Estudiante> findAll();

	public List<Estudiante> findByIdentidad(DocumentoIdentidad identidad);

	public Estudiante actualizar(Estudiante estudiante);

}
