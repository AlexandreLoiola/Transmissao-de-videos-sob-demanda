package com.AlexandreLoiola.OnDemandVideoStreaming.rest.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoForm {

    private String title;

    private String description;

    private String url;
}
