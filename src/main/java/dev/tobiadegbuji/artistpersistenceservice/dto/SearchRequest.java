package dev.tobiadegbuji.artistpersistenceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchRequest {

    @NotBlank
    private String query;

    @NotBlank
    private String type;

    @Min(0)
    @Max(20)
    private int limit;

    @Min(0)
    @Max(1000)
    private int offset;

    //Can only be audio
    private String include_external;
}
