package de.th_koeln.doctorcalendar.persistence.repository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment_;
import de.th_koeln.doctorcalendar.application.entity.MedicalOffice;
import de.th_koeln.doctorcalendar.application.entity.MedicalOffice_;
import de.th_koeln.doctorcalendar.application.entity.enums.MedicalAppointmentState;
import de.th_koeln.doctorcalendar.gui.user.findmedicalappointment.FindMedicalAppointmentSearchParameter;

public class MedicalAppointmentSpecification implements Specification<MedicalAppointment> {

	private final FindMedicalAppointmentSearchParameter searchParameter;

	public MedicalAppointmentSpecification(FindMedicalAppointmentSearchParameter aSearchParameter) {
		super();
		searchParameter = aSearchParameter;
	}

	@Override
	public Predicate toPredicate(Root<MedicalAppointment> aRoot, CriteriaQuery<?> aQuery, CriteriaBuilder aConditions) {
		List<Predicate> predicates = new ArrayList<>();
		Join<MedicalAppointment, MedicalOffice> join = aRoot.join(MedicalAppointment_.medicalOffice);
		addMedicalOfficeName(aConditions, predicates, join);
		addDateFromTo(aRoot, aConditions, predicates);
		addTimeFromTo(aRoot, aConditions, predicates);
		addSpeciality(aConditions, predicates, join);
		addMaximumDistance();
		addFreeFutureMedicalAppointments(aRoot, aConditions, predicates);

		return andTogether(predicates, aConditions);
	}

	private void addMedicalOfficeName(CriteriaBuilder aConditions, List<Predicate> predicates, Join<MedicalAppointment, MedicalOffice> join) {
		if (searchParameter.getMedicalOfficeName() != null && searchParameter.getMedicalOfficeName() != "") {
			predicates.add(aConditions.like(aConditions.lower(join.get(MedicalOffice_.name)), "%" + searchParameter.getMedicalOfficeName() + "%"));
		}
	}

	private void addDateFromTo(Root<MedicalAppointment> aRoot, CriteriaBuilder aConditions, List<Predicate> predicates) {
		if (searchParameter.getMedicalAppointmentDateFrom() != null || searchParameter.getMedicalAppointmentDateTo() != null) {
			if (searchParameter.getMedicalAppointmentDateFrom() == null && searchParameter.getMedicalAppointmentDateTo() != null) {
				predicates.add(aConditions.lessThanOrEqualTo(aRoot.get(MedicalAppointment_.date), searchParameter.getMedicalAppointmentDateTo()));
			} else if (searchParameter.getMedicalAppointmentDateFrom() != null && searchParameter.getMedicalAppointmentDateTo() == null) {
				predicates.add(aConditions.greaterThanOrEqualTo(aRoot.get(MedicalAppointment_.date), searchParameter.getMedicalAppointmentDateFrom()));
			} else {
				predicates.add(aConditions.between(aRoot.get(MedicalAppointment_.date), searchParameter.getMedicalAppointmentDateFrom(),
						searchParameter.getMedicalAppointmentDateTo()));
			}
		}
	}

	private void addTimeFromTo(Root<MedicalAppointment> aRoot, CriteriaBuilder aConditions, List<Predicate> predicates) {
		if (searchParameter.getMedicalAppointmentTimeFrom() != null || searchParameter.getMedicalAppointmentTimeTo() != null) {
			if (searchParameter.getMedicalAppointmentTimeFrom() == null && searchParameter.getMedicalAppointmentTimeTo() != null) {
				predicates.add(aConditions.lessThanOrEqualTo(aConditions.function("time", Time.class, aRoot.get(MedicalAppointment_.timeFrom)),
						searchParameter.getMedicalAppointmentTimeFrom()));
			} else if (searchParameter.getMedicalAppointmentTimeFrom() != null && searchParameter.getMedicalAppointmentTimeTo() == null) {
				predicates.add(aConditions.greaterThanOrEqualTo(aConditions.function("time", Time.class, aRoot.get(MedicalAppointment_.timeTo)),
						searchParameter.getMedicalAppointmentTimeTo()));
			} else {
				// TODO rt57, 17.05.2017: time funktioniert noch nicht
			}
		}
	}

	private void addSpeciality(CriteriaBuilder aConditions, List<Predicate> predicates, Join<MedicalAppointment, MedicalOffice> join) {
		if (searchParameter.getSpeciality() != null) {
			predicates.add(aConditions.equal(join.get(MedicalOffice_.speciality), searchParameter.getSpeciality()));
		}
	}

	private void addMaximumDistance() {
		if (searchParameter.getMaximumDistanceInKm() != null && searchParameter.getMaximumDistanceInKm() > 0) {
			// TODO rt57, 17.05.2017: GoogleAufruf oder Tabelle
		}
	}

	private void addFreeFutureMedicalAppointments(Root<MedicalAppointment> aRoot, CriteriaBuilder aConditions, List<Predicate> predicates) {
		predicates.add(aConditions.equal(aRoot.get(MedicalAppointment_.state), MedicalAppointmentState.FREE));
		predicates.add(aConditions.greaterThanOrEqualTo(aRoot.get(MedicalAppointment_.date), new Date()));

	}

	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
