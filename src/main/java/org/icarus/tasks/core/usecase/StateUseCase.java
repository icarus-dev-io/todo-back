package org.icarus.tasks.core.usecase;

import lombok.RequiredArgsConstructor;
import org.icarus.tasks.core.domain.Task;
import org.icarus.tasks.core.domain.TaskIdentity;
import org.icarus.tasks.core.providers.TaskProvider;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class StateUseCase {

    private final TaskProvider taskProvider;

    public Task done(final TaskIdentity taskIdentity) {
        return this.taskProvider.findById(taskIdentity)
                .map(this.doneTask())
                .orElseThrow(this.throwNotFoundException(taskIdentity));
    }

    public Task delete(final TaskIdentity taskIdentity) {
        return this.taskProvider.findById(taskIdentity)
                .map(this.deleteTask())
                .orElseThrow(this.throwNotFoundException(taskIdentity));
    }

    private Function<Task, Task> doneTask() {
        return task -> {
            task.done();
            this.taskProvider.save(task);
            return task;
        };
    }

    private Function<Task, Task> deleteTask() {
        return task -> {
            task.delete();
            this.taskProvider.save(task);
            return task;
        };
    }

    private Supplier<RuntimeException> throwNotFoundException(final TaskIdentity taskIdentity) {
        return () ->  new RuntimeException(String.format("Task not found for the id '%s'.", taskIdentity.id()));
    }

}
