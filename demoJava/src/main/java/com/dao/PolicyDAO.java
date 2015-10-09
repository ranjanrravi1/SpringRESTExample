/**
 * 
 */
package com.dao;

import java.util.List;

import com.dto.Policy;

/**
 * @author 492086
 *
 */
public interface PolicyDAO {

	public int create(Policy policyDTO);
	public List<Policy> read();
	public Policy read(int policyId);
	public boolean update(Policy policyDTO);
	public boolean delete(int policyId);
	
}
