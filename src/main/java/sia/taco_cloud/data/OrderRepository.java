package sia.taco_cloud.data;

import org.springframework.stereotype.Repository;
import sia.taco_cloud.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder tacoOrder);
}
