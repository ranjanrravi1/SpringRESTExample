/**
 * 
 */
package com.utility;

import java.util.ArrayList;
import java.util.List;

import com.dto.Person;
import com.dto.Policy;
import com.vo.PersonVO;
import com.vo.PolicyVO;

/**
 * @author 492086
 *
 */
public class PolicyUtils {

	public static PolicyVO extractPolicyVO(Policy policyDTO) {
		if(policyDTO == null){
			return null;
		}
		PolicyVO  vo = new PolicyVO();
		vo.setId(policyDTO.getId());
		vo.setName(policyDTO.getName());
		vo.setTerm(policyDTO.getTerm());
		vo.setPremium(policyDTO.getPremium());
		
		List<Person> persons =  policyDTO.getPersonList();
		List<PersonVO> personVOs = new ArrayList<PersonVO>();
		
		for (Person p : persons) {
			PersonVO personVO = new PersonVO();
			personVO.setId(p.getId());
			personVO.setName(p.getName());
			personVO.setAddress(p.getAddress());
			personVO.setAge(p.getAge());
			personVOs.add(personVO);
		}
		
		vo.setPersonVOs(personVOs);
		return vo;
	}

	public static List<PolicyVO> extractAllPolicyVOs(List<Policy> policies) {
		List<PolicyVO> policyVOs  = new ArrayList<PolicyVO>();
		
		for (Policy policy : policies) {
			PolicyVO vo  = new PolicyVO();
			vo.setId(policy.getId());
			vo.setName(policy.getName());
			vo.setTerm(policy.getTerm());
			vo.setPremium(policy.getPremium());
			
			List<Person> persons =  policy.getPersonList();
			List<PersonVO> personVOs = new ArrayList<PersonVO>();
			
			for (Person p : persons) {
				PersonVO personVO = new PersonVO();
				personVO.setId(p.getId());
				personVO.setName(p.getName());
				personVO.setAddress(p.getAddress());
				personVO.setAge(p.getAge());
				personVOs.add(personVO);
			}
			
			vo.setPersonVOs(personVOs);
			
			policyVOs.add(vo);
			
		}
		return policyVOs;
	}
	
	public static PersonVO extractPersonVO(Person person) {
		if(person == null){
			return null;
		}
		PersonVO personVO = null;
		if (person != null) {
			personVO = new PersonVO();
			personVO.setId(person.getId());
			personVO.setName(person.getName());
			personVO.setAddress(person.getAddress());
			personVO.setAge(person.getAge());
			personVO.setPolicyId(person.getPolicy().getId());
		}
		return personVO;
	}

	public static List<PersonVO> extractAllPersonVOs(List<Person> persons) {
		List<PersonVO> personVOs  = new ArrayList<PersonVO>();
		
		if (persons != null) {
			for (Person p : persons) {
				PersonVO personVO = new PersonVO();
				personVO.setId(p.getId());
				personVO.setName(p.getName());
				personVO.setAddress(p.getAddress());
				personVO.setAge(p.getAge());
				personVO.setPolicyId(p.getPolicy().getId());
				personVOs.add(personVO);
			}
		}
		return personVOs;
	}
	
	public static Policy extractPolicy(PolicyVO policyVO) {
		if(policyVO == null){
			return null;
		}
		
		Policy  policy = new Policy();
		policy.setId(policyVO.getId());
		policy.setName(policyVO.getName());
		policy.setTerm(policyVO.getTerm());
		policy.setPremium(policyVO.getPremium());
		
		List<PersonVO> personVOs =  policyVO.getPersonVOs();
		List<Person> persons = new ArrayList<Person>();
		if(personVOs != null){
			for (PersonVO p : personVOs) {
				Person person = new Person();
				person.setName(p.getName());
				person.setAddress(p.getAddress());
				person.setAge(p.getAge());
				person.setPolicy(policy);
				persons.add(person);
			}
			policy.setPersonList(persons);
		}
		
		return policy;
	}
	
	public static Person extractPerson(PersonVO personVO) {
		if(personVO == null){
			return null;
		}
		Person person = new Person();
		if (personVO != null) {
			person.setId(personVO.getId());
			person.setName(personVO.getName());
			Policy policy = new Policy();
			policy.setId(personVO.getPolicyId());
			person.setPolicy(policy);
			person.setAddress(personVO.getAddress());
			person.setAge(personVO.getAge());
		}
		
		return person;
	}
}
