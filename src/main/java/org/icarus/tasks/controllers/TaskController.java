package org.icarus.tasks.controllers;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.icarus.tasks.core.domain.Task;
import org.icarus.tasks.core.domain.TaskIdentity;
import org.icarus.tasks.core.usecase.ReaderUseCase;
import org.icarus.tasks.core.usecase.StateUseCase;
import org.icarus.tasks.core.usecase.WriterUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final ReaderUseCase readerUseCase;
    private final WriterUseCase writerUseCase;
    private final StateUseCase stateUseCase;
    private final DateUtils dateUtils;

    @GetMapping(produces = "application/json")
    public List<TaskSimple> findAll() {
        return this.readerUseCase.findAll().stream()
                .map(this.mapToSimple())
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public TaskSimple create(@RequestBody final TaskIn taskIn) {
        final Task task = Task.create(taskIn.getContent());
        this.writerUseCase.store(task);
        return this.mapToSimple().apply(task);
    }

    @PatchMapping(value = "/{id}/done", produces = "application/json")
    public TaskSimple done(@PathVariable(name = "id") final String id) {
        return this.mapToSimple().apply(this.stateUseCase.done(TaskIdentity.from(id)));
    }

    @PatchMapping(value = "/{id}/delete", produces = "application/json")
    public TaskSimple delete(@PathVariable(name = "id") final String id) {
        return this.mapToSimple().apply(this.stateUseCase.delete(TaskIdentity.from(id)));
    }

    private Function<Task, TaskSimple> mapToSimple() {
        return task -> {
          final TaskSimple taskSimple = new TaskSimple();
          taskSimple.setId(task.taskIdentity().id());
          taskSimple.setContent(task.content());
          taskSimple.setState(task.state().value());
          taskSimple.setCreationDate(this.dateUtils.formatToString(task.creationDate()));
          if (task.modificationDate() != null)
              taskSimple.setModificationDate(this.dateUtils.formatToString(task.modificationDate()));
          return taskSimple;
        };
    }
}
