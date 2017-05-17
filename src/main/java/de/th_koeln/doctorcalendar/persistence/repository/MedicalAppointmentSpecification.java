package de.th_koeln.doctorcalendar.persistence.repository;

import java.util.ArrayList;
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
import de.th_koeln.doctorcalendar.gui.user.findmedicalappointment.FindMedicalAppointmentSearchParameter;

public class MedicalAppointmentSpecification implements Specification<MedicalAppointment> {

	private final FindMedicalAppointmentSearchParameter searchParameter;

	public MedicalAppointmentSpecification(FindMedicalAppointmentSearchParameter aSearchParameter) {
		super();
		searchParameter = aSearchParameter;
	}

	//http://stackoverflow.com/questions/29348742/spring-data-jpa-creating-specification-query-fetch-joins
	//	http://stackoverflow.com/questions/27626825/spring-data-jpa-query-by-example
	@Override
	public Predicate toPredicate(Root<MedicalAppointment> aRoot, CriteriaQuery<?> aQuery, CriteriaBuilder aConditions) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		Join<MedicalAppointment, MedicalOffice> join = aRoot.join(MedicalAppointment_.medicalOffice);
		if (searchParameter.getMedicalOfficeName() != null && searchParameter.getMedicalOfficeName() != "") {
			predicates.add(aConditions.like(aConditions.lower(join.get(MedicalOffice_.name)), "%" + searchParameter.getMedicalOfficeName() + "%"));
		}

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
		if (searchParameter.getMedicalAppointmentTimeFrom() != null) {

		}
		if (searchParameter.getMedicalAppointmentTimeTo() != null) {

		}
		if (searchParameter.getSpeciality() != null) {

		}
		if (searchParameter.getMaximumDistanceInKm() != null && searchParameter.getMaximumDistanceInKm() > 0) {

		}
		// TODO rt57, 17.05.2017: nur FREE termine
		return andTogether(predicates, aConditions);
	}

	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
