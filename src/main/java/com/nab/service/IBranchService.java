package com.nab.service;

import java.util.List;

import com.nab.domain.Branch;
import com.nab.dto.BranchCreationDTO;
import com.nab.dto.BranchRequestDTO;
import com.nab.dto.BranchResponseDTO;

public interface IBranchService {
	public boolean delete(String code);
	public BranchResponseDTO save(BranchCreationDTO creationDTO);
	public BranchResponseDTO get(String code);
	public Branch getRoot(String code);
	public List<BranchResponseDTO> gets(BranchRequestDTO requestDTO);
	public Branch convertDomain(BranchCreationDTO creationDTO);
	public BranchResponseDTO convertToDTO(Branch branch);
	public boolean checkCodeExist(String code);
}
