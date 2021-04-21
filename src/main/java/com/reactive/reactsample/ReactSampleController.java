package com.reactive.reactsample;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class ReactSampleController {

	private List<Foo> fooList = new ArrayList<Foo>();

	@PostConstruct
	private void init() {
		fooList.add(new Foo(1, "Test1"));
		fooList.add(new Foo(2, "Test2"));
		fooList.add(new Foo(2, "Test3"));
		fooList.add(new Foo(2, "Test4"));
		fooList.add(new Foo(2, "Test5"));

	}

	@GetMapping(value = "/load", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Foo> getSpeech() {
		return Flux.fromIterable(fooList).delayElements(Duration.ofSeconds(1)).log();
	}
}
