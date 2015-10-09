/**
 * 
 */
package com.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PersonDAO;
import com.dao.PolicyDAO;
import com.dto.Person;
import com.dto.Policy;

/**
 * @author 492086
 *
 */
public class PolicyServiceImpl implements PolicyService{

	private static final Logger log = LoggerFactory.getLogger(PolicyServiceImpl.class);
			
	@Autowired
	public PolicyDAO policyDAO;
	/**
	 * @param policyDAO the policyDAO to set
	 */
	public void setPolicyDAO(PolicyDAO policyDAO) {
		log.info("PolicyServiceImpl -> setPolicyDAO");
		this.policyDAO = policyDAO;
	}

	@Autowired
	public PersonDAO personDAO;	
	/**
	 * @param personDAO the personDAO to set
	 */
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Transactional
	public int createPolicy(Policy policyDTO) {

		return policyDAO.create(policyDTO);
	}

	@Transactional
	public List getPolicy() {
		return policyDAO.read();
	}
	
	@Transactional
	public Policy getPolicy(int policyId) {
		
		return policyDAO.read(policyId);
	}

	@Transactional
	public boolean updatePolicy(Policy policyDTO) {
		
		return policyDAO.update(policyDTO);
	}

	@Transactional
	public boolean deletePolicy(int policyId) {

		return policyDAO.delete(policyId);
	}

	@Transactional
	public List<Person> getPolicyHolders(int policyId) {
		Policy policy = new Policy();
		policy.setId(policyId);
		List<Person> persons = personDAO.read(policy);
		return persons;
	}

	@Transactional
	public Person getPolicyHolder(int policyId, int phId) {		
		Policy policy = new Policy();
		policy.setId(policyId);
		Person person = personDAO.read(policy,phId);
		
		return person;
	}

	@Transactional
	public int addPolicyHolder(Person person) {
		
		return personDAO.create(person);
	}

	@Transactional
	public boolean updatePolicyHolder(Person personObj) {
		return personDAO.update(personObj);
	}	

	@Transactional
	public boolean deletePolicyHolder(int policyId, int phId) {
		Policy policy = new Policy();
		policy.setId(policyId);
		
		return personDAO.delete(policy, phId);
	}	
	
}
