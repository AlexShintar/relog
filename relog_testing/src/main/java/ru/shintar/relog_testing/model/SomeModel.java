package ru.shintar.relog_testing.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SomeModel {
    private Long id;
    private String description;
}
