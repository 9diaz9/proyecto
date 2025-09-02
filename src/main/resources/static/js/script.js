document.addEventListener("DOMContentLoaded", () => {
    const setText = (id, value) => {
        const el = document.getElementById(id);
        if (el) el.textContent = value || "";
    };

    const fields = [
        { id: "nombre1", target: "previewNombre" },
        { id: "nombre2", target: "previewNombre" },
        { id: "apellido1", target: "previewNombre" },
        { id: "apellido2", target: "previewNombre" },
        { id: "profesion", target: "previewProfesion" },
        { id: "fechaNacimiento", target: "previewFecha" },
        { id: "sexo", target: "previewSexo" },
    
        { id: "ciudad", target: "previewCiudad" },
        { id: "cedula", target: "previewCedula" },
        { id: "direccion", target: "previewDireccion" },
        { id: "email", target: "previewEmail" },
        { id: "telefono", target: "previewTelefono" },
        { id: "experiencia", target: "previewExperiencia" },
        { id: "habilidades", target: "previewHabilidades" },
    ];

    function updatePreview() {
        const nombre1   = document.getElementById("nombre1")?.value || "";
        const nombre2   = document.getElementById("nombre2")?.value || "";
        const apellido1 = document.getElementById("apellido1")?.value || "";
        const apellido2 = document.getElementById("apellido2")?.value || "";
        setText("previewNombre", `${nombre1} ${nombre2} ${apellido1} ${apellido2}`.trim());

        fields.forEach(f => {
            if (f.target !== "previewNombre") {
                const input = document.getElementById(f.id);
                setText(f.target, input ? input.value : "");
            }
        });
    }

    // Conecta evento "input" y también "change" por si algún navegador no dispara input siempre
    let bound = 0;
    fields.forEach(f => {
        const input = document.getElementById(f.id);
        if (input) {
            input.addEventListener("input", updatePreview);
            input.addEventListener("change", updatePreview);
            bound++;
        }
    });

    console.log(`[script.js] Campos enlazados: ${bound}`);
    updatePreview();
});
// ===== Fin script.js =====

// === Autocomplete país usando API ===
document.addEventListener("DOMContentLoaded", function () {

    const paisInput = document.getElementById("pais");
    const paisList = document.getElementById("pais-list");

    paisInput.addEventListener("input", function () {
        const query = this.value.trim();
        paisList.innerHTML = "";

        if (query.length < 1) return; // no buscar si no hay na

        fetch(`https://restcountries.com/v3.1/name/${query}`)
            .then(res => res.json())
            .then(data => {
                data.forEach(country => {
                    const item = document.createElement("button");
                    item.type = "button";
                    item.className = "list-group-item list-group-item-action";
                    item.textContent = country.name.common; // nombre del país
                    // bandera
                    // item.innerHTML = `<img src="${country.flags.png}" width="20"> ${country.name.common}`;
                    item.addEventListener("click", () => {
                        paisInput.value = country.name.common;
                        paisList.innerHTML = "";
                    });
                    paisList.appendChild(item);
                });
            })
            .catch(err => {
                paisList.innerHTML = ""; // limpia si no hay coincidencias
            });
    });

    // cerrar la lista si se hace click fuera
    document.addEventListener("click", function (e) {
        if (!paisInput.contains(e.target)) {
            paisList.innerHTML = "";
        }
    });

});