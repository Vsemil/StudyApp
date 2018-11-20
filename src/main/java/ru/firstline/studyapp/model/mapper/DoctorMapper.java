package ru.firstline.studyapp.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.firstline.studyapp.model.DoctorEntity;
import ru.firstline.studyapp.model.dto.Doctor;

@Component
public class DoctorMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(DoctorEntity.class, Doctor.class)
                .field("id", "id")
                .field("name", "name")
                .byDefault()
                .register();
    }
}
