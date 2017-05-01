package de.th_koeln.doctorcalendar.application.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import de.th_koeln.doctorcalendar.application.entity.uuid.UuidGenerator;

@Entity
// TODO rt57, 29.04.2017:  UniqueConstraint auf LoginName
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 36, nullable = false, unique = true)
	@NotNull
	private String id = UuidGenerator.buildUuidString();

	@Column(length = 12, nullable = false, unique = true)
	@NotNull
	private String loginName;

	@Column(length = 63, nullable = false, unique = false)
	@NotNull
	private String firstName;

	@Column(length = 63, nullable = false, unique = false)
	@NotNull
	private String surName;

	@Column(length = 63, nullable = false, unique = true)
	@NotNull
	private String email;

	@Column(length = 63, nullable = false, unique = false)
	@NotNull
	private String password;

	@OneToOne
	private PhoneNumber phoneNumber;

	@OneToOne
	private Address address;

	@ManyToOne
	private MedicalOffice workingMedicalOffice;

	@OneToMany(mappedBy = "user")
	private List<MedicalAppointment> medicalAppointments = new ArrayList<>();

	public String getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String aLoginName) {
		loginName = aLoginName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String aFirstName) {
		firstName = aFirstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String aSurName) {
		surName = aSurName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String aEmail) {
		email = aEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String aPassword) {
		password = aPassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber aPhoneNumber) {
		phoneNumber = aPhoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address aAddress) {
		address = aAddress;
	}

	public MedicalOffice getWorkingMedicalOffice() {
		return workingMedicalOffice;
	}

	public void setWorkingMedicalOffice(MedicalOffice aWorkingMedicalOffice) {
		workingMedicalOffice = aWorkingMedicalOffice;
	}

	public List<MedicalAppointment> getMedicalAppointments() {
		return medicalAppointments;
	}

	public void setMedicalAppointments(List<MedicalAppointment> aMedicalAppointments) {
		medicalAppointments = aMedicalAppointments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}