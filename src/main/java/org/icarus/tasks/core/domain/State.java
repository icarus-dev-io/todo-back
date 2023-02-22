package org.icarus.tasks.core.domain;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public enum State {
    TO_DO("TO DO"), DONE("DONE"), DELETED("DELETED");

    private final String value;

    State(final String value) {
        this.value = value;
    }
}
