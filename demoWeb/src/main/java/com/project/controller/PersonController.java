/**
 * 
 */
package com.project.controller;

import java.util.List;

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
import com.vo.PersonVO;

/**
 * @author 492086
 *
 */
@Controller
@RequestMapping("/policy/{id}/ph")
public class PersonController {

	private static final Logger log = LoggerFactory.getLogger(PersonController.class);
			
	@Autowired
	public PolicyBean policyBean;
	/**
	 * @param policyBean the policyBean to set
	 */
	public void setPolicyBean(PolicyBean policyBean) {
		this.policyBean = policyBean;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PersonVO>> getPolicyHolders(@PathVariable int id) {
		log.info("getPolicyHolders " + id);
		List<PersonVO> personVOs = policyBean.getPolicyHolders(id); 
		if(personVOs !=null){
			log.info("persons size " + personVOs.size());
			return new ResponseEntity<List<PersonVO>>(personVOs, HttpStatus.OK);
		}
		return new ResponseEntity<List<PersonVO>>(personVOs, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/{phId}", method = RequestMethod.GET)
	public ResponseEntity<PersonVO> getPolicyHolder(@PathVariable int id, @PathVariable int phId){
		
		PersonVO personVO = policyBean.getPolicyHolder(id, phId);
				
		if(personVO == null){
			return new ResponseEntity<PersonVO>(personVO, HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<PersonVO>(personVO, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> addPolicyHolder(UriComponentsBuilder b, @PathVariable int id, @RequestBody PersonVO personVO){
		HttpHeaders headers = new HttpHeaders();
		/* 
		Person personObj = new Person();
		log.info("person "+personVO);
		if(personVO !=null){
			personObj.setName(personVO.getName());
			Policy policy = new Policy();
			policy.setId(id);
			personObj.setPolicy(policy);
			personObj.setAddress(personVO.getAddress());
			personObj.setAge(personVO.getAge());			
		}*/
		personVO.setPolicyId(id);
		int phId = policyBean.addPolicyHolder(personVO);
		
		UriComponents uriComponents = b.path("/policy/{id}/ph/").buildAndExpand(phId);
		headers.setLocation(uriComponents.toUri());
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> updatePolicyHolder(@PathVariable int id,
			@RequestBody PersonVO personVO) {
		/*Person personObj = new Person();
		log.info("updatePolicyHolder--> person " + personVO);
		if (personVO != null) {
			personObj.setId(personVO.getId());
			personObj.setName(personVO.getName());
			Policy policy = new Policy();
			policy.setId(id);
			personObj.setPolicy(policy);
			personObj.setAddress(personVO.getAddress());
			personObj.setAge(personVO.getAge());
		}*/
		personVO.setPolicyId(id);
		boolean status = policyBean.updatePolicyHolder(personVO);
		
		if(status){
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value ="{phId}",method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePolicyHolder(@PathVariable int id, @PathVariable int phId){

		boolean status =policyBean.deletePolicyHolder(id, phId);
		log.info("status "+status);
		
		if(status){
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}
