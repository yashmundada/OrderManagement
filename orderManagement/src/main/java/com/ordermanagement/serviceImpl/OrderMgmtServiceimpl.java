package com.ordermanagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordermanagement.dao.OrderMgmtDao;
import com.ordermanagement.dto.OrderDto;
import com.ordermanagement.model.Order;
import com.ordermanagement.service.OrderMgmtService;

@Transactional
@Service
public class OrderMgmtServiceimpl implements OrderMgmtService {
	@Autowired
	OrderMgmtDao orderMgmtDao;
	public static int a = 0;

	@Override
	public String saveOrder(OrderDto orderDto) {
		Order order = new Order();
		order.setUserId(orderDto.getUserId());
		order.setTokennumber(++a);
		order.setOrderName(orderDto.getOrderName());
		order.setQuantity(orderDto.getQuantity());
		order.setPrice(orderDto.getPrice());
		orderMgmtDao.save(order);
		return "Your" + orderDto.getOrderName() + "order is Placed,Your order no. is" + a;
	}

	@Override
	public List<Order> orders() {

		return orderMgmtDao.findAll();
	}

	@Override
	public String updateOrder(int tokenNumber, OrderDto orderDto) {
		Optional<Order> token = orderMgmtDao.findByTokenNumber(tokenNumber);
		if (token.isPresent()) {
			Order order = orderMgmtDao.getByTokenNumber(tokenNumber);
			order.setOrderName(orderDto.getOrderName());
			order.setPrice(orderDto.getPrice());
			order.setQuantity(orderDto.getQuantity());
			orderMgmtDao.save(order);
			return "Order updated Successfully";
		} else {
			return "Order is not confirm";
		}
	}

	@Override
	public String deleteOrder(int tokenNumber) {
		System.out.println(tokenNumber);
		Optional<Order> token = orderMgmtDao.findByTokenNumber(tokenNumber);
		System.out.println(token.isPresent());
		if (token.isPresent()) {
			orderMgmtDao.deleteByTokenNumber(tokenNumber);
			return " Your Order Cancel Successfully...";
		} else {
			return "Order not Exist...";
		}
	}

}
