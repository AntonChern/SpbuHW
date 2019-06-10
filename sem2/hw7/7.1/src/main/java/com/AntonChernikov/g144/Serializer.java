package com.AntonChernikov.g144;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** Interface describing work with serializer */
public interface Serializer {

    /** Method converting ADT to stream */
    void serialize(OutputStream out) throws IOException;

    /** Method creating ADT from stream */
    void deserialize(InputStream in) throws IOException;
}
