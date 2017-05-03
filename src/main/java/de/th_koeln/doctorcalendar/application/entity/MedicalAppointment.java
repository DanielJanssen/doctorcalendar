package de.th_koeln.doctorcalendar.application.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import de.th_koeln.doctorcalendar.application.entity.enums.MedicalAppointmentState;
import de.th_koeln.doctorcalendar.application.entity.uuid.UuidGenerator;

@Entity
public class MedicalAppointment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 36, nullable = false, unique = true)
	@NotNull
	private String id = UuidGenerator.buildUuidString();

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, unique = false)
	@NotNull
	private Date date;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false, unique = false)
	@NotNull
	private Date timeFrom;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false, unique = false)
	@NotNull
	private Date timeTo;

	@Column(length = 63, nullable = false, unique = false)
	@NotNull
	private String description;

	@Column(length = 255, nullable = true, unique = false)
	private String comment;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false, unique = false)
	MedicalAppointmentState state;

	@ManyToOne
	private User user;

	@ManyToOne
	private MedicalOffice medicalOffice = new MedicalOffice();

	public MedicalAppointment() {
		super();
	}

	public MedicalAppointment(Date aDate, Date aTimeFrom, Date aTimeTo, String aDescription, String aComment, User aUser, MedicalOffice aMedicalOffice,
			MedicalAppointmentState aState) {
		super();
		date = aDate;
		timeFrom = aTimeFrom;
		timeTo = aTimeTo;
		description = aDescription;
		comment = aComment;
		user = aUser;
		medicalOffice = aMedicalOffice;
		state = aState;
	}

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

	public MedicalAppointmentState getState() {
		return state;
	}

	public void setState(MedicalAppointmentState aState) {
		state = aState;
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

	public String getFormattedTime() {
		return timeFrom + " - " + timeTo;
	}
}