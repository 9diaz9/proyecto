// === Autocomplete país usando REST Countries API ===
document.addEventListener("DOMContentLoaded", function () {
    const paisInput = document.getElementById("pais");
    const paisList = document.getElementById("pais-list");

    paisInput.addEventListener("input", function () {
        const query = this.value.trim();
        paisList.innerHTML = "";

        if (query.length < 1) return;

        fetch(`https://restcountries.com/v3.1/name/${query}`)
            .then(res => res.json())
            .then(data => {
                data.forEach(country => {
                    const item = document.createElement("button");
                    item.type = "button";
                    item.className = "list-group-item list-group-item-action";
                    item.textContent = country.name.common;
                    item.addEventListener("click", () => {
                        paisInput.value = country.name.common;
                        paisList.innerHTML = "";
                        updatePreview();
                    });
                    paisList.appendChild(item);
                });
            })
            .catch(err => {
                paisList.innerHTML = "";
            });
    });

    document.addEventListener("click", function (e) {
        if (!paisInput.contains(e.target)) {
            paisList.innerHTML = "";
        }
    });

    // Inicializar vista previa
    updatePreview();
});

// === Vista previa en tiempo real ===
function updatePreview() {
    // Obtener valores de los campos por ID
    const nombre1 = document.getElementById('nombre1')?.value || '';
    const nombre2 = document.getElementById('nombre2')?.value || '';
    const apellido1 = document.getElementById('apellido1')?.value || '';
    const apellido2 = document.getElementById('apellido2')?.value || '';
    const profesion = document.getElementById('profesion')?.value || '';
    const fechaNacimiento = document.getElementById('fechaNacimiento')?.value || '';
    const sexo = document.getElementById('sexo')?.value || '';
    const pais = document.getElementById('pais')?.value || '';
    const ciudad = document.getElementById('ciudad')?.value || '';
    const cedula = document.getElementById('cedula')?.value || '';
    const direccion = document.getElementById('direccion')?.value || '';
    const estadoCivil = document.getElementById('estadoCivil')?.value || '';
    const email = document.getElementById('email')?.value || '';
    const telefono = document.getElementById('telefono')?.value || '';
    const experiencia = document.getElementById('experiencia')?.value || '';
    const habilidades = document.getElementById('habilidades')?.value || '';

    // Actualizar vista previa
    document.getElementById('previewNombre').textContent = `${nombre1} ${nombre2} ${apellido1} ${apellido2}`.trim() || '-';
    document.getElementById('previewProfesion').textContent = profesion || '-';
    document.getElementById('previewFecha').textContent = fechaNacimiento || '-';
    document.getElementById('previewSexo').textContent = sexo || '-';
    document.getElementById('previewPais').textContent = pais || '-';
    document.getElementById('previewCiudad').textContent = ciudad || '-';
    document.getElementById('previewCedula').textContent = cedula || '-';
    document.getElementById('previewDireccion').textContent = direccion || '-';
    document.getElementById('previewEstadoCivil').textContent = estadoCivil || '-';
    document.getElementById('previewEmail').textContent = email || '-';
    document.getElementById('previewTelefono').textContent = telefono || '-';
    document.getElementById('previewExperiencia').textContent = experiencia || '-';
    document.getElementById('previewHabilidades').textContent = habilidades || '-';
}

// Validación del formulario antes de enviar
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('cvForm');
    if (form) {
        form.addEventListener('submit', function(e) {
            let isValid = true;
            let firstError = null;
            
            // Ocultar mensaje de error general
            document.getElementById('errorGeneral').style.display = 'none';
            
            // Validar campos requeridos
            const requiredFields = form.querySelectorAll('[required]');
            requiredFields.forEach(field => {  // CORREGIDO: field => {
                if (!field.value.trim()) {
                    isValid = false;
                    if (!firstError) firstError = field;
                    field.classList.add('is-invalid');
                } else {
                    field.classList.remove('is-invalid');
                }
            });
            
            if (!isValid) {
                e.preventDefault();
                // Mostrar mensaje de error general
                const errorContainer = document.getElementById('errorGeneral');
                const errorText = document.getElementById('errorText');
                errorText.textContent = 'Por favor, complete todos los campos obligatorios marcados con *.';
                errorContainer.style.display = 'block';
                
                // Desplazarse al primer error
                if (firstError) {
                    firstError.scrollIntoView({ behavior: 'smooth', block: 'center' });
                }
            }
        });
    }
});