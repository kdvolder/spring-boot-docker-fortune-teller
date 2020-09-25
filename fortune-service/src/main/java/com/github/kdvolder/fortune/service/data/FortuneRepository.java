package com.github.kdvolder.fortune.service.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FortuneRepository extends CrudRepository<FortuneMessage, Long> {

//	@Query("SELECT * FROM fortunes ORDER BY RANDOM()")
//	Iterable<FortuneMessage> listInRandomOrder();
	

}
