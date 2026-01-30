package org.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    public enum Status {
        NEW,        // Just submitted, not yet reviewed
        QUOTED,     // Quote sent to customer
        CONFIRMED,  // Customer agreed, booking confirmed
        COMPLETED,  // Moving job completed
        CANCELLED   // Cancelled
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Company name is optional for single-company use
    private String companyName;

    @Column(nullable = false)
    private String language;

    @Column(columnDefinition = "TEXT")
    private String items;

    @Column(columnDefinition = "TEXT")
    private String additionalItems;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String pickupAddress;

    @Column(nullable = false)
    private String pickupZipcode;

    @Column(nullable = false)
    private String pickupAccess;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(nullable = false)
    private String deliveryZipcode;

    @Column(nullable = false)
    private String deliveryAccess;

    @Column(nullable = false)
    private LocalDate tentativeDate;

    private String tentativeTime;

    @Column(nullable = false)
    private String adSource;

    // New fields for workflow management
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.NEW;

    private Double quoteAmount;

    @Column(columnDefinition = "TEXT")
    private String internalNotes;

    private LocalDate confirmedDate;
    
    private String confirmedTime;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Constructors
    public Booking() {
        this.createdAt = LocalDateTime.now();
        this.status = Status.NEW;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getItems() { return items; }
    public void setItems(String items) { this.items = items; }

    public String getAdditionalItems() { return additionalItems; }
    public void setAdditionalItems(String additionalItems) { this.additionalItems = additionalItems; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPickupAddress() { return pickupAddress; }
    public void setPickupAddress(String pickupAddress) { this.pickupAddress = pickupAddress; }

    public String getPickupZipcode() { return pickupZipcode; }
    public void setPickupZipcode(String pickupZipcode) { this.pickupZipcode = pickupZipcode; }

    public String getPickupAccess() { return pickupAccess; }
    public void setPickupAccess(String pickupAccess) { this.pickupAccess = pickupAccess; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public String getDeliveryZipcode() { return deliveryZipcode; }
    public void setDeliveryZipcode(String deliveryZipcode) { this.deliveryZipcode = deliveryZipcode; }

    public String getDeliveryAccess() { return deliveryAccess; }
    public void setDeliveryAccess(String deliveryAccess) { this.deliveryAccess = deliveryAccess; }

    public LocalDate getTentativeDate() { return tentativeDate; }
    public void setTentativeDate(LocalDate tentativeDate) { this.tentativeDate = tentativeDate; }

    public String getTentativeTime() { return tentativeTime; }
    public void setTentativeTime(String tentativeTime) { this.tentativeTime = tentativeTime; }

    public String getAdSource() { return adSource; }
    public void setAdSource(String adSource) { this.adSource = adSource; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Double getQuoteAmount() { return quoteAmount; }
    public void setQuoteAmount(Double quoteAmount) { this.quoteAmount = quoteAmount; }

    public String getInternalNotes() { return internalNotes; }
    public void setInternalNotes(String internalNotes) { this.internalNotes = internalNotes; }

    public LocalDate getConfirmedDate() { return confirmedDate; }
    public void setConfirmedDate(LocalDate confirmedDate) { this.confirmedDate = confirmedDate; }

    public String getConfirmedTime() { return confirmedTime; }
    public void setConfirmedTime(String confirmedTime) { this.confirmedTime = confirmedTime; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // Helper method to get the effective moving date (confirmed or tentative)
    public LocalDate getMovingDate() {
        return confirmedDate != null ? confirmedDate : tentativeDate;
    }

    public String getMovingTime() {
        return confirmedTime != null ? confirmedTime : tentativeTime;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
