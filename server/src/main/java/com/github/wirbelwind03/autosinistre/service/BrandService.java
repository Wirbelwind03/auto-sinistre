package com.github.wirbelwind03.autosinistre.service;

import com.github.wirbelwind03.autosinistre.model.entity.Brand;
import com.github.wirbelwind03.autosinistre.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }
}
