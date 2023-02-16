package Telecom.repositories;

import Telecom.entities.Call;
import org.springframework.data.repository.CrudRepository;

public interface CallRepository extends CrudRepository<Call, Integer> {

}
