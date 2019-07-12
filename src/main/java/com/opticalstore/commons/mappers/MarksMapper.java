package com.opticalstore.commons.mappers;


import com.opticalstore.models.GlassesMark;
import com.opticalstore.models.GlassesMarkDto;
import org.springframework.stereotype.Component;

@Component
public class MarksMapper implements Mapper<GlassesMark, GlassesMarkDto> {

    @Override
    public GlassesMarkDto map(GlassesMark from) {

        return GlassesMarkDto
                .builder()
                .mark(from.getMark())
                .build();
    }

    @Override
    public GlassesMark reverseMap(GlassesMarkDto to) {
        return GlassesMark
                .builder()
                .mark(to.getMark())
                .build();
    }


}
