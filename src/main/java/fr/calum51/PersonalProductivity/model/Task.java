package fr.calum51.PersonalProductivity.model;

import fr.calum51.PersonalProductivity.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;
    @Setter
    private String description;

    @Setter
    @Enumerated(EnumType.STRING)
    private TaskStatus statut;

    @Setter
    private LocalDateTime deadline;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}