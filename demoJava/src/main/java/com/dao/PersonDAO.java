/**
 * 
 */
package com.dao;

import java.util.List;

import com.dto.Person;
import com.dto.Policy;

/**
 * @author 492086
 *
 */
public interface PersonDAO {
	public List<Person> read(Policy policy);
	public Person read(Policy policy, int phId);
	public int create(Person person);
	public boolean update(Person person);
	public boolean delete(Policy policy, int phId);
	
}
