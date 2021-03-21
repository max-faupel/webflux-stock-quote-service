package com.example.webfluxstockquoteservice.service;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

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
    public void fetchQuoteStreamCountDownTest() throws InterruptedException {
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100L));
        Consumer<Quote> printConsumer = System.out::println;
        Consumer<Throwable> errorConsumer = e -> System.out.println("Error");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runnable allDone = () -> countDownLatch.countDown();
        quoteFlux.subscribe(printConsumer, errorConsumer, allDone);
        countDownLatch.await();
    }
    
}
