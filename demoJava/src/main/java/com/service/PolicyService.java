/**
 * 
 */
package com.service;

import java.util.List;

import com.dto.Person;
import com.dto.Policy;

/**
 * @author 492086
 *
 */
public interface PolicyService {

	public int createPolicy(Policy policyDTO);
	public List<Policy> getPolicy();
	public Policy getPolicy(int policyId);
	public boolean updatePolicy(Policy policyDTO);
	public boolean deletePolicy(int policyId);
	
	public List<Person> getPolicyHolders(int policyId);
	public Person getPolicyHolder(int policyId, int phId);
	public int addPolicyHolder(Person person);
	public boolean updatePolicyHolder(Person personObj);
	public boolean deletePolicyHolder(int policyId, int phId);
}
