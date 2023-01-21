package registro.service;



import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import registro.model.Estudiante;
import registro.response.Response;

@Primary
public interface EstudianteService {

	public Estudiante crear(Estudiante estudiante);

	public Response findByEstado(String estado);

	public List<Estudiante> findAll();

	public Response findByIdentidad(Integer numeroDocumento);

	public Estudiante actualizar(Estudiante estudiante);
	
	public Page findAll(Pageable page);

}
