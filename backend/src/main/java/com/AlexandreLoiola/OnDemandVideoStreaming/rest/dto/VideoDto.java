package com.AlexandreLoiola.OnDemandVideoStreaming.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {

    private String title;

    private String description;

    private String folder;

    private URL videoUrl;

    private URL thumbnailUrl;
}
