/**
 * 
 */
package com.bean;

import java.util.List;

import com.vo.PersonVO;
import com.vo.PolicyVO;

/**
 * @author 492086
 *
 */
public interface PolicyBean {

	public int CreatePolicy(PolicyVO policyDto);
	public List<PolicyVO> getPolicy();
	public PolicyVO getPolicy(int policyId);
	public boolean updatePolicy(PolicyVO policyDto);
	public boolean deletePolicy(int policyId);
	
	public List<PersonVO> getPolicyHolders(int policyId);
	public PersonVO getPolicyHolder(int policyId, int phId);
	public int addPolicyHolder(PersonVO person);
	public boolean updatePolicyHolder(PersonVO personObj);
	public boolean deletePolicyHolder(int id, int phId);
}
