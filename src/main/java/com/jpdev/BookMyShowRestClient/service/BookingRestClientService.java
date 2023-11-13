package com.jpdev.BookMyShowRestClient.service;

import com.jpdev.BookMyShowRestClient.dto.BookingDto;
import com.jpdev.BookMyShowRestClient.entity.BookingDetails;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class BookingRestClientService {

    private RestClient restClient;

    public BookingRestClientService(){
        restClient= RestClient.builder()
                .baseUrl("http://localhost:9001/rest/api/booking")
                .build();

    }

    public Optional<BookingDetails> bookTickets(BookingDto bookingDto){
      return Optional.ofNullable(restClient.post()
              .contentType(MediaType.APPLICATION_JSON)
              .body(bookingDto)
              .retrieve()
              .body(BookingDetails.class));
    }

    public Optional<BookingDto> getBookingById(Long id){
        return Optional.ofNullable(restClient.get()
                .uri("/{id}",id)
                .retrieve()
                .body(BookingDto.class));
    }

    public Optional<BookingDetails> updateTickets(BookingDto bookingDto, Long id) {
        return Optional.ofNullable(restClient.put()
                .uri("/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BookingDto.class)
                .retrieve()
                .body(BookingDetails.class));
    }

    public Optional<List<BookingDto>> getAllBookings() {
        return Optional.ofNullable(restClient.get()
                .retrieve()
                .body(new ParameterizedTypeReference<List<BookingDto>>() {}));

    }

    public Optional<String> cancelBooking(Long id) {
        restClient.delete()
                .uri("/{id}",id)
                .retrieve();
        return Optional.of("Booking is cancelled for id "+id);
    }
}
