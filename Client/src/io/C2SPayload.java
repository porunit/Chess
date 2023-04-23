package io;

import management.control.Position;

import java.io.Serializable;

public record C2SPayload(
        Position position
) implements Serializable {
}
