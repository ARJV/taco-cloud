package sia.taco_cloud.data.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sia.taco_cloud.data.OrderRepository;


@Service
public class OrderAdminService {

  private OrderRepository orderRepository;

  public OrderAdminService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @PreAuthorize("hasRole('ADMIN')")
  public void deleteAllOrders() {
    orderRepository.deleteAll();
  }

}
