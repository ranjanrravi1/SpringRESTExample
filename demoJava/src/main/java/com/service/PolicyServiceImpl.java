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
import com.utility.PolicyUtils;
import com.vo.PersonVO;
import com.vo.PolicyVO;

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
	public int createPolicy(PolicyVO policyVO) {

		Policy policyDTO = PolicyUtils.extractPolicy(policyVO);
		
		return policyDAO.create(policyDTO);
	}

	@Transactional
	public List getPolicy() {
		List<Policy> policies = policyDAO.read();
		
		List<PolicyVO> policyVOs = PolicyUtils.extractAllPolicyVOs(policies);
		
		return policyVOs;
	}
	
	@Transactional
	public PolicyVO getPolicy(int policyId) {
		
		Policy policyDTO = policyDAO.read(policyId);

		if(policyDTO == null){
			return null;
		}
		else{
			return PolicyUtils.extractPolicyVO(policyDTO);
		}
	}

	@Transactional
	public boolean updatePolicy(PolicyVO policyVO) {
		
		Policy policyDTO = PolicyUtils.extractPolicy(policyVO);
		
		return policyDAO.update(policyDTO);
	}

	@Transactional
	public boolean deletePolicy(int policyId) {

		return policyDAO.delete(policyId);
	}

	@Transactional
	public List<PersonVO> getPolicyHolders(int policyId) {
		Policy policy = new Policy();
		policy.setId(policyId);
		List<Person> persons = personDAO.read(policy);
		List<PersonVO> personVOs = PolicyUtils.extractAllPersonVOs(persons);
		return personVOs;
	}

	@Transactional
	public PersonVO getPolicyHolder(int policyId, int phId) {		
		Policy policy = new Policy();
		policy.setId(policyId);
		Person person = personDAO.read(policy,phId);
		
		PersonVO personVO = PolicyUtils.extractPersonVO(person);
		
		return personVO;
	}

	@Transactional
	public int addPolicyHolder(PersonVO personVO) {
		
		Person person = PolicyUtils.extractPerson(personVO);
		
		return personDAO.create(person);
	}

	@Transactional
	public boolean updatePolicyHolder(PersonVO personVO) {
		
		Person person = PolicyUtils.extractPerson(personVO);
		
		return personDAO.update(person);
	}	

	@Transactional
	public boolean deletePolicyHolder(int policyId, int phId) {
		Policy policy = new Policy();
		policy.setId(policyId);
		
		return personDAO.delete(policy, phId);
	}	
	
}
