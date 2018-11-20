package ru.firstline.studyapp.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.firstline.studyapp.model.StudyEntity;
import ru.firstline.studyapp.model.dto.Study;

@Component
public class StudyMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(StudyEntity.class, Study.class)
                .field("id", "id")
                .field("patient", "patient")
                .field("description", "description")
                .field("status", "status")
                .field("plannedStartTime", "plannedStartTime")
                .field("estimatedEndTime", "estimatedEndTime")
                .byDefault()
                .register();
    }
}
