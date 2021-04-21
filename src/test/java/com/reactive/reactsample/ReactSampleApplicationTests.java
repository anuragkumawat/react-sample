package com.reactive.reactsample;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ReactSampleController.class)
class ReactSampleApplicationTests {

	@Autowired
    private WebTestClient webTestClient;
	
	
    @Test
    public void trueAssumption_statusOk() {
        webTestClient.get()
        .uri("/load")
        .exchange()
        .expectStatus().isOk();
    }
    
    @Test
    public void trueAssumption_validResponseType() {
    	webTestClient.get()
    	.uri("/load")
		.exchange()
		.expectStatus().isOk()
    	.expectBodyList(Foo.class);
    }
    
    @Test
    public void trueAssumption_validResponse() {

    	EntityExchangeResult<List<Foo>> result = webTestClient.get()
    		.uri("/load")
    		.exchange()
    		.expectBodyList(Foo.class).returnResult();

    	assertThat(result.getResponseBody().size()).isGreaterThan(0);    	
    }
        
					        
}
