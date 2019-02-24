package hr.vvg.Miokovic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.vvg.Miokovic.entitet.Predavac;


/**
 * 
 * Predstavlja interface klasu za repozitorij Predavača.
 * 
 * @author Denis
 *
 */

@Repository
public interface PredavacRepository extends CrudRepository<Predavac, Long> {

}
