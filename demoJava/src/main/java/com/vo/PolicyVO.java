package com.vo;

import java.util.List;


public class PolicyVO {
	
	private int id;
	private String name;
	private int term;
	private float premium;
	private List<PersonVO> personVOs;
	
	public PolicyVO() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the term
	 */
	public int getTerm() {
		return term;
	}
	/**
	 * @param term the term to set
	 */
	public void setTerm(int term) {
		this.term = term;
	}
	/**
	 * @return the premium
	 */
	public float getPremium() {
		return premium;
	}
	/**
	 * @param premium the premium to set
	 */
	public void setPremium(float premium) {
		this.premium = premium;
	}

	/**
	 * @return the personVOs
	 */
	public List<PersonVO> getPersonVOs() {
		return personVOs;
	}

	/**
	 * @param personVOs the personVOs to set
	 */
	public void setPersonVOs(List<PersonVO> personVOs) {
		this.personVOs = personVOs;
	}
	
	
}
