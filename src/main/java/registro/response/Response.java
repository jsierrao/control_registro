package registro.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import registro.model.Estudiante;

@Setter
@Getter
public class Response {
	
	
	private String code;
	
	private String message;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<Estudiante> estudiante;

}
