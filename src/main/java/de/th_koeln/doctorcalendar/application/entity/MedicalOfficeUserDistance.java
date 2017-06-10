package de.th_koeln.doctorcalendar.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import de.th_koeln.doctorcalendar.application.entity.uuid.UuidGenerator;

@Entity
public class MedicalOfficeUserDistance {

	@Id
	@Column(length = 36, nullable = false, unique = true)
	@NotNull
	private String id = UuidGenerator.buildUuidString();

	@ManyToOne
	private MedicalOffice medicalOffice;

	@ManyToOne
	private User user;

	@Column
	private Integer distance;

	public MedicalOfficeUserDistance() {
		super();
	}

	public MedicalOfficeUserDistance(MedicalOffice aMedicalOffice, User aUser, Integer aDistance) {
		super();
		medicalOffice = aMedicalOffice;
		user = aUser;
		distance = aDistance;
	}

	public String getId() {
		return id;
	}

	public void setId(String aId) {
		id = aId;
	}

	public MedicalOffice getMedicalOffice() {
		return medicalOffice;
	}

	public void setMedicalOffice(MedicalOffice aMedicalOffice) {
		medicalOffice = aMedicalOffice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User aUser) {
		user = aUser;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer aDistance) {
		distance = aDistance;
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
		MedicalOfficeUserDistance other = (MedicalOfficeUserDistance) obj;
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
