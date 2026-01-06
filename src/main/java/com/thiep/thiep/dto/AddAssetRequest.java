package com.thiep.thiep.dto;

import com.thiep.thiep.model.AssetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddAssetRequest {
    @NotNull
    private AssetType type; // IMAGE/MUSIC

    @NotBlank
    private String fieldKey; // avatar/gallery/music...

    @NotBlank
    private String url; // link MinIO

    private Integer sortIndex = 0;
}
