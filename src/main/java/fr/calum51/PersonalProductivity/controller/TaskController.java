package fr.calum51.PersonalProductivity.controller;

import fr.calum51.PersonalProductivity.model.Task;
import fr.calum51.PersonalProductivity.model.User;
import fr.calum51.PersonalProductivity.model.enums.TaskStatus;
import fr.calum51.PersonalProductivity.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(taskService.getTasks(user));
    }

    @PostMapping
    public ResponseEntity<Void> createTask(
            @RequestBody Map<String, String> request,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        taskService.createTask(user,
                request.get("title"),
                request.get("description"),
                LocalDateTime.parse(request.get("deadline")),
                TaskStatus.valueOf(request.get("taskStatus"))
                );
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(
            @PathVariable Long id,
            @RequestBody Map<String, String> request,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        List<Task> tasks = taskService.getTasks(user);

        boolean owned = tasks.stream().anyMatch(t -> t.getId().equals(id));
        if (!owned) return ResponseEntity.status(403).build();

        taskService.updateTask(id,
                request.get("title"),
                request.get("description"),
                LocalDateTime.parse(request.get("deadline")),
                TaskStatus.valueOf(request.get("taskStatus"))
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        List<Task> tasks = taskService.getTasks(user);

        boolean owned = tasks.stream().anyMatch(t -> t.getId().equals(id));
        if (!owned) return ResponseEntity.status(403).build();

        taskService.deleteTask(id);

        return ResponseEntity.ok().build();
    }
}
