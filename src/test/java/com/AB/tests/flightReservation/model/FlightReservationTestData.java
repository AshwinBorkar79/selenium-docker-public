package com.AB.tests.flightReservation.model;

public record FlightReservationTestData(String firstName,
                                        String lastName,
                                        String email,
                                        String password,
                                        String street,
                                        String city,
                                        String zip,
                                        String passengerCount,
                                        String expectedPrice
                                        )
{}
