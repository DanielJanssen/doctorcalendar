package de.th_koeln.doctorcalendar.dataaccess.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.dataaccess.entity.Caregiving;

public interface CaregivingRepository extends CrudRepository<Caregiving, String> {

}
