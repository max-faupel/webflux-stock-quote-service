package com.example.webfluxstockquoteservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.webfluxstockquoteservice.model.Quote;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebfluxStockQuoteServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testFetchQuotes() {
		webTestClient.get().uri("/quotes?size=20").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectBodyList(Quote.class).hasSize(20)
				.consumeWith(allQuotes -> {
					assertThat(allQuotes.getResponseBody())
							.allSatisfy(quote -> assertThat(quote.getPrice()).isPositive());
							assertThat(allQuotes.getResponseBody()).hasSize(20);
				});
	}

}
