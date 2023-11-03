package jmauriciorlima.com.github.projecttesttask.service;

import jmauriciorlima.com.github.projecttesttask.domain.Task;

import javax.sound.midi.InvalidMidiDataException;
import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task createTask(Task task);

    Task updateTask(Long id, Task updateTask);

    void deleteTask(Long id);

}
