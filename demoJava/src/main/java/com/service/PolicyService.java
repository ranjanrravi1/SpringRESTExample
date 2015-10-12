/**
 * 
 */
package com.service;

import java.util.List;

import com.vo.PersonVO;
import com.vo.PolicyVO;

/**
 * @author 492086
 *
 */
public interface PolicyService {

	public int createPolicy(PolicyVO policyVO);
	public List getPolicy();
	public PolicyVO getPolicy(int policyId);
	public boolean updatePolicy(PolicyVO policyVO);
	public boolean deletePolicy(int policyId);
	
	public List getPolicyHolders(int policyId);
	public PersonVO getPolicyHolder(int policyId, int phId);
	public int addPolicyHolder(PersonVO personVO);
	public boolean updatePolicyHolder(PersonVO personVO);
	public boolean deletePolicyHolder(int policyId, int phId);
}
