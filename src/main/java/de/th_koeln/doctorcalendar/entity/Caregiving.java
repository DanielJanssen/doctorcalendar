package de.th_koeln.doctorcalendar.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Caregiving implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic
	@NotNull
	private String id;

	@Basic
	@NotNull
	private String name;

	@Basic
	@NotNull
	private Integer durcationInMinutes;

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