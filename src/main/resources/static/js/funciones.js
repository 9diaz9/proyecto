export function initPhoneInput(inputSelector) {
    const inputPhone = document.querySelector(inputSelector);
    if (!inputPhone) return null;

    const iti = window.intlTelInput(inputPhone, {
        initialCountry: "auto",
        geoIpLookup: function (success, failure) {
            fetch("https://ipapi.co/json")
                .then(res => res.json())
                .then(data => success(data.country_code))
                .catch(() => failure());
        },
        preferredCountries: ['co', 'us', 'mx', 'es', 'ar', 'fr'],
        separateDialCode: true,
        hiddenInput: "full_phone",
        utilsScript: "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.19/js/utils.js",
        customPlaceholder: function (selectedCountryPlaceholder, selectedCountryData) {
            return "Ej: " + selectedCountryPlaceholder;
        }
    });

    inputPhone.placeholder = "";
    return iti;
}