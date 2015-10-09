/**
 * 
 */
package com.bean;

import java.util.List;

import com.dto.Person;
import com.dto.Policy;

/**
 * @author 492086
 *
 */
public interface PolicyBean {

	public int CreatePolicy(Policy policyDto);
	public List<Policy> getPolicy();
	public Policy getPolicy(int policyId);
	public boolean updatePolicy(Policy policyDto);
	public boolean deletePolicy(int policyId);
	
	public List<Person> getPolicyHolders(int policyId);
	public Person getPolicyHolder(int policyId, int phId);
	public int addPolicyHolder(Person person);
	public boolean updatePolicyHolder(Person personObj);
	public boolean deletePolicyHolder(int id, int phId);
}
