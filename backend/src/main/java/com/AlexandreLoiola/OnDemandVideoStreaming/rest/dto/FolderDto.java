package com.AlexandreLoiola.OnDemandVideoStreaming.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolderDto {
    private String key;
    private long size;
    private Date lastModified;
    private Map<String, String> metadata;
    private Map<String, String> tags;
    private String owner;
    private String permissions;
}
