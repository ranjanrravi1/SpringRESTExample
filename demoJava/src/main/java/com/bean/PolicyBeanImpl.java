/**
 * 
 */
package com.bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dto.Person;
import com.dto.Policy;
import com.service.PolicyService;

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

	
	public List<Policy> getPolicy() {
		log.info("inside PolicyBeanImpl -> getPolicy ");
		
		List<Policy> policies = policyService.getPolicy();
		return policies;
	}

	public Policy getPolicy(int policyId) {
		log.info("inside PolicyBeanImpl -> getPolicy - with policyid : "+policyId);
		
		Policy policyDTO = policyService.getPolicy(policyId);
		return policyDTO;
	}
	
	public int CreatePolicy(Policy policyDTO) {
		log.info("inside CreatePolicy method ");
		
		return policyService.createPolicy(policyDTO);
	}

	public boolean updatePolicy(Policy policyDTO) {
		log.info("inside updatePolicy method ");
		return policyService.updatePolicy(policyDTO);
	}

	public boolean deletePolicy(int policyId) {
		log.info("inside deletePolicy method with policyid "+policyId);
		return policyService.deletePolicy(policyId);
	}

	public List<Person> getPolicyHolders(int policyId) {
		log.info("inside PolicyBeanImpl -> getPolicyHolders - with policyid : "+policyId);
		
		List<Person> persons = policyService.getPolicyHolders(policyId);
		return persons;
	}

	public Person getPolicyHolder(int policyId, int phId) {
		log.info("inside PolicyBeanImpl -> getPolicyHolder - with policyid : "+policyId);
		
		Person person = policyService.getPolicyHolder(policyId, phId);
		return person;
	}

	public int addPolicyHolder(Person person) {
		log.info("inside PolicyBeanImpl -> addPolicyHolder ");
		
		int phId = policyService.addPolicyHolder(person);
		return phId;
	}


	public boolean updatePolicyHolder(Person personObj) {
		return policyService.updatePolicyHolder(personObj);
	}
	
	public boolean deletePolicyHolder(int policyId, int phId) {
		return policyService.deletePolicyHolder(policyId, phId);
	}
}
