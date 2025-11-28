// Translation data
const translations = {
    en: {
        companyName: "Company Name Here",
        language: "Language:",
        sections: {
            bedroom: "Bedroom",
            hall: "Hall",
            dining: "Dining Room",
            kitchen: "Kitchen",
            others: "Others",
            additionalInfo: "Additional Information",
            contactInfo: "Contact Information",
            pickupInfo: "Pickup Information",
            deliveryInfo: "Delivery Information",
            scheduleSource: "Schedule & Source"
        },
        items: {
            // Bedroom
            beds: "Beds",
            electricBed: "Electric bed base with motor",
            bunkBed: "Bunk bed",
            kingMattress: "King mattress",
            queenMattress: "Queen mattress",
            fullMattress: "Full mattress",
            twinMattress: "Twin mattress",
            cradle: "Cradle",
            bedsideLamp: "Bedside lamp",
            dresser: "Dresser",
            drawer: "Drawer",
            vanity: "Vanity",
            shoeRack: "Shoe rack",
            bigCloset: "Big closet",
            ottoman: "Ottoman",
            // Hall
            "1SeaterCouch": "1 seater couch",
            "2SeaterCouch": "2 seater couch",
            "3SeaterCouch": "3 seater couch",
            sectionalCouch: "Sectional couch",
            recliningSeat: "Reclining seat",
            couchBed: "Couch bed",
            armchair: "Armchair",
            bigChair: "Big chair",
            centerTable: "Center table",
            livingTable: "Living table",
            consoleTable: "Console table",
            rug: "Rug",
            wallPicture: "Wall picture",
            wallMirror: "Wall mirror",
            floorMirror: "Floor mirror",
            floorLamp: "Floor lamp",
            largeTvStand: "Large TV stand",
            smallTvStand: "Small TV stand",
            tv: "TV",
            // Dining
            woodenTable4: "Wooden dining table 4 chairs",
            woodenTable6: "Wooden dining table 6 chairs",
            woodenTable8: "Wooden dining table 8 chairs",
            glassTable4: "Glass or marble dining table 4 chairs",
            glassTable6: "Glass or marble dining table 6 chairs",
            sideboard: "Dining room sideboard",
            showcase: "Showcase",
            // Kitchen
            refrigerator: "Refrigerator",
            executiveRefrigerator: "Executive refrigerator",
            freezer: "Freezer",
            counterChair: "Counter chair",
            microwave: "Microwave",
            // Others
            bookcase: "Bookcase",
            desk: "Desk",
            computerTable: "Computer table",
            officeChair: "Office chair",
            treadmill: "Treadmill",
            stationaryBike: "Stationary bike",
            largeShelf: "Large shelf",
            smallShelf: "Small shelf",
            bbq: "BBQ",
            floors: "Floors",
            coffeeTable: "Coffee table",
            patioCouch: "Patio couch",
            largeBox: "Large box",
            mediumBox: "Medium box",
            smallBox: "Small box",
            garbageBag: "Garbage bag",
            suitcase: "Suitcase"
        },
        labels: {
            item: "Item",
            additionalNotes: "Any other items, doubts, etc?",
            additionalNotesPlaceholder: "Please describe any additional items or questions...",
            fullName: "Full Name",
            phone: "Phone Number",
            pickupAddress: "Pickup Address",
            pickupZipcode: "Pickup Zipcode",
            pickupAccess: "Pickup Access",
            deliveryAddress: "Delivery Address",
            deliveryZipcode: "Delivery Zipcode",
            deliveryAccess: "Delivery Access",
            tentativeDate: "Tentative Date",
            tentativeDateHelp: "This date can be changed later",
            tentativeTime: "Preferred Time",
            tentativeTimeHelp: "Approximate time of day",
            adSource: "Where did you see this ad?",
            submitBtn: "Submit Form"
        },
        placeholders: {
            fullName: "John Doe",
            phone: "(555) 123-4567",
            pickupAddress: "123 Main Street",
            pickupZipcode: "12345",
            deliveryAddress: "456 Oak Avenue",
            deliveryZipcode: "54321",
            adSource: "Facebook, Google, Friend, etc.",
            selectAccessType: "Select access type..."
        },
        accessTypes: {
            "1stFloorHouse": "1st floor house",
            "2ndFloor": "2nd floor",
            apartment: "Apartment",
            townhouse: "Townhouse",
            duplex: "Duplex",
            efficiency: "Efficiency"
        }
    },
    es: {
        companyName: "Nombre de la Empresa Aquí",
        language: "Idioma:",
        sections: {
            bedroom: "Dormitorio",
            hall: "Sala",
            dining: "Comedor",
            kitchen: "Cocina",
            others: "Otros",
            additionalInfo: "Información Adicional",
            contactInfo: "Información de Contacto",
            pickupInfo: "Información de Recogida",
            deliveryInfo: "Información de Entrega",
            scheduleSource: "Fecha y Origen"
        },
        items: {
            // Bedroom
            beds: "Camas",
            electricBed: "Base de cama eléctrica con motor",
            bunkBed: "Litera",
            kingMattress: "Colchón king",
            queenMattress: "Colchón queen",
            fullMattress: "Colchón full",
            twinMattress: "Colchón twin",
            cradle: "Cuna",
            bedsideLamp: "Lámpara de noche",
            dresser: "Cómoda",
            drawer: "Cajones",
            vanity: "Tocador",
            shoeRack: "Zapatero",
            bigCloset: "Closet grande",
            ottoman: "Otomana",
            // Hall
            "1SeaterCouch": "Sofá de 1 puesto",
            "2SeaterCouch": "Sofá de 2 puestos",
            "3SeaterCouch": "Sofá de 3 puestos",
            sectionalCouch: "Sofá seccional",
            recliningSeat: "Silla reclinable",
            couchBed: "Sofá cama",
            armchair: "Sillón",
            bigChair: "Silla grande",
            centerTable: "Mesa de centro",
            livingTable: "Mesa de sala",
            consoleTable: "Mesa consola",
            rug: "Alfombra",
            wallPicture: "Cuadro de pared",
            wallMirror: "Espejo de pared",
            floorMirror: "Espejo de piso",
            floorLamp: "Lámpara de piso",
            largeTvStand: "Mueble de TV grande",
            smallTvStand: "Mueble de TV pequeño",
            tv: "Televisor",
            // Dining
            woodenTable4: "Mesa de comedor de madera 4 sillas",
            woodenTable6: "Mesa de comedor de madera 6 sillas",
            woodenTable8: "Mesa de comedor de madera 8 sillas",
            glassTable4: "Mesa de comedor de vidrio o mármol 4 sillas",
            glassTable6: "Mesa de comedor de vidrio o mármol 6 sillas",
            sideboard: "Aparador de comedor",
            showcase: "Vitrina",
            // Kitchen
            refrigerator: "Refrigerador",
            executiveRefrigerator: "Refrigerador ejecutivo",
            freezer: "Congelador",
            counterChair: "Silla de barra",
            microwave: "Microondas",
            // Others
            bookcase: "Librero",
            desk: "Escritorio",
            computerTable: "Mesa de computadora",
            officeChair: "Silla de oficina",
            treadmill: "Caminadora",
            stationaryBike: "Bicicleta estacionaria",
            largeShelf: "Estante grande",
            smallShelf: "Estante pequeño",
            bbq: "Parrilla",
            floors: "Pisos",
            coffeeTable: "Mesa de café",
            patioCouch: "Sofá de patio",
            largeBox: "Caja grande",
            mediumBox: "Caja mediana",
            smallBox: "Caja pequeña",
            garbageBag: "Bolsa de basura",
            suitcase: "Maleta"
        },
        labels: {
            item: "Artículo",
            additionalNotes: "¿Otros artículos, dudas, etc?",
            additionalNotesPlaceholder: "Por favor describa cualquier artículo adicional o preguntas...",
            fullName: "Nombre Completo",
            phone: "Número de Teléfono",
            pickupAddress: "Dirección de Recogida",
            pickupZipcode: "Código Postal de Recogida",
            pickupAccess: "Acceso de Recogida",
            deliveryAddress: "Dirección de Entrega",
            deliveryZipcode: "Código Postal de Entrega",
            deliveryAccess: "Acceso de Entrega",
            tentativeDate: "Fecha Tentativa",
            tentativeDateHelp: "Esta fecha puede cambiar más tarde",
            tentativeTime: "Hora Preferida",
            tentativeTimeHelp: "Hora aproximada del día",
            adSource: "¿Dónde vio este anuncio?",
            submitBtn: "Enviar Formulario"
        },
        placeholders: {
            fullName: "Juan Pérez",
            phone: "(555) 123-4567",
            pickupAddress: "Calle Principal 123",
            pickupZipcode: "12345",
            deliveryAddress: "Avenida Roble 456",
            deliveryZipcode: "54321",
            adSource: "Facebook, Google, Amigo, etc.",
            selectAccessType: "Seleccione tipo de acceso..."
        },
        accessTypes: {
            "1stFloorHouse": "Casa de 1er piso",
            "2ndFloor": "2do piso",
            apartment: "Apartamento",
            townhouse: "Casa adosada",
            duplex: "Dúplex",
            efficiency: "Estudio"
        }
    }
};

