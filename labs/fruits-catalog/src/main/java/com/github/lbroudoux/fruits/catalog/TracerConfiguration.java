package com.github.lbroudoux.fruits.catalog;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.reporters.InMemoryReporter;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.jaegertracing.spi.Reporter;
import io.jaegertracing.spi.Sampler;
import io.opentracing.Tracer;

/**
 * A configuration bean for enabling distributed tracing with Jaeger.
 * @author laurent
 */
@ConditionalOnProperty(value = "opentracing.jaeger.enabled", havingValue = "false", matchIfMissing = false)
@Configuration
public class TracerConfiguration {

    @Bean
    public Tracer jaegerTracer() {
        final Reporter reporter = new InMemoryReporter();
        final Sampler sampler = new ConstSampler(false);
        return new JaegerTracer.Builder("fruits-catalog-api")
                .withReporter(reporter)
                .withSampler(sampler)
                .build();
    }
}
