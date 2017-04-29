package de.th_koeln.doctorcalendar.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class MedicalAppointment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic
	@NotNull
	private String id;

	@Temporal(TemporalType.DATE)
	@Column
	@NotNull
	private Date date;

	@Temporal(TemporalType.TIME)
	@Column
	@NotNull
	private Date timeFrom;

	@Temporal(TemporalType.TIME)
	@Column
	@NotNull
	private Date timeTo;

	@Basic
	@NotNull
	private String description;

	@Basic
	private String comment;

	@ManyToOne
	private User user;

	@ManyToOne
	private MedicalOffice medicalOffice;

	public String getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date aDate) {
		date = aDate;
	}

	public Date getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Date aTimeFrom) {
		timeFrom = aTimeFrom;
	}

	public Date getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Date aTimeTo) {
		timeTo = aTimeTo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String aDescription) {
		description = aDescription;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String aComment) {
		comment = aComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User aUser) {
		user = aUser;
	}

	public MedicalOffice getMedicalOffice() {
		return medicalOffice;
	}

	public void setMedicalOffice(MedicalOffice aMedicalOffice) {
		medicalOffice = aMedicalOffice;
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
		MedicalAppointment other = (MedicalAppointment) obj;
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