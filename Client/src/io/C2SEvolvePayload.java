package io;

import management.control.FigureToEvolve;

import java.io.Serializable;

public record C2SEvolvePayload(
        FigureToEvolve figure
) implements Serializable {
}
