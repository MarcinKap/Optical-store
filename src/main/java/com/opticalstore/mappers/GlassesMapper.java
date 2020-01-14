package com.opticalstore.mappers;


import com.opticalstore.models.Glasses;
import com.opticalstore.models.GlassesDto;
import org.springframework.stereotype.Component;

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
                .id(from.getId())
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
                .id(to.getId())
                .build();
    }
}
