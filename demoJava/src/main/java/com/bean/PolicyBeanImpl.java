/**
 * 
 */
package com.bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.service.PolicyService;
import com.vo.PersonVO;
import com.vo.PolicyVO;

/**
 * @author 492086
 *
 */
public class PolicyBeanImpl implements PolicyBean {

	private static final Logger log = LoggerFactory.getLogger(PolicyBeanImpl.class);
			
	@Autowired
	public PolicyService policyService;
	
	/**
	 * @param policyService the policyService to set
	 */
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	
	public List<PolicyVO> getPolicy() {
		log.info("inside PolicyBeanImpl -> getPolicy ");
		
		List<PolicyVO> policies = policyService.getPolicy();
		return policies;
	}

	public PolicyVO getPolicy(int policyId) {
		log.info("inside PolicyBeanImpl -> getPolicy - with policyid : "+policyId);
		
		PolicyVO policyVO = policyService.getPolicy(policyId);
		return policyVO;
	}
	
	public int CreatePolicy(PolicyVO policyVO) {
		log.info("inside CreatePolicy method ");
		
		return policyService.createPolicy(policyVO);
	}

	public boolean updatePolicy(PolicyVO policyVO) {
		log.info("inside updatePolicy method ");
		return policyService.updatePolicy(policyVO);
	}

	public boolean deletePolicy(int policyId) {
		log.info("inside deletePolicy method with policyid "+policyId);
		return policyService.deletePolicy(policyId);
	}

	public List<PersonVO> getPolicyHolders(int policyId) {
		log.info("inside PolicyBeanImpl -> getPolicyHolders - with policyid : "+policyId);
		
		List<PersonVO> persons = policyService.getPolicyHolders(policyId);
		return persons;
	}

	public PersonVO getPolicyHolder(int policyId, int phId) {
		log.info("inside PolicyBeanImpl -> getPolicyHolder - with policyid : "+policyId);
		
		PersonVO person = policyService.getPolicyHolder(policyId, phId);
		return person;
	}

	public int addPolicyHolder(PersonVO personVO) {
		log.info("inside PolicyBeanImpl -> addPolicyHolder ");
		
		int phId = policyService.addPolicyHolder(personVO);
		return phId;
	}


	public boolean updatePolicyHolder(PersonVO personVO) {
		return policyService.updatePolicyHolder(personVO);
	}
	
	public boolean deletePolicyHolder(int policyId, int phId) {
		return policyService.deletePolicyHolder(policyId, phId);
	}
}
