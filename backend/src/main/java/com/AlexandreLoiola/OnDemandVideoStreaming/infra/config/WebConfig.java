package com.AlexandreLoiola.OnDemandVideoStreaming.infra.config;

import com.AlexandreLoiola.OnDemandVideoStreaming.mapper.VideoMapper;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.form.VideoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class, VideoForm.class, videoMapper::stringToForm);
    }
}