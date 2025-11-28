package org.example.repository;

import org.example.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCompanyName(String companyName);

    @Query("SELECT b FROM Booking b WHERE b.companyName = :companyName " +
           "AND b.tentativeDate >= :from AND b.tentativeDate <= :to " +
           "ORDER BY b.tentativeDate ASC")
    List<Booking> findByCompanyNameAndDateRange(
            @Param("companyName") String companyName,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

    List<Booking> findByTentativeDate(LocalDate date);

    @Query("SELECT b FROM Booking b ORDER BY b.createdAt DESC")
    List<Booking> findAllOrderByCreatedAtDesc();
}