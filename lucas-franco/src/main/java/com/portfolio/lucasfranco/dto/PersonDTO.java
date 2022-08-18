package com.portfolio.lucasfranco.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;

@Data
public class PersonDTO {
    @NotNull(message = MESSAGE_NOT_NULL)
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    @Size(min = 2, max = 50, message = MESSAGE_SIZE_LIMIT)
    private String nameFirst;
    @NotNull(message = MESSAGE_NOT_NULL)
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    @Size(min = 2, max = 50, message = MESSAGE_SIZE_LIMIT)
    private String lastName;
    @NotNull(message = MESSAGE_NOT_NULL)
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    @Size(min = 2, max = 50, message = MESSAGE_SIZE_LIMIT)
    private String profession;
    @NotNull(message = MESSAGE_NOT_NULL)
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    @Size(min = 2, max = 250, message = MESSAGE_SIZE_LIMIT)
    private String about;
    private String image;
    private String bannerImage;
    private String gitHubUrl;
    private String linkedinUrl;
    private String country;
    private String province;
    private String curriculum;

    public PersonDTO(String nameFirst, String lastName, String profession, String about, String image, String bannerImage, String gitHubUrl, String linkedinUrl, String country, String province, String curriculum) {
        this.nameFirst = nameFirst;
        this.lastName = lastName;
        this.profession = profession;
        this.about = about;
        this.image = image;
        this.bannerImage = bannerImage;
        this.gitHubUrl = gitHubUrl;
        this.linkedinUrl = linkedinUrl;
        this.country = country;
        this.province = province;
        this.curriculum = curriculum;
    }
}
