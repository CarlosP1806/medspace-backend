package com.medspace.infrastructure.dto.clinic;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetPhotoAsPrimaryDTO {
    @NotNull
    private Long photoId;
}
