package de.th_koeln.doctorcalendar.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment_;
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
	public Predicate toPredicate(Root<MedicalAppointment> aRoot, CriteriaQuery<?> aQuery, CriteriaBuilder aCb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (searchParameter.getMedicalOfficeName() != null) {
			predicates.add(aCb.equal(aCb.lower(aRoot.<String> get(MedicalAppointment_.description)), searchParameter.getMedicalOfficeName()));
		}
		return andTogether(predicates, aCb);
	}

	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
