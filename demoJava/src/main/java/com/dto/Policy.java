package com.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="policy")
public class Policy{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="term")
	private int term;
	
	@Column(name="premium")
	private float premium;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "policy", cascade =  CascadeType.ALL)
	private List<Person> personList;
	
	

	/**
	 * @return the personList
	 */
	public List<Person> getPersonList() {
		return personList;
	}

	/**
	 * @param personList the personList to set
	 */
	public void setPersonList(List<Person> personList) {
		this.personList = personList;
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
	 * @return the person
	 */
	/*public Person getPerson() {
		return person;
	}

	*//**
	 * @param person the person to set
	 *//*
	public void setPerson(Person person) {
		this.person = person;
	} */
	
}
