package sia.taco_cloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.taco_cloud.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
