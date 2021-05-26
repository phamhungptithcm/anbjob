package com.nab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nab.domain.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long>{
	public Optional<OrderStatus> findByOrderStatusCode(String code);

}
