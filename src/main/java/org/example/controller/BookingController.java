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

            // Company name is optional now - use default if not provided
            String companyName = (String) requestData.get("companyName");
            booking.setCompanyName(companyName != null && !companyName.isEmpty() ? companyName : "default");
            
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
            booking.setStatus(Booking.Status.NEW);

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

    // GET /api/bookings - fetch ALL bookings with optional date filters
    @GetMapping("/api/bookings")
    @ResponseBody
    public ResponseEntity<List<Booking>> getBookings(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String status
    ) {
        List<Booking> bookings;

        if (from != null && to != null) {
            bookings = bookingRepository.findByDateRange(from, to);
        } else {
            bookings = bookingRepository.findAllOrderByCreatedAtDesc();
        }

        // Filter by status if provided
        if (status != null && !status.isEmpty()) {
            Booking.Status statusEnum = Booking.Status.valueOf(status.toUpperCase());
            bookings = bookings.stream()
                    .filter(b -> b.getStatus() == statusEnum)
                    .toList();
        }

        return ResponseEntity.ok(bookings);
    }

    // GET /api/bookings/:id - get single booking
    @GetMapping("/api/bookings/{id}")
    @ResponseBody
    public ResponseEntity<?> getBooking(@PathVariable Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Booking not found"));
        }
        return ResponseEntity.ok(booking.get());
    }

    // PUT /api/bookings/:id - update booking (full update for admin)
    @PutMapping("/api/bookings/{id}")
    @ResponseBody
    public ResponseEntity<?> updateBooking(
            @PathVariable Long id,
            @RequestBody Map<String, Object> requestData
    ) {
        try {
            Optional<Booking> existingOpt = bookingRepository.findById(id);

            if (existingOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Booking not found"));
            }

            Booking existing = existingOpt.get();

            // Update basic fields
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
            if (requestData.containsKey("tentativeDate")) {
                existing.setTentativeDate(LocalDate.parse((String) requestData.get("tentativeDate")));
            }
            if (requestData.containsKey("tentativeTime")) {
                existing.setTentativeTime((String) requestData.get("tentativeTime"));
            }
            if (requestData.containsKey("additionalItems")) {
                existing.setAdditionalItems((String) requestData.get("additionalItems"));
            }

            // Update workflow fields
            if (requestData.containsKey("status")) {
                existing.setStatus(Booking.Status.valueOf((String) requestData.get("status")));
            }
            if (requestData.containsKey("quoteAmount")) {
                Object quoteObj = requestData.get("quoteAmount");
                if (quoteObj != null) {
                    existing.setQuoteAmount(Double.valueOf(quoteObj.toString()));
                }
            }
            if (requestData.containsKey("internalNotes")) {
                existing.setInternalNotes((String) requestData.get("internalNotes"));
            }
            if (requestData.containsKey("confirmedDate")) {
                String dateStr = (String) requestData.get("confirmedDate");
                existing.setConfirmedDate(dateStr != null && !dateStr.isEmpty() ? LocalDate.parse(dateStr) : null);
            }
            if (requestData.containsKey("confirmedTime")) {
                existing.setConfirmedTime((String) requestData.get("confirmedTime"));
            }

            Booking saved = bookingRepository.save(existing);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Failed to update booking", "message", e.getMessage()));
        }
    }

    // PATCH /api/bookings/:id/status - quick status update
    @PatchMapping("/api/bookings/{id}/status")
    @ResponseBody
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        try {
            Optional<Booking> existingOpt = bookingRepository.findById(id);
            if (existingOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Booking not found"));
            }

            Booking existing = existingOpt.get();
            existing.setStatus(Booking.Status.valueOf(body.get("status")));
            
            Booking saved = bookingRepository.save(existing);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Failed to update status", "message", e.getMessage()));
        }
    }

    // PATCH /api/bookings/:id/quote - set quote amount
    @PatchMapping("/api/bookings/{id}/quote")
    @ResponseBody
    public ResponseEntity<?> setQuote(
            @PathVariable Long id,
            @RequestBody Map<String, Object> body
    ) {
        try {
            Optional<Booking> existingOpt = bookingRepository.findById(id);
            if (existingOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Booking not found"));
            }

            Booking existing = existingOpt.get();
            existing.setQuoteAmount(Double.valueOf(body.get("quoteAmount").toString()));
            existing.setStatus(Booking.Status.QUOTED);
            
            Booking saved = bookingRepository.save(existing);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Failed to set quote", "message", e.getMessage()));
        }
    }

    // PATCH /api/bookings/:id/confirm - confirm booking with final date/time
    @PatchMapping("/api/bookings/{id}/confirm")
    @ResponseBody
    public ResponseEntity<?> confirmBooking(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        try {
            Optional<Booking> existingOpt = bookingRepository.findById(id);
            if (existingOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Booking not found"));
            }

            Booking existing = existingOpt.get();
            
            String confirmedDate = body.get("confirmedDate");
            if (confirmedDate != null && !confirmedDate.isEmpty()) {
                existing.setConfirmedDate(LocalDate.parse(confirmedDate));
            } else {
                existing.setConfirmedDate(existing.getTentativeDate());
            }
            
            String confirmedTime = body.get("confirmedTime");
            if (confirmedTime != null && !confirmedTime.isEmpty()) {
                existing.setConfirmedTime(confirmedTime);
            } else {
                existing.setConfirmedTime(existing.getTentativeTime());
            }
            
            existing.setStatus(Booking.Status.CONFIRMED);
            
            Booking saved = bookingRepository.save(existing);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Failed to confirm booking", "message", e.getMessage()));
        }
    }

    // DELETE /api/bookings/:id - delete booking
    @DeleteMapping("/api/bookings/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        try {
            if (!bookingRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Booking not found"));
            }

            bookingRepository.deleteById(id);
            return ResponseEntity.ok(Map.of("message", "Booking deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Failed to delete booking", "message", e.getMessage()));
        }
    }
}
