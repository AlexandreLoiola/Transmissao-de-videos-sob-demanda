package com.AlexandreLoiola.OnDemandVideoStreaming.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {

    private String title;

    private String description;

    private String url;
}
