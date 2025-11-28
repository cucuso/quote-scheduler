// Form submission handler - sends data to backend
document.getElementById('intakeForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const submitBtn = document.querySelector('.submit-btn');
    submitBtn.classList.add('loading');
    submitBtn.disabled = true;
    submitBtn.textContent = 'Submitting...';

    // Get company name from contenteditable element
    const companyName = document.querySelector('.company-name').textContent.trim();

    // Collect form data
    const formData = {
        companyName: companyName,
        language: currentLang,
        items: {},
        additionalItems: document.getElementById('additional-notes').value,
        fullName: document.getElementById('full-name').value,
        phoneNumber: document.getElementById('phone').value,
        pickupAddress: document.getElementById('pickup-address').value,
        pickupZipcode: document.getElementById('pickup-zipcode').value,
        pickupAccess: document.getElementById('pickup-access').value,
        deliveryAddress: document.getElementById('delivery-address').value,
        deliveryZipcode: document.getElementById('delivery-zipcode').value,
        deliveryAccess: document.getElementById('delivery-access').value,
        tentativeDate: document.getElementById('tentative-date').value,
        tentativeTime: document.getElementById('tentative-time').value,
        adSource: document.getElementById('ad-source').value
    };

    // Collect only items with quantity > 0
    document.querySelectorAll('input[type="radio"]:checked').forEach(radio => {
        const value = parseInt(radio.value);
        if (value > 0) {
            const name = radio.name;
            formData.items[name] = value;
        }
    });

    try {
        const response = await fetch('/bookings', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        const result = await response.json();

        if (response.ok) {
            // Success - show success message
            showSuccessMessage(result);
            // Reset form
            document.getElementById('intakeForm').reset();
        } else {
            // Error - show error message
            showErrorMessage(result.message || result.error || 'Failed to submit quote request');
        }
    } catch (error) {
        showErrorMessage('Network error: ' + error.message);
    } finally {
        submitBtn.classList.remove('loading');
        submitBtn.disabled = false;
        submitBtn.textContent = translations[currentLang].labels.submitBtn;
    }
});

// Show success message
function showSuccessMessage(result) {
    const modal = createMessageModal(
        'Success!',
        `Your quote request has been submitted successfully!<br><br>Reference ID: <strong>${result.id}</strong><br><br>We will contact you soon at ${result.phoneNumber}`,
        'success'
    );
    modal.style.display = 'flex';
}

// Show error message
function showErrorMessage(message) {
    const modal = createMessageModal(
        'Error',
        message,
        'error'
    );
    modal.style.display = 'flex';
}

// Create message modal
function createMessageModal(title, message, type) {
    let modal = document.getElementById('messageModal');
    if (!modal) {
        modal = document.createElement('div');
        modal.id = 'messageModal';
        modal.className = 'json-modal';
        modal.innerHTML = `
            <div class="json-modal-content" style="max-width: 500px;">
                <div class="json-modal-header">
                    <h3 id="modalTitle">${title}</h3>
                    <button class="json-modal-close">&times;</button>
                </div>
                <div style="padding: 2rem; text-align: center;">
                    <p id="modalMessage" style="font-size: 1.1rem; line-height: 1.6;"></p>
                </div>
                <div class="json-modal-footer">
                    <button class="json-modal-close-btn">Close</button>
                </div>
            </div>
        `;
        document.body.appendChild(modal);

        // Add event listeners
        modal.querySelector('.json-modal-close').addEventListener('click', () => {
            modal.style.display = 'none';
        });
        modal.querySelector('.json-modal-close-btn').addEventListener('click', () => {
            modal.style.display = 'none';
        });
        modal.addEventListener('click', (e) => {
            if (e.target === modal) {
                modal.style.display = 'none';
            }
        });
    }

    // Update content
    modal.querySelector('#modalTitle').textContent = title;
    modal.querySelector('#modalMessage').innerHTML = message;

    // Update header color based on type
    const header = modal.querySelector('.json-modal-header');
    if (type === 'success') {
        header.style.background = 'linear-gradient(135deg, #10B981 0%, #059669 100%)';
    } else {
        header.style.background = 'linear-gradient(135deg, #EF4444 0%, #DC2626 100%)';
    }

    return modal;
}

console.log('Form submission handler loaded');
