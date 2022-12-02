package com.ordermanagement.service;

import java.util.List;

import com.ordermanagement.dto.OrderDto;
import com.ordermanagement.model.Order;

public interface OrderMgmtService {

	String saveOrder(OrderDto orderDto);

	List<Order> orders();

	String updateOrder(int tokenNumber,OrderDto orderDto);

	String deleteOrder(int tokenNumber);

}
