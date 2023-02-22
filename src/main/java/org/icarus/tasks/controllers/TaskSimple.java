package org.icarus.tasks.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskSimple {
    private String id;
    private String content;
    private String state;
    private String creationDate;
    private String modificationDate;
}
