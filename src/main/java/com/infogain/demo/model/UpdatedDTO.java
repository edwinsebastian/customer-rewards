package com.infogain.demo.model;

import com.infogain.demo.enums.ResourceStateEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
public class UpdatedDTO {
    private UUID id;
    private ResourceStateEnum state;

    public UpdatedDTO(UUID id, ResourceStateEnum state) {
        this.id = id;
        this.state = state;
    }
}
