package com.example.webfluxstockquoteservice.service;

import java.time.Duration;

import com.example.webfluxstockquoteservice.model.Quote;

import reactor.core.publisher.Flux;

public interface QuoteGeneratorService {
    Flux<Quote> fetchQuoteStream(Duration period);
    
}
