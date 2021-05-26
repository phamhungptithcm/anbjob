package com.nab.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nab.domain.Branch;
import com.nab.dto.BranchCreationDTO;
import com.nab.dto.BranchRequestDTO;
import com.nab.dto.BranchResponseDTO;
import com.nab.helper.CommonUtil;
import com.nab.repository.BranchRepository;
import com.nab.service.IBranchService;

@Service
@Transactional
public class BranchService implements IBranchService {

	private Logger logger = LoggerFactory.getLogger(BranchService.class);

	@Autowired
	private BranchRepository branchRepository;

	@Value("${format.datetime.string:MM/dd/yyyy hh:mm:ssZ}")
	private String formatDateStr;

	private static final boolean IS_LETER = true;
	private static final boolean IS_NUMBER = false;
	private static final int CODE_LENGTH = 5;

	@Override
	public boolean delete(String code) {
		logger.debug("Start deleteing branch {}", code);
		boolean result = false;
		try {
			Optional<Branch> optional = branchRepository.findByBranchCode(code);
			if (optional.isPresent()) {
				Branch branch = optional.get();
				branch.setActive(false);
				branchRepository.save(branch);
				result = true;
				logger.debug("Branch {} is deleted", code);
			} else {
				logger.debug("Cannot find branch {} to delete", code);
			}

		} catch (Exception e) {
			logger.error("Cannot delete branch: " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public BranchResponseDTO save(BranchCreationDTO creationDTO) {
		logger.debug("Start saving branch {}", creationDTO);
		Branch branch = this.convertDomain(creationDTO);
		branchRepository.save(branch);
		return this.convertToDTO(branch);
	}

	@Override
	public BranchResponseDTO get(String code) {
		logger.debug("Start getting branch {}", code);
		BranchResponseDTO responseDTO = null;
		if (StringUtils.isNotBlank(code)) {
			Optional<Branch> optional = branchRepository.findByBranchCode(code);
			if (optional.isPresent()) {
				Branch branch = optional.get();
				responseDTO = this.convertToDTO(branch);
			}
		}
		return responseDTO;
	}

	@Override
	public List<BranchResponseDTO> gets(BranchRequestDTO requestDTO) {
		logger.debug("Start getting branchs {}", requestDTO);
		return null;
	}

	@Override
	public Branch convertDomain(BranchCreationDTO creationDTO) {
		logger.debug("Start converting branch {}", creationDTO);
		Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
		Branch branch = null;
		try {
			if (StringUtils.isNotBlank(creationDTO.getBranchCode())) {
				Optional<Branch> optional = branchRepository.findByBranchCode(creationDTO.getBranchCode());
				if (optional.isPresent()) {
					branch = optional.get();
					branch.setBranchName(creationDTO.getBranchName());
					branch.setModifiedBy(creationDTO.getActionBy());
					branch.setModifiedDate(currTimestamp);
				}
			} else {
				branch = new Branch();
				String branchCode = null;
				do {
					branchCode = CommonUtil.randomString(CODE_LENGTH, IS_LETER, IS_NUMBER);
				} while (checkCodeExist(branchCode));
				branch.setBranchCode(branchCode);
				branch.setBranchName(creationDTO.getBranchName());
				branch.setModifiedBy(creationDTO.getActionBy());
				branch.setModifiedDate(currTimestamp);
				branch.setCreatedBy(creationDTO.getActionBy());
				branch.setCreatedDate(currTimestamp);
				branch.setActive(true);
			}
		} catch (Exception e) {
			logger.error("Cannot convert to domain: " + e.getMessage(), e);
		}

		return branch;
	}

	@Override
	public BranchResponseDTO convertToDTO(Branch branch) {
		logger.debug("Start converting branch {}", branch);
		BranchResponseDTO responseDTO = null;
		try {
			if (branch != null) {
				responseDTO = new BranchResponseDTO();
				responseDTO.setBranchCode(branch.getBranchCode());
				responseDTO.setBranchName(branch.getBranchName());
				responseDTO.setModifiedBy(branch.getModifiedBy());
				responseDTO.setCreatedBy(branch.getCreatedBy());

				String createdDate = CommonUtil.convertTimestampToString(branch.getCreatedDate(), formatDateStr);
				String modifiedDate = CommonUtil.convertTimestampToString(branch.getModifiedDate(), formatDateStr);

				responseDTO.setCreatedDate(createdDate);
				responseDTO.setModifiedDate(modifiedDate);
				responseDTO.setFormatDateStr(formatDateStr);
			}
		} catch (Exception e) {
			logger.error("Cannot convert to Dto: " + e.getMessage(), e);
		}

		return responseDTO;
	}

	@Override
	public boolean checkCodeExist(String code) {
		logger.debug("Start checking branch code {}", code);
		Optional<Branch> optional = branchRepository.findByBranchCode(code);
		return optional.isPresent() ? true : false;
	}

	@Override
	public Branch getRoot(String code) {
		return  branchRepository.findByBranchCode(code).get();
	}

}
