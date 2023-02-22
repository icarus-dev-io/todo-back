package org.icarus.tasks.core.domain;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Accessors(fluent = true)
public class Task {
    private final TaskIdentity taskIdentity;
    private final String content;
    private State state;
    private final LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    private Task(final TaskIdentity taskIdentity, final String content, final State state) {
        this.taskIdentity = taskIdentity;
        this.content = content;
        this.state = state;
        this.creationDate = LocalDateTime.now();
    }

    public static Task create(final String content) {
        return new Task(TaskIdentity.generate(), content, State.TO_DO);
    }

    public void done() {
        if (this.state == State.DONE || this.state == State.DELETED)
            throw new RuntimeException("Changing the state of the task is not allowed.");
        this.state = State.DONE;
        this.modificationDate = LocalDateTime.now();
    }

    public void delete() {
        if (this.state == State.DELETED)
            throw new RuntimeException("Changing the state of the task is not allowed.");
        this.state = State.DELETED;
        this.modificationDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Task task = (Task) o;
        return Objects.equals(this.taskIdentity, task.taskIdentity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.taskIdentity);
    }
}
