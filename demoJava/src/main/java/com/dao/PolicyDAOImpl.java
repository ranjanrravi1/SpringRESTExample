/**
 * 
 */
package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dto.Person;
import com.dto.Policy;

/**
 * @author 492086
 *
 */
public class PolicyDAOImpl implements PolicyDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public PolicyDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List read() {
		List<Policy> policies = sessionFactory.getCurrentSession()
				.createCriteria(Policy.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return policies;
	}

	public Policy read(int policyId) {
		Policy policy = (Policy) sessionFactory.getCurrentSession().get(
				Policy.class, policyId);

		return policy;
	}

	public int create(Policy policyDTO) {

		int newPolicyId = 0;

		sessionFactory.getCurrentSession().save(policyDTO);
		newPolicyId = policyDTO.getId();
		return newPolicyId;
	}

	public boolean update(Policy policyDTO) {
		boolean updated = false;

		Session s = sessionFactory.getCurrentSession();
		s.update(policyDTO);
		updated = true;

		return updated;
	}

	public boolean delete(int policyId) {
		boolean deleted = false;
		Session s = sessionFactory.getCurrentSession();

		Policy policyDTO = (Policy) s.get(Policy.class, policyId);

		if (policyDTO != null) {
			s.delete(policyDTO);
			deleted = true;
		}

		return deleted;
	}

	public List list() {
		List<Policy> policies = sessionFactory.getCurrentSession()
				.createCriteria(Policy.class).list();
		return policies;
	}

	public int addPerson(Person person) {
		sessionFactory.getCurrentSession().save(person);
		int returnInt = person.getPolicy().getId();

		return returnInt;
	}

}
