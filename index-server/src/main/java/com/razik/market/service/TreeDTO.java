package com.razik.market.service;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeDTO {
    @NotEmpty
    private Long parent;
    @NotEmpty
    private String name;
    private String url;
}
