package org.icarus.tasks.core.usecase;

import lombok.RequiredArgsConstructor;
import org.icarus.tasks.core.domain.Task;
import org.icarus.tasks.core.providers.TaskProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderUseCase {

    private final TaskProvider taskProvider;

    public List<Task> findAll() {
        return this.taskProvider.findAll();
    }

}
