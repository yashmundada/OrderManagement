package com.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.dto.OrderDto;

import com.ordermanagement.model.Order;
import com.ordermanagement.service.OrderMgmtService;

@RestController
public class OrderManagementController {

	@Autowired
	private OrderMgmtService orderMgmtService ;
	
	@PostMapping("/user")
	public String OrderSave(@RequestBody OrderDto orderDto)
	{
		return orderMgmtService.saveOrder(orderDto);
	}
	
	@GetMapping("/Order")
	public List<Order> allOrders(){
		return orderMgmtService.orders();
	}
	
	@PutMapping("/update/{tokenNumber}")
	public String OrderUpdate(@PathVariable("tokenNumber") int tokenNumber,@RequestBody OrderDto orderDto) {
	return orderMgmtService.updateOrder(tokenNumber,orderDto);
	}

	@DeleteMapping("/delete/{tokenNumber}")
	public String OrderDelete(@PathVariable("tokenNumber") int tokenNumber)
	{
		return orderMgmtService.deleteOrder(tokenNumber);
	}

}
