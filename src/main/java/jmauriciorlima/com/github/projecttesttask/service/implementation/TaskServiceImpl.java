package jmauriciorlima.com.github.projecttesttask.service.implementation;

import jmauriciorlima.com.github.projecttesttask.domain.Task;
import jmauriciorlima.com.github.projecttesttask.exception.*;
import jmauriciorlima.com.github.projecttesttask.repository.TaskRepository;
import jmauriciorlima.com.github.projecttesttask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Task not found with id" + id));
    }

    @Override
    public Task createTask(Task task) {
        if (!validateTask(task)) {
            throw new InvalidDataException("Task title cannot be empty");
        }
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task updateTask) {
        Task existingTask = taskRepository.findById(id).orElseThrow(()
                -> new UpdateResourceNotFoundException("Cannot update. Task not found with id: " + id));

        if (existingTask != null) {
            existingTask.setTitle(updateTask.getTitle());
            existingTask.setDescription(updateTask.getDescription());
            return taskRepository.save(existingTask);
        }
        return null;
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with ID " + id);
        }
        taskRepository.deleteById(id);
    }

    private boolean validateTask(Task task) {
        if (task.getTitle() == null || task.getDescription() == null
                || task.getTitle().isEmpty() || task.getDescription().isEmpty()) {
            throw new BadRequestException("Task cannot be completely empty");
        }
        Optional<Task> exixtingTask = taskRepository
                .findByTitleAndDescription(task.getTitle(), task.getDescription());

        if (exixtingTask.isPresent()) {
            throw new DuplicateTaskException("A task already exists with the same title and description");
        }

        return true;
    }

}
