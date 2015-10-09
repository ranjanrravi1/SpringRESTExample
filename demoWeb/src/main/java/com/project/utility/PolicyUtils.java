/**
 * 
 */
package com.project.utility;

import java.util.ArrayList;
import java.util.List;

import com.dto.Person;
import com.dto.Policy;
import com.project.vo.PersonVO;
import com.project.vo.PolicyVO;

/**
 * @author 492086
 *
 */
public class PolicyUtils {

	public static PolicyVO extractPolicyInfo(Policy policyDTO) {
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

	public static List<PolicyVO> extractAllPolicies(List<Policy> policies) {
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
}
