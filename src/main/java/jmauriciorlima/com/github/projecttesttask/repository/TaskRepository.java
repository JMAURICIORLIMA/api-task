package jmauriciorlima.com.github.projecttesttask.repository;

import jmauriciorlima.com.github.projecttesttask.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitleAndDescription(String title, String description);

}
