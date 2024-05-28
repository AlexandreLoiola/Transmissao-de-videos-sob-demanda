package com.AlexandreLoiola.OnDemandVideoStreaming.mapper;

import com.AlexandreLoiola.OnDemandVideoStreaming.model.VideoModel;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.dto.VideoDto;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.form.VideoForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class VideoMapper {
    public static final VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);

    public abstract VideoDto modelToDto(VideoModel model);
    public abstract VideoModel formToModel(VideoForm form);
    public abstract Set<VideoDto> setModelToSetDto(Set<VideoModel> models);

    ObjectMapper objectMapper = new ObjectMapper();

    public VideoForm stringToForm(String videoForm) {
        try {
            return objectMapper.readValue(videoForm, VideoForm.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to VideoForm", e);
        }
    }
}