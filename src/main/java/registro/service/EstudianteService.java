package registro.service;




import registro.model.Estudiante;
import registro.response.Response;

public interface EstudianteService {

	public Estudiante crear(Estudiante estudiante);

	public Response findByEstado(String estado);

	public Response findAll();

	public Response findByIdentidad(Integer numeroDocumento);

	public Estudiante actualizar(Estudiante estudiante);

}
