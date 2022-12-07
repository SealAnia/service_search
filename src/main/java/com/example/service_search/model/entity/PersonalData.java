package com.example.service_search.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "personal_data")
public class PersonalData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer personalDataId;
	@Column(name = "first_name", columnDefinition = "TEXT", nullable = false)
	private String firstName;
	@Column(name = "second_name", columnDefinition = "TEXT", nullable = false)
	private String secondName;
	@Column(name = "card_number", columnDefinition = "BIGINT", nullable = false, unique = true)
	private Long cardNumber;
	@Column(name = "phone_number", columnDefinition = "BIGINT", nullable = false, unique = true)
	private Long phoneNumber;
	@Column(name = "watsapp_number", columnDefinition = "BIGINT", unique = true)
	private Long watsappNumber;
	@Column(name = "e_mail", columnDefinition = "TEXT", nullable = false, unique = true)
	private String email;
	@Column(name = "address", columnDefinition = "TEXT", unique = true)
	private String address;
	@Column(name = "postal_code", columnDefinition = "NUMBER")
	private Integer postalCode;
	@OneToOne(mappedBy = "personalData", orphanRemoval = true)
	private User user;

	public Integer getPersonalDataId() {
		return personalDataId;
	}

	public void setPersonalDataId(Integer personalDataId) {
		this.personalDataId = personalDataId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getWatsappNumber() {
		return watsappNumber;
	}

	public void setWatsappNumber(Long watsappNumber) {
		this.watsappNumber = watsappNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
