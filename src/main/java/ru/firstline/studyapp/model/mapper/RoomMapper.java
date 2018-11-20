package ru.firstline.studyapp.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.firstline.studyapp.model.RoomEntity;
import ru.firstline.studyapp.model.dto.Room;

@Component
public class RoomMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(RoomEntity.class, Room.class)
                .field("id", "id")
                .field("name", "name")
                .byDefault()
                .register();
    }
}
