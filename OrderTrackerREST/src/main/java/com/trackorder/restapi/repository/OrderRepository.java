package com.trackorder.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackorder.restapi.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query(value = "SELECT * FROM orders WHERE status = ?1", nativeQuery = true)
	List<Order> findOrdersByStatus(String status);
	
	@Query(value = "SELECT * FROM orders WHERE status = \"vo tranzit\" OR status = \"primena\"", nativeQuery = true)
	List<Order> findOrdersInDelivery();
	
	@Query(value = "SELECT * FROM orders WHERE created LIKE ?%1", nativeQuery = true)
	List<Order> findOrdersByDate(String date);
}
