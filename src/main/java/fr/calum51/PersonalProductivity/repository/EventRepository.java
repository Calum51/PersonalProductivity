package fr.calum51.PersonalProductivity.repository;

import fr.calum51.PersonalProductivity.model.Event;
import fr.calum51.PersonalProductivity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUser(User user);
}
