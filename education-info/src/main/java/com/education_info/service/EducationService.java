package com.education_info.service;

import com.education_info.dto.Educationdto;
import com.education_info.entity.EducationEntity;
import com.education_info.mapstruct.EntityToDtoReverse;
import com.education_info.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository repository;
    private final ModelMapper modelMapper;

    public Long saveEducation(Educationdto educationdto){
        //EducationEntity educationEntity=modelMapper.map(educationdto,EducationEntity.class);
        //EducationEntity educationEntity=new EducationEntity();
        EducationEntity educationEntity=EducationEntity.builder().degree(educationdto.getDegree()).studentId(educationdto.getStudentId())
                .university(educationdto.getUniversity()).build();

          //EducationEntity educationEntity= reverse.toEntity(educationdto);
//        educationEntity.setStudentId(educationdto.getStudentId());
//        educationEntity.setDegree(educationdto.getDegree());
//        educationEntity.setUniversity(educationdto.getUniversity());

        return repository.save(educationEntity).getId();

    }

    public List<Educationdto> getEducation(Long studentId){
        List<EducationEntity> educationEntity =repository.findByStudentId(studentId);
        List<Educationdto> educationdto=new ArrayList<>();
        educationEntity.forEach((entity)->{
            educationdto.add(modelMapper.map(entity,Educationdto.class));
        });
        return  educationdto;
    }
}
