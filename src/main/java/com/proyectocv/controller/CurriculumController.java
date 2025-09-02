package com.proyectocv.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectocv.model.Curriculum;
import com.proyectocv.service.CurriculumService;
import com.proyectocv.springboot.di.app.proyectocv.dto.Curriculumdot;

import jakarta.validation.Valid;

@Controller
public class CurriculumController {

    private final CurriculumService service;

    public CurriculumController(CurriculumService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("curriculums", service.listarTodos());
        model.addAttribute("curriculum", new Curriculumdot());
        return "index";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("curriculum") Curriculumdot curriculumDot,
                        BindingResult result,
                        Model model,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errores.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errores", errores);
            model.addAttribute("curriculums", service.listarTodos());
            return "index";
        }
        
        Curriculum curriculum = convertirDtoAEntidad(curriculumDot);
        service.guardar(curriculum);
        redirectAttributes.addFlashAttribute("mensaje", "CV guardado exitosamente");
        return "redirect:/";
    }

/*    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("curriculum") Curriculumdot curriculumDot,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errores.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errores", errores);
            return "editar";
        }
        
        Curriculum curriculum = convertirDtoAEntidad(curriculumDot);
        curriculum.setId(id);
        service.guardar(curriculum);
        redirectAttributes.addFlashAttribute("mensaje", "CV actualizado exitosamente");
        return "redirect:/";
    } */

    @GetMapping("/editar/{id}")
    public String editarCV(@PathVariable Long id, Model model) {
        Curriculum cv = service.obtenerPorId(id);
        Curriculumdot curriculumDot = convertirEntidadADto(cv);
        model.addAttribute("curriculum", curriculumDot);
        return "editar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCV(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "CV eliminado exitosamente");
        return "redirect:/";
    }

    // Métodos de conversión
    private Curriculum convertirDtoAEntidad(Curriculumdot dto) {
        Curriculum curriculum = new Curriculum();
        curriculum.setNombre1(dto.getNombre1());
        curriculum.setNombre2(dto.getNombre2());
        curriculum.setApellido1(dto.getApellido1());
        curriculum.setApellido2(dto.getApellido2());
        curriculum.setProfesion(dto.getProfesion());
        curriculum.setFechaNacimiento(dto.getFechaNacimiento());
        curriculum.setSexo(dto.getSexo());
        curriculum.setPais(dto.getPais());
        curriculum.setCiudad(dto.getCiudad());
        curriculum.setCedula(dto.getCedula().replaceAll("[.-]", ""));
        curriculum.setDireccion(dto.getDireccion());
        curriculum.setEstadoCivil(dto.getEstadoCivil());
        curriculum.setEmail(dto.getEmail());
        curriculum.setTelefono(dto.getTelefono());
        curriculum.setExperiencia(dto.getExperiencia());
        curriculum.setHabilidades(dto.getHabilidades());
        
        return curriculum;
    }

    private Curriculumdot convertirEntidadADto(Curriculum curriculum) {
        Curriculumdot dto = new Curriculumdot();
        dto.setNombre1(curriculum.getNombre1());
        dto.setNombre2(curriculum.getNombre2());
        dto.setApellido1(curriculum.getApellido1());
        dto.setApellido2(curriculum.getApellido2());
        dto.setProfesion(curriculum.getProfesion());
        dto.setFechaNacimiento(curriculum.getFechaNacimiento());
        dto.setSexo(curriculum.getSexo());
        dto.setPais(curriculum.getPais());
        dto.setCiudad(curriculum.getCiudad());
        dto.setCedula(curriculum.getCedula());
        dto.setDireccion(curriculum.getDireccion());
        dto.setEstadoCivil(curriculum.getEstadoCivil());
        dto.setEmail(curriculum.getEmail());
        dto.setTelefono(curriculum.getTelefono());
        dto.setExperiencia(curriculum.getExperiencia());
        dto.setHabilidades(curriculum.getHabilidades());
        
        return dto;
    }
}