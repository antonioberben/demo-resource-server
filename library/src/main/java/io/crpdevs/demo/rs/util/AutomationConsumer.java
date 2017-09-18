package io.crpdevs.demo.rs.util;

import static java.util.Objects.requireNonNull;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Automation consumer to facilitate automation function chaining.
 * @param <I> the param
 */
@FunctionalInterface
public interface AutomationConsumer<I> extends Consumer<I> {

    /**
     *
     * @return consumer
     */
    default Consumer<I> wrap() {
        return input -> {
            accept(input);
        };
    }

    default AutomationConsumer<I> then(Consumer<I> after) {
        requireNonNull(after);
        return (I t) -> { accept(t); after.accept(t); };
    }

    default <O> Function<I, O> then(Function<I, O> after) {
        requireNonNull(after);
        return (I t) -> { wrap().accept(t); return after.apply(t); };
    }
}
