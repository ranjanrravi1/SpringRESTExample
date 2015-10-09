package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.bean.PolicyBean;
import com.dto.Person;
import com.dto.Policy;
import com.project.utility.PolicyUtils;
import com.project.vo.PersonVO;
import com.project.vo.PolicyVO;

@Controller
@RequestMapping("/policy")
public class PolicyController {

	private static final Logger log = LoggerFactory.getLogger(PolicyController.class);
			
	@Autowired
	public PolicyBean policyBean;
	
	/**
	 * @param policyBean the policyBean to set
	 */
	public void setPolicyBean(PolicyBean policyBean) {
		this.policyBean = policyBean;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<PolicyVO>> getAllPolicies() {

		List<Policy> policies = policyBean.getPolicy();

		List<PolicyVO> policyVOs  = PolicyUtils.extractAllPolicies(policies);
		
		return new ResponseEntity<List<PolicyVO>>(policyVOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<PolicyVO> getOnlyPolicy(@PathVariable int id) {

		Policy policyDTO = policyBean.getPolicy(id);

		if(policyDTO == null){
			return null;
		}
		PolicyVO vo = PolicyUtils.extractPolicyInfo(policyDTO);
		
		return new ResponseEntity<PolicyVO>(vo, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> addPolicy(UriComponentsBuilder b, @RequestBody PolicyVO policyDto){
		log.info("Str "+policyDto);
		ObjectMapper mapper = new ObjectMapper();
		PolicyVO policyVO = null;
		
		int returnInt = 0 ;
		
		Policy  vo = new Policy();
		vo.setName(policyDto.getName());
		vo.setTerm(policyDto.getTerm());
		vo.setPremium(policyDto.getPremium());
		
		List<PersonVO> personVOs =  policyDto.getPersonVOs();
		List<Person> persons = new ArrayList<Person>();
		log.info("personVOs "+personVOs);
		
		if(personVOs != null){
			for (PersonVO p : personVOs) {
				Person person = new Person();
				person.setName(p.getName());
				person.setAddress(p.getAddress());
				person.setAge(p.getAge());
				person.setPolicy(vo);
				persons.add(person);
			}
			vo.setPersonList(persons);
		}
		
		returnInt =	policyBean.CreatePolicy(vo);
		HttpHeaders headers = new HttpHeaders();
		UriComponents uriComponents = b.path("/policy/{id}/ph/").buildAndExpand(returnInt);
		headers.setLocation(uriComponents.toUri());
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> updatePolicy(@RequestBody PolicyVO policyVO){
		ObjectMapper mapper = new ObjectMapper();
		
		Policy  vo = new Policy();
		vo.setId(policyVO.getId());
		vo.setName(policyVO.getName());
		vo.setTerm(policyVO.getTerm());
		vo.setPremium(policyVO.getPremium());
		
		List<PersonVO> personVOs =  policyVO.getPersonVOs();
		List<Person> persons = new ArrayList<Person>();
		log.info("personVOs "+personVOs);
		
		if(personVOs != null){
			for (PersonVO p : personVOs) {
				Person person = new Person();
				person.setId(p.getId());
				person.setName(p.getName());
				person.setAddress(p.getAddress());
				person.setAge(p.getAge());
				person.setPolicy(vo);
				persons.add(person);
			}
			vo.setPersonList(persons);
		}
		
		boolean returnStatus =	policyBean.updatePolicy(vo);		
		if(returnStatus){
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePolicy(@PathVariable int id){
		log.info("Id: "+id);
		boolean status = policyBean.deletePolicy(id);
		if(status){
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}
