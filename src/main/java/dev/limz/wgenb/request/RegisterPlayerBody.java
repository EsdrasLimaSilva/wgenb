package dev.limz.wgenb.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterPlayerBody(
        @NotEmpty String id
) {
}
