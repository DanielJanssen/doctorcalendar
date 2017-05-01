package de.th_koeln.doctorcalendar.application.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import de.th_koeln.doctorcalendar.application.entity.uuid.UuidGenerator;

@Entity
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic
	@NotNull
	private String id = UuidGenerator.buildUuidString();

	@Basic
	@NotNull
	private String street;

	@Basic
	@NotNull
	private Integer zipcode;

	@Basic
	@NotNull
	private String city;

	public String getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String aStreet) {
		street = aStreet;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer aZipcode) {
		zipcode = aZipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String aCity) {
		city = aCity;
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
		Address other = (Address) obj;
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