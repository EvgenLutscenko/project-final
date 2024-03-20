package com.javarush.jira.bugtracking.task.to;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class TaskToTags {
    Set<@Size(min = 2, max = 32) String> tags;
}
