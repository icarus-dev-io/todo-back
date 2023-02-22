package org.icarus.tasks.repositories;

import org.icarus.tasks.core.domain.Task;
import org.icarus.tasks.core.domain.TaskIdentity;
import org.icarus.tasks.core.providers.TaskProvider;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryInMemory implements TaskProvider {
    private static final List<Task> TASKS = new ArrayList<>();

    @Override
    public Optional<Task> findById(final TaskIdentity taskIdentity) {
        return TASKS.stream()
                .filter(task -> task.taskIdentity().equals(taskIdentity))
                .findFirst();
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(TASKS);
    }

    @Override
    public void save(final Task task) {
        TASKS.remove(task);
        TASKS.add(task);
    }
}