// Current language
let currentLang = 'en';

// Get language from URL parameter
function getLangFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    const lang = urlParams.get('lang');
    return (lang === 'es' || lang === 'en') ? lang : 'en';
}

// Update language
function setLanguage(lang) {
    currentLang = lang;
    const t = translations[lang];

    // Update company name
    document.querySelector('.company-name').textContent = t.companyName;

    // Update language label
    document.querySelector('.language-selector label').textContent = t.language;

    // Update section titles
    document.querySelectorAll('.section-title').forEach(title => {
        const text = title.textContent.trim();
        if (text === translations.en.sections.bedroom || text === translations.es.sections.bedroom) {
            title.textContent = t.sections.bedroom;
        } else if (text === translations.en.sections.hall || text === translations.es.sections.hall) {
            title.textContent = t.sections.hall;
        } else if (text.includes('Dining') || text.includes('Comedor')) {
            title.textContent = t.sections.dining;
        } else if (text === translations.en.sections.kitchen || text === translations.es.sections.kitchen) {
            title.textContent = t.sections.kitchen;
        } else if (text === translations.en.sections.others || text === translations.es.sections.others) {
            title.textContent = t.sections.others;
        } else if (text.includes('Additional') || text.includes('Adicional')) {
            title.textContent = t.sections.additionalInfo;
        } else if (text.includes('Contact') || text.includes('Contacto')) {
            title.textContent = t.sections.contactInfo;
        } else if (text.includes('Pickup') || text.includes('Recogida')) {
            title.textContent = t.sections.pickupInfo;
        } else if (text.includes('Delivery') || text.includes('Entrega')) {
            title.textContent = t.sections.deliveryInfo;
        } else if (text.includes('Schedule') || text.includes('Fecha')) {
            title.textContent = t.sections.scheduleSource;
        }
    });

    // Update quantity header
    document.querySelectorAll('.item-label-header').forEach(el => {
        el.textContent = t.labels.item;
    });

    // Update item labels - map HTML text to translation keys
    const itemMapping = {
        'Beds': 'beds', 'Camas': 'beds',
        'Electric bed base with motor': 'electricBed', 'Base de cama eléctrica con motor': 'electricBed',
        'Bunk bed': 'bunkBed', 'Litera': 'bunkBed',
        'King mattress': 'kingMattress', 'Colchón king': 'kingMattress',
        'Queen mattress': 'queenMattress', 'Colchón queen': 'queenMattress',
        'Full mattress': 'fullMattress', 'Colchón full': 'fullMattress',
        'Twin mattress': 'twinMattress', 'Colchón twin': 'twinMattress',
        'Cradle': 'cradle', 'Cuna': 'cradle',
        'Bedside lamp': 'bedsideLamp', 'Lámpara de noche': 'bedsideLamp',
        'Dresser': 'dresser', 'Cómoda': 'dresser',
        'Drawer': 'drawer', 'Cajones': 'drawer',
        'Vanity': 'vanity', 'Tocador': 'vanity',
        'Shoe rack': 'shoeRack', 'Zapatero': 'shoeRack',
        'Big closet': 'bigCloset', 'Closet grande': 'bigCloset',
        'Ottoman': 'ottoman', 'Otomana': 'ottoman',
        '1 seater couch': '1SeaterCouch', 'Sofá de 1 puesto': '1SeaterCouch',
        '2 seater couch': '2SeaterCouch', 'Sofá de 2 puestos': '2SeaterCouch',
        '3 seater couch': '3SeaterCouch', 'Sofá de 3 puestos': '3SeaterCouch',
        'Sectional couch': 'sectionalCouch', 'Sofá seccional': 'sectionalCouch',
        'Reclining seat': 'recliningSeat', 'Silla reclinable': 'recliningSeat',
        'Couch bed': 'couchBed', 'Sofá cama': 'couchBed',
        'Armchair': 'armchair', 'Sillón': 'armchair',
        'Big chair': 'bigChair', 'Silla grande': 'bigChair',
        'Center table': 'centerTable', 'Mesa de centro': 'centerTable',
        'Living table': 'livingTable', 'Mesa de sala': 'livingTable',
        'Console table': 'consoleTable', 'Mesa consola': 'consoleTable',
        'Rug': 'rug', 'Alfombra': 'rug',
        'Wall picture': 'wallPicture', 'Cuadro de pared': 'wallPicture',
        'Wall mirror': 'wallMirror', 'Espejo de pared': 'wallMirror',
        'Floor mirror': 'floorMirror', 'Espejo de piso': 'floorMirror',
        'Floor lamp': 'floorLamp', 'Lámpara de piso': 'floorLamp',
        'Large TV stand': 'largeTvStand', 'Mueble de TV grande': 'largeTvStand',
        'Small TV stand': 'smallTvStand', 'Mueble de TV pequeño': 'smallTvStand',
        'TV': 'tv', 'Televisor': 'tv',
        'Wooden dining table 4 chairs': 'woodenTable4', 'Mesa de comedor de madera 4 sillas': 'woodenTable4',
        'Wooden dining table 6 chairs': 'woodenTable6', 'Mesa de comedor de madera 6 sillas': 'woodenTable6',
        'Wooden dining table 8 chairs': 'woodenTable8', 'Mesa de comedor de madera 8 sillas': 'woodenTable8',
        'Glass or marble dining table 4 chairs': 'glassTable4', 'Mesa de comedor de vidrio o mármol 4 sillas': 'glassTable4',
        'Glass or marble dining table 6 chairs': 'glassTable6', 'Mesa de comedor de vidrio o mármol 6 sillas': 'glassTable6',
        'Dining room sideboard': 'sideboard', 'Aparador de comedor': 'sideboard',
        'Showcase': 'showcase', 'Vitrina': 'showcase',
        'Refrigerator': 'refrigerator', 'Refrigerador': 'refrigerator',
        'Executive refrigerator': 'executiveRefrigerator', 'Refrigerador ejecutivo': 'executiveRefrigerator',
        'Freezer': 'freezer', 'Congelador': 'freezer',
        'Counter chair': 'counterChair', 'Silla de barra': 'counterChair',
        'Microwave': 'microwave', 'Microondas': 'microwave',
        'Bookcase': 'bookcase', 'Librero': 'bookcase',
        'Desk': 'desk', 'Escritorio': 'desk',
        'Computer table': 'computerTable', 'Mesa de computadora': 'computerTable',
        'Office chair': 'officeChair', 'Silla de oficina': 'officeChair',
        'Treadmill': 'treadmill', 'Caminadora': 'treadmill',
        'Stationary bike': 'stationaryBike', 'Bicicleta estacionaria': 'stationaryBike',
        'Large shelf': 'largeShelf', 'Estante grande': 'largeShelf',
        'Small shelf': 'smallShelf', 'Estante pequeño': 'smallShelf',
        'BBQ': 'bbq', 'Parrilla': 'bbq',
        'Floors': 'floors', 'Pisos': 'floors',
        'Coffee table': 'coffeeTable', 'Mesa de café': 'coffeeTable',
        'Patio couch': 'patioCouch', 'Sofá de patio': 'patioCouch',
        'Large box': 'largeBox', 'Caja grande': 'largeBox',
        'Medium box': 'mediumBox', 'Caja mediana': 'mediumBox',
        'Small box': 'smallBox', 'Caja pequeña': 'smallBox',
        'Garbage bag': 'garbageBag', 'Bolsa de basura': 'garbageBag',
        'Suitcase': 'suitcase', 'Maleta': 'suitcase'
    };

    document.querySelectorAll('.item-label').forEach(el => {
        const currentText = el.textContent.trim();
        const key = itemMapping[currentText];
        if (key && t.items[key]) {
            el.textContent = t.items[key];
        }
    });

    // Update form labels
    const labelUpdates = {
        'additional-notes': t.labels.additionalNotes,
        'full-name': t.labels.fullName + ' *',
        'phone': t.labels.phone + ' *',
        'pickup-address': t.labels.pickupAddress + ' *',
        'pickup-zipcode': t.labels.pickupZipcode + ' *',
        'pickup-access': t.labels.pickupAccess + ' *',
        'delivery-address': t.labels.deliveryAddress + ' *',
        'delivery-zipcode': t.labels.deliveryZipcode + ' *',
        'delivery-access': t.labels.deliveryAccess + ' *',
        'tentative-date': t.labels.tentativeDate + ' *',
        'tentative-time': t.labels.tentativeTime + ' *',
        'ad-source': t.labels.adSource + ' *'
    };

    Object.keys(labelUpdates).forEach(id => {
        const label = document.querySelector(`label[for="${id}"]`);
        if (label) label.textContent = labelUpdates[id];
    });

    // Update placeholders
    const placeholderUpdates = {
        'additional-notes': t.placeholders.additionalNotesPlaceholder,
        'full-name': t.placeholders.fullName,
        'phone': t.placeholders.phone,
        'pickup-address': t.placeholders.pickupAddress,
        'pickup-zipcode': t.placeholders.pickupZipcode,
        'delivery-address': t.placeholders.deliveryAddress,
        'delivery-zipcode': t.placeholders.deliveryZipcode,
        'ad-source': t.placeholders.adSource
    };

    Object.keys(placeholderUpdates).forEach(id => {
        const input = document.getElementById(id);
        if (input) input.placeholder = placeholderUpdates[id];
    });

    // Update help text
    const helpTexts = document.querySelectorAll('.help-text');
    if (helpTexts.length >= 2) {
        helpTexts[helpTexts.length - 2].textContent = t.labels.tentativeDateHelp;
        helpTexts[helpTexts.length - 1].textContent = t.labels.tentativeTimeHelp;
    }

    // Update access type options
    document.querySelectorAll('#pickup-access option, #delivery-access option').forEach(option => {
        if (option.value === '') {
            option.textContent = t.placeholders.selectAccessType;
        } else if (option.value === '1st-floor-house') {
            option.textContent = t.accessTypes['1stFloorHouse'];
        } else if (option.value === '2nd-floor') {
            option.textContent = t.accessTypes['2ndFloor'];
        } else if (option.value === 'apartment') {
            option.textContent = t.accessTypes.apartment;
        } else if (option.value === 'townhouse') {
            option.textContent = t.accessTypes.townhouse;
        } else if (option.value === 'duplex') {
            option.textContent = t.accessTypes.duplex;
        } else if (option.value === 'efficiency') {
            option.textContent = t.accessTypes.efficiency;
        }
    });

    // Update submit button
    document.querySelector('.submit-btn').textContent = t.labels.submitBtn;
}

// Note: Form submission is handled by script.js

// Language dropdown change handler
document.getElementById('language').addEventListener('change', function (e) {
    setLanguage(e.target.value);
});

// Initialize on page load
window.addEventListener('load', function () {
    // Get language from URL or default to 'en'
    const urlLang = getLangFromURL();
    currentLang = urlLang;

    // Set dropdown value
    document.getElementById('language').value = urlLang;

    // Apply translation
    if (urlLang !== 'en') {
        setLanguage(urlLang);
    }
});

console.log('Multilingual support loaded. Use ?lang=es or ?lang=en in URL');
