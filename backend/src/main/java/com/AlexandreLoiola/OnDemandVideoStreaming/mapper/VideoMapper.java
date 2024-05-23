package com.AlexandreLoiola.OnDemandVideoStreaming.mapper;

import com.AlexandreLoiola.OnDemandVideoStreaming.model.VideoModel;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.dto.VideoDto;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.form.VideoForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class VideoMapper {
    public static final VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);

    public abstract VideoDto modelToDto(VideoModel model);
    public abstract VideoModel formToModel(VideoForm form);
    public abstract Set<VideoDto> setModelToSetDto(Set<VideoModel> models);
}
