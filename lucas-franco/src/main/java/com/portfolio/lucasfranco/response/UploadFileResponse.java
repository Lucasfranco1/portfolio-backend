package com.portfolio.lucasfranco.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponse {
    private String id;
    private String fileType;
    private String message;
    private Boolean uploadStatus;
    private String downloadUri;
}
