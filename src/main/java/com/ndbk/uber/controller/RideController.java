package com.ndbk.uber.controller;

import com.ndbk.uber.dto.CreateClientRequest;
import com.ndbk.uber.dto.CreateRideRequest;
import com.ndbk.uber.dto.UpdateClientRequest;
import com.ndbk.uber.model.Client;
import com.ndbk.uber.model.Driver;
import com.ndbk.uber.model.Ride;
import com.ndbk.uber.service.ClientService;
import com.ndbk.uber.service.RideService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/ride")
public class RideController {
  private final RideService _rideService;

  public RideController(RideService rideService){
    _rideService = rideService;
  }

  @GetMapping("{rideId}")
  public ResponseEntity<Ride> GetRide(@PathVariable int rideId){
    Optional<Ride> ride = _rideService.getRide(rideId);
    return ride.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Ride> CreateRide(@RequestBody CreateRideRequest createRideRequest){
    Ride ride = _rideService.createRide(createRideRequest);
    return ResponseEntity.ok().body(ride);
  }
}
