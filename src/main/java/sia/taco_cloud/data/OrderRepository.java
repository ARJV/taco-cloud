package sia.taco_cloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.taco_cloud.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}

// Эта реалицация была для JDBC
//public interface OrderRepository {
//    TacoOrder save(TacoOrder tacoOrder);
//}
