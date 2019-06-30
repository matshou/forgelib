package io.yooksi.forgelib.error;

import io.yooksi.forgelib.ForgeMod;
import io.yooksi.forgelib.meta.Metadata;

/**
 * Thrown to indicate that {@code ForgeMod} {@link Metadata} contains a malformed value.
 */
public class MalformedMetadataException extends IllegalModStateException {

    public MalformedMetadataException(ForgeMod mod, Metadata meta) {
        super(mod, String.format("has a malformed metadata definition %s.", meta.toString()));
    }
}
