package com.opticalstore.services;


import com.opticalstore.commons.mappers.MarksMapper;
import com.opticalstore.models.GlassesDto;
import com.opticalstore.models.GlassesMarkDto;
import com.opticalstore.repositories.MarksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarksService {

    private MarksRepository marksRepository;
    private MarksMapper marksMapper;

    public MarksService(MarksRepository marksRepository, MarksMapper marksMapper) {
        this.marksRepository = marksRepository;
        this.marksMapper = marksMapper;
    }

    public List<GlassesMarkDto> getGlassesMarkDto() {
        return marksRepository
                .findAll()
                .stream()
                .map(marksMapper::map)
                .collect(Collectors.toList());
    }

}
