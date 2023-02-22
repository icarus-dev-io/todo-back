package org.icarus.tasks.core.usecase;

import lombok.RequiredArgsConstructor;
import org.icarus.tasks.core.domain.Task;
import org.icarus.tasks.core.providers.TaskProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriterUseCase {

    private final TaskProvider taskProvider;

    public void store(final Task task) {
        this.taskProvider.save(task);
    }

}
