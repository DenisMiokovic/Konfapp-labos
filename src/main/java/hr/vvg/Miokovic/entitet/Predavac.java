package hr.vvg.Miokovic.entitet;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Predstavlja entitet predavača koji je definiran sa imenom i pozicijom.
 * 
 * @author Denis
 *
 */

@Data
@Entity
@Table(name="Predavac")
@NoArgsConstructor
public class Predavac implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "{msg.lecturerNameError}")
	@Size(min = 2, max = 50, message = "{msg.lecturerNameSizeError}")
	@Column(name="ime")
	public String ime;
	
	@NotNull(message = "{msg.lecturerPositionError}")
	@Column(name="pozicija")
	@Enumerated(EnumType.STRING)
	public PozicijaPredavaca pozicijaPredavaca;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="datumUpisa")
	private Date datumUpisa;

	public enum PozicijaPredavaca {

		JUNIOR, MID, SENIOR
	}

}
