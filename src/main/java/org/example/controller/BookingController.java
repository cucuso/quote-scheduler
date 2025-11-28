package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Booking;
import org.example.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // GET /form - serves the input page
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "form";
    }

    // POST /bookings - saves booking in DB
    @PostMapping("/bookings")
    @ResponseBody
    public ResponseEntity<?> createBooking(@RequestBody Map<String, Object> requestData) {
        try {
            Booking booking = new Booking();

            // Set basic fields
            booking.setCompanyName((String) requestData.get("companyName"));
            booking.setLanguage((String) requestData.get("language"));
            booking.setAdditionalItems((String) requestData.get("additionalItems"));
            booking.setFullName((String) requestData.get("fullName"));
            booking.setPhoneNumber((String) requestData.get("phoneNumber"));
            booking.setPickupAddress((String) requestData.get("pickupAddress"));
            booking.setPickupZipcode((String) requestData.get("pickupZipcode"));
            booking.setPickupAccess((String) requestData.get("pickupAccess"));
            booking.setDeliveryAddress((String) requestData.get("deliveryAddress"));
            booking.setDeliveryZipcode((String) requestData.get("deliveryZipcode"));
            booking.setDeliveryAccess((String) requestData.get("deliveryAccess"));
            booking.setTentativeDate(LocalDate.parse((String) requestData.get("tentativeDate")));
            booking.setTentativeTime((String) requestData.get("tentativeTime"));
            booking.setAdSource((String) requestData.get("adSource"));

            // Convert items map to JSON string
            Map<String, Integer> items = (Map<String, Integer>) requestData.get("items");
            if (items != null && !items.isEmpty()) {
                booking.setItems(objectMapper.writeValueAsString(items));
            }

            Booking saved = bookingRepository.save(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to create booking");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // GET /calendar/:companyName - serves viewer page
    @GetMapping("/calendar/{companyName}")
    public String showCalendar(@PathVariable String companyName, Model model) {
        model.addAttribute("companyName", companyName);
        return "calendar";
    }

    // GET /api/bookings?companyName=...&from=...&to=... - calendar fetch
    @GetMapping("/api/bookings")
    @ResponseBody
    public ResponseEntity<List<Booking>> getBookings(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        List<Booking> bookings;

        if (companyName != null && from != null && to != null) {
            bookings = bookingRepository.findByCompanyNameAndDateRange(companyName, from, to);
        } else if (companyName != null) {
            bookings = bookingRepository.findByCompanyName(companyName);
        } else {
            bookings = bookingRepository.findAllOrderByCreatedAtDesc();
        }

        return ResponseEntity.ok(bookings);
    }

    // PUT /api/bookings/:id - update booking
    @PutMapping("/api/bookings/{id}")
    @ResponseBody
    public ResponseEntity<?> updateBooking(
            @PathVariable Long id,
            @RequestBody Map<String, Object> requestData
    ) {
        try {
            Optional<Booking> existingOpt = bookingRepository.findById(id);

            if (existingOpt.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Booking not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            Booking existing = existingOpt.get();

            // Update fields
            if (requestData.containsKey("tentativeDate")) {
                existing.setTentativeDate(LocalDate.parse((String) requestData.get("tentativeDate")));
            }
            if (requestData.containsKey("fullName")) {
                existing.setFullName((String) requestData.get("fullName"));
            }
            if (requestData.containsKey("phoneNumber")) {
                existing.setPhoneNumber((String) requestData.get("phoneNumber"));
            }
            if (requestData.containsKey("pickupAddress")) {
                existing.setPickupAddress((String) requestData.get("pickupAddress"));
            }
            if (requestData.containsKey("deliveryAddress")) {
                existing.setDeliveryAddress((String) requestData.get("deliveryAddress"));
            }
            if (requestData.containsKey("additionalItems")) {
                existing.setAdditionalItems((String) requestData.get("additionalItems"));
            }

            Booking saved = bookingRepository.save(existing);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to update booking");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // DELETE /api/bookings/:id - delete booking
    @DeleteMapping("/api/bookings/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        try {
            if (!bookingRepository.existsById(id)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Booking not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            bookingRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Booking deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to delete booking");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}