package com.AlexandreLoiola.OnDemandVideoStreaming.rest.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDto {
    private static final long serialVersionUID=1L;
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
