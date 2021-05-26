package com.nab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nab.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	public Optional<Order> findByOrderCode(String code);

}
