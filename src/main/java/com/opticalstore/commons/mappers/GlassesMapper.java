package com.opticalstore.commons.mappers;


import com.opticalstore.models.Glasses;
import com.opticalstore.models.GlassesDto;
import com.opticalstore.models.GlassesMark;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GlassesMapper implements Mapper<Glasses, GlassesDto> {

    @Override
    public GlassesDto map(Glasses from) {
        return GlassesDto
                .builder()
                .glassesType(from.getGlassesType())
                .glassesNumber(from.getGlassesNumber())
                .glassesGender(from.getGlassesGender())
                .form(from.getForm())
                .price(from.getPrice())
                .polarization(from.getPolarization())
                .widthOfTheLens(from.getWidthOfTheLens())
                .glassesImage(from.getGlassesImage())
                .glassesMarks(from.getGlassesMarks())
                .build();
    }
    @Override
    public Glasses reverseMap(GlassesDto to) {
        return Glasses
                .builder()
                .glassesType(to.getGlassesType())
                .glassesNumber(to.getGlassesNumber())
                .glassesGender(to.getGlassesGender())
                .form(to.getForm())
                .price(to.getPrice())
                .polarization(to.getPolarization())
                .widthOfTheLens(to.getWidthOfTheLens())
                .glassesImage(to.getGlassesImage())
                .glassesMarks(to.getGlassesMarks())
                .build();
    }
//
//    private enum GlassesMarksToStringsList implements Function<GlassesMark, String> {
//        INSTANCE;
//
//        @Override
//        public String apply(GlassesMark glassesMark) {
//            return glassesMark.getTitle();
//        }
//    }


}
