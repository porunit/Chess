package io;

import management.desk.SideColor;

import java.io.Serializable;

public record SidePayload(
        SideColor side
) implements Serializable {
}
