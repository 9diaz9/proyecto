package com.proyectocv.service;

import com.proyectocv.model.Curriculum;
import com.proyectocv.repository.CurriculumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurriculumService {

    private final CurriculumRepository repository;

    public CurriculumService(CurriculumRepository repository) {
        this.repository = repository;
    }

    public List<Curriculum> listarTodos() {
        return repository.findAll();
    }

    public void guardar(Curriculum curriculum) {
        repository.save(curriculum);
    }

    public Curriculum obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}