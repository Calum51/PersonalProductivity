package fr.calum51.PersonalProductivity.repository;

import fr.calum51.PersonalProductivity.model.Task;
import fr.calum51.PersonalProductivity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByUser(User user);
}
