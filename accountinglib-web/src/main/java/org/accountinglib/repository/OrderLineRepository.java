package org.accountinglib.repository;

import org.accountinglib.model.OrderLine;
import org.accountinglib.model.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderLineRepository extends CrudRepository<OrderLine, Integer>{

    List<OrderLine> findByOrders(Orders orders);

}
