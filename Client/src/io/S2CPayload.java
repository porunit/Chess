package io;

import management.network.PayloadType;

import java.io.Serializable;

public record S2CPayload(
        String data,
        PayloadType type
) implements Serializable {
}
