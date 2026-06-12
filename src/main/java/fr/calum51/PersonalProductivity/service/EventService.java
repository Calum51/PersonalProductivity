package fr.calum51.PersonalProductivity.service;

import fr.calum51.PersonalProductivity.model.Event;
import fr.calum51.PersonalProductivity.model.Task;
import fr.calum51.PersonalProductivity.model.User;
import fr.calum51.PersonalProductivity.model.enums.TaskStatus;
import fr.calum51.PersonalProductivity.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;


    public List<Event> getEvent(User user){
        return eventRepository.findByUser(user);
    }

    public void createEvent(User user, String title, String description, LocalDateTime start, LocalDateTime end){
        Event event = new Event();

        event.setUser(user);
        event.setTitle(title);
        event.setDescription(description);
        event.setStart(start);
        event.setEnd(end);

        eventRepository.save(event);
    }

    public void updateEvent(Long id, String title,String description, LocalDateTime start, LocalDateTime end){
        Optional<Event> eventOpt = eventRepository.findById(id);
        if (eventOpt.isEmpty()) return; // oulah, peut etre un throw TODO : Faire un throw
        Event event = eventOpt.get();

        if(title != null)
            event.setTitle(title);
        if(description != null)
            event.setDescription(description);
        if(start != null)
            event.setStart(start);
        if(end != null)
            event.setEnd(end);

        eventRepository.save(event);

    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }
}
