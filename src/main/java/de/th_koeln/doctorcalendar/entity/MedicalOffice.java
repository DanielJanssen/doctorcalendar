package de.th_koeln.doctorcalendar.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import de.th_koeln.doctorcalendar.entity.enums.Speciality;

@Entity
public class MedicalOffice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic
	@NotNull
	private String id;

	@Enumerated(EnumType.STRING)
	@Column
	@NotNull
	private Speciality speciality;

	@Temporal(TemporalType.TIME)
	@Column
	@NotNull
	private Date openFrom;

	@Temporal(TemporalType.TIME)
	@Column
	@NotNull
	private Date openTo;

	@Basic
	private String website;

	@Basic
	private String email;

	@OneToOne
	private PhoneNumber phoneNumber;

	@OneToOne
	private Address address;

	@OneToMany(mappedBy = "workingMedicalOffice")
	private List<User> employees = new ArrayList<>();

	@OneToMany
	private List<Caregiving> caregivings = new ArrayList<>();

	@OneToMany(mappedBy = "medicalOffice")
	private List<MedicalAppointment> medicalAppointments = new ArrayList<>();

	public String getId() {
		return id;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality aSpeciality) {
		speciality = aSpeciality;
	}

	public Date getOpenFrom() {
		return openFrom;
	}

	public void setOpenFrom(Date aOpenFrom) {
		openFrom = aOpenFrom;
	}

	public Date getOpenTo() {
		return openTo;
	}

	public void setOpenTo(Date aOpenTo) {
		openTo = aOpenTo;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String aWebsite) {
		website = aWebsite;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String aEmail) {
		email = aEmail;
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

	public List<User> getEmployees() {
		return employees;
	}

	public void setEmployees(List<User> aEmployees) {
		employees = aEmployees;
	}

	public List<Caregiving> getCaregivings() {
		return caregivings;
	}

	public void setCaregivings(List<Caregiving> aCaregivings) {
		caregivings = aCaregivings;
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
		MedicalOffice other = (MedicalOffice) obj;
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