package com.reactive.TestPublisher;

import org.junit.Test;
import reactor.test.StepVerifier;

public class TestPublisher {

    final reactor.test.publisher.TestPublisher<String> testPublisher = reactor.test.publisher.TestPublisher.create();

    @Test
    public void testUpperCase() {
        UppercaseConverter uppercaseConverter = new UppercaseConverter(testPublisher.flux());
        StepVerifier.create(uppercaseConverter.getUpperCase())
                .then(() -> testPublisher.emit("datos", "GeNeRaDoS", "Sofka"))
                .expectNext("DATOS", "GENERADOS", "SOFKA")
                .verifyComplete();
        reactor.test.publisher.TestPublisher
                .createNoncompliant(reactor.test.publisher.TestPublisher.Violation.ALLOW_NULL)
                .emit("1", "2", null, "3");
    }

}