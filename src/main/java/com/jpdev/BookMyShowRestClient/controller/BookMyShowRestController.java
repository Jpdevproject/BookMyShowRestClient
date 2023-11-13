package com.jpdev.BookMyShowRestClient.controller;

import com.jpdev.BookMyShowRestClient.dto.BookingDto;
import com.jpdev.BookMyShowRestClient.entity.BookingDetails;
import com.jpdev.BookMyShowRestClient.service.BookingRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/client/booking")
public class BookMyShowRestController {
    @Autowired
    private BookingRestClientService service;
    @PostMapping
    public BookingDetails book(@RequestBody BookingDto bookingDto){
       return  service.bookTickets(bookingDto).orElse(null) ;
    }

    @GetMapping("/{id}")
    public BookingDto getBookingById(@PathVariable Long id){
        return  service.getBookingById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public BookingDetails updateTicket(@RequestBody BookingDto bookingDto, @PathVariable Long id){
        return service.updateTickets(bookingDto,id).orElse(null);

    }

    @GetMapping
    public List<BookingDto> getAllTicket(){
        return service.getAllBookings()
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public String cancelBookingById(@PathVariable Long id){
        return service.cancelBooking(id)
                .orElse(null);
    }
}
