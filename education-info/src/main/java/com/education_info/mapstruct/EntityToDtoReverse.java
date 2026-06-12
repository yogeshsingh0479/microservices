package com.education_info.mapstruct;

import com.education_info.dto.Educationdto;
import com.education_info.entity.EducationEntity;


//@Mapper(componentModel = "spring")
public interface EntityToDtoReverse {

        EducationEntity toEntity(Educationdto dto);

        Educationdto toDto(EducationEntity entity);
}
