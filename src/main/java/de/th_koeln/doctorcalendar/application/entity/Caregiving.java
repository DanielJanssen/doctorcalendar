package de.th_koeln.doctorcalendar.application.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import de.th_koeln.doctorcalendar.application.entity.uuid.UuidGenerator;

@Entity
public class Caregiving implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 36, nullable = false, unique = true)
	@NotNull
	private String id = UuidGenerator.buildUuidString();

	@Column(length = 63, nullable = false, unique = false)
	@NotNull
	private String name;

	@Basic
	@NotNull
	private Integer durcationInMinutes;

	public Caregiving() {
		super();
	}

	public Caregiving(String aName, Integer aDurcationInMinutes) {
		super();
		name = aName;
		durcationInMinutes = aDurcationInMinutes;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String aName) {
		name = aName;
	}

	public Integer getDurcationInMinutes() {
		return durcationInMinutes;
	}

	public void setDurcationInMinutes(Integer aDurcationInMinutes) {
		durcationInMinutes = aDurcationInMinutes;
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
		Caregiving other = (Caregiving) obj;
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