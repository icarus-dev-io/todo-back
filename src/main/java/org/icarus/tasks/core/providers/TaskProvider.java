package org.icarus.tasks.core.providers;

import org.icarus.tasks.core.domain.Task;
import org.icarus.tasks.core.domain.TaskIdentity;

import java.util.List;
import java.util.Optional;

public interface TaskProvider {
    Optional<Task> findById(final TaskIdentity taskIdentity);
    List<Task> findAll();
    void save(final Task task);
}
