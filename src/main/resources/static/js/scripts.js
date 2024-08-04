document.addEventListener("DOMContentLoaded", function() {
    const phoneInput = document.getElementById('phone');
    phoneInput.addEventListener('input', function() {
        const phone = phoneInput.value;
        if (!/^\d{10}$/.test(phone)) {
            phoneInput.style.borderColor = 'red';
        } else {
            phoneInput.style.borderColor = '';
        }
    });
});
