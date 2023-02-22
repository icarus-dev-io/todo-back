package org.icarus.tasks.core.domain;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
public class TaskIdentity {
    private final String id;

    private TaskIdentity(final String id) {
        this.id = id;
    }

    public static TaskIdentity generate() {
        return new TaskIdentity(UUID.randomUUID().toString().split("-")[0]);
    }

    public static TaskIdentity from(final String id) {
        return new TaskIdentity(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TaskIdentity that = (TaskIdentity) o;
        return Objects.equals(this.id, that.id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
