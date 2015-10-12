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
import com.vo.PolicyVO;

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

		List<PolicyVO> policies = policyBean.getPolicy();

		return new ResponseEntity<List<PolicyVO>>(policies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<PolicyVO> getOnlyPolicy(@PathVariable int id) {

		PolicyVO policy = policyBean.getPolicy(id);
		if(policy == null){
			return new ResponseEntity<PolicyVO>(policy, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PolicyVO>(policy, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> addPolicy(UriComponentsBuilder b, @RequestBody PolicyVO policyVO){
		log.info("Str "+policyVO);
		
		int returnInt = 0 ;
		
		returnInt =	policyBean.CreatePolicy(policyVO);
		HttpHeaders headers = new HttpHeaders();
		UriComponents uriComponents = b.path("/policy/{id}/ph/").buildAndExpand(returnInt);
		headers.setLocation(uriComponents.toUri());
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> updatePolicy(@RequestBody PolicyVO policyVO){
		
		
		boolean returnStatus =	policyBean.updatePolicy(policyVO);		
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
