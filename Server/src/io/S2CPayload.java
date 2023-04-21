package io;

import management.desk.SideColor;

import java.io.Serializable;

public record S2CPayload(
        String message,
        String board,
        SideColor sideToSet
) implements Serializable {
}
