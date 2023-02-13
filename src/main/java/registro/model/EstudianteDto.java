package registro.model;


import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstudianteDto {

	private Long id;

	private String primerNombre;

	private String segundoNombre;

	private String primerApellido;

	private String segundoApellido;

	private List<DocumentoIdentidad> tipoDocumento;

	private Integer numeroDocumento;

	private List<Curso> curso;

	private String estado;

	

}
