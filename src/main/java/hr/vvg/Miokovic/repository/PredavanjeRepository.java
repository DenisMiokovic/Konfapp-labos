package hr.vvg.Miokovic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.vvg.Miokovic.entitet.Predavanje;


/**
 * 
 * Predstavlja interface klasu za repozitorij Predavanja.
 * 
 * @author Denis
 *
 */

@Repository
public interface PredavanjeRepository extends CrudRepository<Predavanje, Long> {

	List<Predavanje> findByTema(String tema);
}