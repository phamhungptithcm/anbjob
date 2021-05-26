package com.nab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nab.domain.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{
	public Optional<Branch> findByBranchCode(String branchCode);

}
