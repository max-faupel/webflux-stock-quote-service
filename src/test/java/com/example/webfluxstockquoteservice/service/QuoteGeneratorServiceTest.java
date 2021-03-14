package com.example.webfluxstockquoteservice.service;

import java.time.Duration;

import com.example.webfluxstockquoteservice.model.Quote;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class QuoteGeneratorServiceTest {
    QuoteGeneratorServiceImpl quoteGeneratorService = new QuoteGeneratorServiceImpl();

    @Test
    public void fetchQuoteStreamTest() {
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(1L));
        quoteFlux.take(22000).subscribe(System.out::println);
    }

    @Test
    public void fetchQuoteStreamCountDownTest() {
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(1L));
        quoteFlux.take(22000).subscribe(System.out::println);
    }
    
}
