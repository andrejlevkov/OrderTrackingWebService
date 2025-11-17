package com.trackorder.restapi.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trackorder.restapi.entity.ChangeOrderLocationRequest;
import com.trackorder.restapi.entity.ChangeOrderStatusRequest;
import com.trackorder.restapi.entity.GetOrdersByDateRequest;
import com.trackorder.restapi.entity.GetOrdersInDeliveryResponseObject;
import com.trackorder.restapi.entity.Order;
import com.trackorder.restapi.repository.OrderRepository;

@RestController
public class OrderController {
	
	public long getDateDifference(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long difference_In_Time = -1;
		try {
			Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);
 
            // Calculate time difference in milliseconds
            difference_In_Time = d2.getTime() - d1.getTime();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return difference_In_Time;
	}
	
	@Autowired
	OrderRepository orderRepository;
	
	@GetMapping("/orders/{id}")
	public Order getOrderById(@PathVariable int id) {
		return orderRepository.findById(id).get();
	}
	
	@GetMapping("/orders/status/{status}")
	public List<Order> getOrdersByStatus(@PathVariable String status){
		String stat = new String();
		if(status.equals("vo_tranzit") || status.equals("se_priprema")) {
			String[] arr = status.split("_");
			stat = arr[0] + " " + arr[1];
		}
		else {
			stat = status;
		}
		List<Order> orders = orderRepository.findOrdersByStatus(stat);
		
		return orders;
	}
	
	@GetMapping("/orders/indelivery")
	public List<GetOrdersInDeliveryResponseObject> getOrdersInDelivery(){
		List<Order> orders = orderRepository.findOrdersInDelivery();
		List<GetOrdersInDeliveryResponseObject> ordersList = new ArrayList<GetOrdersInDeliveryResponseObject>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(Order o : orders) {
			GetOrdersInDeliveryResponseObject object = new GetOrdersInDeliveryResponseObject();
			object.setOrder(o);
			
			long dateDiff = -1;
			if(o.getStatus().equals("primena")) {
				dateDiff = getDateDifference(o.getDeparture(), o.getCollected());
			}
			else {
				String now = simpleDateFormat.format(new Date());
				dateDiff = getDateDifference(o.getDeparture(), now);
//				object.setTimeElapsed(dateDiff);
			}
			object.setTimeElapsed(dateDiff);
			
			ordersList.add(object);
		}
		return ordersList;
	}
	
	
	@GetMapping("/orders/date")
	public List<Order> getOrdersByDate(@RequestBody GetOrdersByDateRequest request){
		List<Order> orders = orderRepository.findOrdersByDate(request.getDate());
		return orders;
	}
	
	@PatchMapping("/orders/update/location")
	public Order changeOrderLocation(@RequestBody ChangeOrderLocationRequest request) {
		int orderId = request.getOrderId();
		String location = request.getLocation();
		
		Order order = orderRepository.findById(orderId).get();
		order.setLocation(location);
		orderRepository.save(order);
		return order;
	}
	
	@PatchMapping("/orders/update/status")
	public Order changeOrderStatus(@RequestBody ChangeOrderStatusRequest request) {
		int orderId = request.getId();
		String status = request.getStatus();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String now = simpleDateFormat.format(d);
		
		Order order = orderRepository.findById(orderId).get();
		if(status.equals("vo tranzit")) {
			order.setDeparture(now);
		}
		else if(status.equals("primena")) {
			order.setCollected(now);
		}
		order.setStatus(status);
		orderRepository.save(order);
		return order;
	}
	
}
