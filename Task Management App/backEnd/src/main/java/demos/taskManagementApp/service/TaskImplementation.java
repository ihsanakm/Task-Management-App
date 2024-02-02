package demos.taskManagementApp.service;

import demos.taskManagementApp.entity.TaskEntity;
import demos.taskManagementApp.model.Task;
import demos.taskManagementApp.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskImplementation implements TaskService {

    private TaskRepository taskRepository;

    public TaskImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Task saveTask(Task task) {
        TaskEntity taskEntity = new TaskEntity();
        BeanUtils.copyProperties(task,taskEntity);

        taskRepository.save(taskEntity);
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        List<TaskEntity> taskEntities= taskRepository.findAll();
        List<Task> tasks = taskEntities.stream().map(taskEntity -> new Task(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.isCompleted(),
                taskEntity.getAssignedTo(),
                taskEntity.getDueDate()
        )).collect(Collectors.toList());
        return tasks;
    }

    @Override
    public Task getTaskById(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id).get();
        Task task = new Task();
        BeanUtils.copyProperties(taskEntity, task);
        return task;
    }

    @Override
    public boolean deleteTask(Long id) {
        TaskEntity task = taskRepository.findById(id).get();
        taskRepository.delete(task);
        return true;
    }

    @Override
    public Task updateTask(Long id, Task task) {
        TaskEntity taskEntity = taskRepository.findById(id).get();
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setDueDate(task.getDueDate());
        taskEntity.setAssignedTo(task.getAssignedTo());
        taskEntity.setCompleted(task.isCompleted());

        taskRepository.save(taskEntity);

        return task;
    }
}
