package com.proyectocv.springboot.di.app.proyectocv.dto;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Curriculumdot {

    @NotBlank(message = "El primer nombre es obligatorio")
    @Pattern(
        regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,50}$",
        message = "El nombre solo puede contener letras y espacios (2-50 caracteres)"
    )
    private String nombre1;

    @Pattern(
        regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{0,50}$",
        message = "El segundo nombre solo puede contener letras y espacios (máx. 50 caracteres)"
    )
    private String nombre2;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Pattern(
        regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,50}$",
        message = "El apellido solo puede contener letras y espacios (2-50 caracteres)"
    )
    private String apellido1;

    @NotBlank(message = "El segundo apellido es obligatorio")
    @Pattern(
        regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,50}$",
        message = "El segundo apellido solo puede contener letras y espacios (2-50 caracteres)"
    )
    private String apellido2;

    @NotBlank(message = "La profesión es obligatoria")
    @Size(min = 3, max = 50, message = "La profesión debe tener entre 3 y 50 caracteres")
    @Pattern(
        regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s\\-()]{3,50}$",
        message = "La profesión solo puede contener letras, espacios, guiones y paréntesis"
    )
    private String profesion;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento no puede ser futura")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El sexo es obligatorio")
    @Pattern(
        regexp = "^(Hombre|Mujer|Prefiero no decir)$",
        message = "Valor inválido, elige Hombre, Mujer o Prefiero no decir"
    )
    private String sexo;

    @NotBlank(message = "El país es obligatorio")
    @Size(min = 2, max = 50, message = "El país debe tener entre 2 y 50 caracteres")
    private String pais;

    @Size(max = 50, message = "La ciudad no puede exceder los 50 caracteres")
    private String ciudad;

    @NotBlank(message = "La cédula es obligatoria")
    @Pattern(
        regexp = "^[0-9.-]{5,20}$", 
        message = "La cédula debe contener solo números, puntos y guiones (5-20 caracteres)"
    )
    private String cedula;

    @Size(max = 200, message = "La dirección no puede exceder los 200 caracteres")
    private String direccion;

    @NotBlank(message = "El estado civil es obligatorio")
    @Pattern(
        regexp = "^(Soltero|Casado|Divorciado|Viudo|Unión libre|Prefiero no decir)$", 
        message = "Seleccione una opción válida para estado civil"
    )
    private String estadoCivil;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El formato del correo electrónico no es válido")
    @Size(max = 100, message = "El correo electrónico no puede exceder los 100 caracteres")
    private String email;

    @Pattern(
        regexp = "^[+]?[0-9\\s\\-\\(\\)]{7,20}$",
        message = "El teléfono debe contener solo números, espacios, guiones y paréntesis"
    )
    private String telefono;

    @Size(max = 1000, message = "La experiencia no puede exceder los 1000 caracteres")
    private String experiencia;

    @Size(max = 500, message = "Las habilidades no pueden exceder los 500 caracteres")
    private String habilidades;

    // Métodos de validación personalizados
    @AssertTrue(message = "Debes tener al menos 15 años")
    public boolean isEdadValida() {
        if (this.fechaNacimiento == null) return false;
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears() >= 15;
    }

    @AssertTrue(message = "La cédula no puede empezar o terminar con puntos o guiones, ni tener caracteres especiales consecutivos")
    public boolean isCedulaFormatoValido() {
        if (cedula == null || cedula.trim().isEmpty()) return false;
        
        String trimmedCedula = cedula.trim();
        
        // Validar que no empiece o termine con caracteres especiales
        if (trimmedCedula.startsWith(".") || trimmedCedula.startsWith("-") ||
            trimmedCedula.endsWith(".") || trimmedCedula.endsWith("-")) {
            return false;
        }
        
        // Validar que no tenga caracteres especiales consecutivos
        if (trimmedCedula.contains("..") || trimmedCedula.contains("--") || 
            trimmedCedula.contains(".-") || trimmedCedula.contains("-.")) {
            return false;
        }
        
        return true;
    }

    // Getters y Setters (mantener los mismos)
    public String getNombre1() { return nombre1; }
    public void setNombre1(String nombre1) { this.nombre1 = nombre1; }

    public String getNombre2() { return nombre2; }
    public void setNombre2(String nombre2) { this.nombre2 = nombre2; }

    public String getApellido1() { return apellido1; }
    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }

    public String getApellido2() { return apellido2; }
    public void setApellido2(String apellido2) { this.apellido2 = apellido2; }

    public String getProfesion() { return profesion; }
    public void setProfesion(String profesion) { this.profesion = profesion; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getExperiencia() { return experiencia; }
    public void setExperiencia(String experiencia) { this.experiencia = experiencia; }

    public String getHabilidades() { return habilidades; }
    public void setHabilidades(String habilidades) { this.habilidades = habilidades; }
}