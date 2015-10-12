/**
 * 
 */
package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.dto.Person;
import com.dto.Policy;

/**
 * @author 492086
 *
 */
public class PersonDAOImpl implements PersonDAO{

	
	@Autowired
	private SessionFactory sessionFactory;

	public PersonDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Person> read(Policy policy) {
		List<Person> persons = sessionFactory.getCurrentSession()
				.createCriteria(Person.class).createCriteria("policy", "p")
				.add(Restrictions.eq("p.id", policy.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		System.out.println("persons "+persons);
		
		return persons;
	}

	@Override
	public Person read(Policy policy,int phId) {
		List<Person> persons = sessionFactory.getCurrentSession()
				.createCriteria(Person.class,"person").createCriteria("policy", "p")
				.add(Restrictions.eq("p.id", policy.getId()))
				.add(Restrictions.eq("person.id", phId))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		Person person = null;
		if(persons != null && persons.size() >0){
			person = persons.get(0);
		}		
		return person;
	}

	@Override
	public int create(Person person) {
		sessionFactory.getCurrentSession().save(person);
		
		return person.getId();
	}

	@Override
	public boolean update(Person person) {
		sessionFactory.getCurrentSession().update(person);
		return true;
	}

	@Override
	public boolean delete(Policy policy, int phId) {
		Session s = sessionFactory.getCurrentSession();
		Policy p = (Policy) s.get(Policy.class, policy.getId());
		Person person = (Person) s.get(Person.class, phId);
		p.getPersonList().remove(person);
				
		/*List<Person> persons = s.createCriteria(Person.class,"person").createCriteria("policy", "p")
				.add(Restrictions.eq("p.id", policy.getId()))
				.add(Restrictions.eq("person.id", phId))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		Person person = null;
		if(persons != null && persons.size() >0){
			person = persons.get(0);
		}		
		*/
		boolean deleted = false;
		if(person != null){
			s.delete(person);
			deleted  = true;
		}
		return deleted;
	}

	

}
