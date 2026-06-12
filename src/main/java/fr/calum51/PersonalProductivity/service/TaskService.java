package fr.calum51.PersonalProductivity.service;

import fr.calum51.PersonalProductivity.model.Task;
import fr.calum51.PersonalProductivity.model.User;
import fr.calum51.PersonalProductivity.model.enums.TaskStatus;
import fr.calum51.PersonalProductivity.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getTasks(User user){
        return taskRepository.findByUser(user);
    }

    public void createTask(User user, String title, String description, LocalDateTime deadline, TaskStatus taskStatus){
        Task task = new Task();

        task.setUser(user);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadline);
        task.setStatut(taskStatus);

        taskRepository.save(task);
    }

    public void updateTask(Long id, String title,String description, LocalDateTime deadLine, TaskStatus taskStatus){
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isEmpty()) return; // oulah, peut etre un throw TODO : Faire un throw
        Task task = taskOpt.get();

        if(title != null)
         task.setTitle(title);
        if(description != null)
            task.setDescription(description);
        if(deadLine != null)
            task.setDeadline(deadLine);
        if(taskStatus != null)
          task.setStatut(taskStatus);

        taskRepository.save(task);

    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
