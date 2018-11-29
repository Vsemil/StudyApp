package ru.firstline.studyapp.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.firstline.studyapp.model.PatientEntity;
import ru.firstline.studyapp.model.dto.Patient;

@Component
public class PatientMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(PatientEntity.class, Patient.class)
                .field("id", "id")
                .field("name", "name")
                .field("sex", "sex")
                .field("dayOfBirth", "dayOfBirth")
                .field("files", "files")
                .byDefault()
                .register();
    }
}
