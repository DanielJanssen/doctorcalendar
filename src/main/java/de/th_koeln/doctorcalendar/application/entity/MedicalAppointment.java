package de.th_koeln.doctorcalendar.application.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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

	@Column(length = 63, nullable = true, unique = false)
	private String description;

	@Column(length = 255, nullable = true, unique = false)
	private String comment;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false, unique = false)
	private MedicalAppointmentState state;

	@ManyToOne
	private Caregiving careGiving;

	@ManyToOne
	private User user;

	@ManyToOne(cascade = CascadeType.DETACH)
	private MedicalOffice medicalOffice = new MedicalOffice();

	@Transient
	private Integer distance;

	public MedicalAppointment() {
		super();
	}

	public MedicalAppointment(Date aDate, Date aTimeFrom, Date aTimeTo, MedicalAppointmentState aState, MedicalOffice aMedicalOffice) {
		super();
		date = aDate;
		timeFrom = aTimeFrom;
		timeTo = aTimeTo;
		state = aState;
		medicalOffice = aMedicalOffice;
	}

	public MedicalAppointment(Date aDate, Date aTimeFrom, Date aTimeTo, String aDescription, String aComment, User aUser, MedicalOffice aMedicalOffice,
			MedicalAppointmentState aState, Caregiving aCareGiving) {
		this(aDate, aTimeFrom, aTimeTo, aState, aMedicalOffice);
		description = aDescription;
		comment = aComment;
		user = aUser;
		careGiving = aCareGiving;
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

	public Caregiving getCareGiving() {
		return careGiving;
	}

	public void setCareGiving(Caregiving aCareGiving) {
		careGiving = aCareGiving;
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
		SimpleDateFormat tempSimpleDateFormat = new SimpleDateFormat("HH:mm");
		return tempSimpleDateFormat.format(timeFrom) + " - " + tempSimpleDateFormat.format(timeTo);
	}
}