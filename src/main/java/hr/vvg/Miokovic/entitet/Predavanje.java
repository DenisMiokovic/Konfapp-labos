package hr.vvg.Miokovic.entitet;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Predstavlja entitet predavanja koji je definiran sa podacima o predavaču, temi, kratkom sadržaju i vrsti predavanja.
 * 
 * @author Denis
 *
 */

@Data
@Entity
@Table(name="Predavanje")
@NoArgsConstructor
public class Predavanje implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Valid
	@OneToOne(targetEntity=Predavac.class, cascade=CascadeType.ALL)
	@JoinTable(
		name="Predavanje_Predavac",
        joinColumns = @JoinColumn(name = "predavanje"),
        inverseJoinColumns = @JoinColumn(name = "predavac"))
	private Predavac predavac;
	
	@NotEmpty(message = "{msg.lectureSubjectError}")
	@Size(min = 2, max = 50, message = "{msg.lectureSubjectSizeError}")
	@Column(name="tema")
	private String tema;
	
	@NotEmpty(message = "{msg.lectureAbstractError}")
	@Size(min = 2, max = 150, message = "{msg.lectureAbstractSizeError}")
	@Column(name="sadrzaj")
	private String kratkiSadrzaj;
	
	@NotNull(message = "{msg.lectureTypeError}")
	@Column(name="vrsta")
	@Enumerated(EnumType.STRING)
	private VrstaPredavanja vrstaPredavanja;

	@Column(name="objavljeno")
	private Boolean objavljeno;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="datumUpisa")
	private Date datumUpisa;
	
	private LocalDateTime vrijemeUnosa = LocalDateTime.now();
	
	
	public enum VrstaPredavanja {

		RADIONICA, PREZENTACIJA
	}

}
