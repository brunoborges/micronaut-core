/*
 * Copyright 2017 original authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.particleframework.http.codec;

import org.particleframework.core.io.buffer.ByteBuffer;
import org.particleframework.core.io.buffer.ByteBufferFactory;
import org.particleframework.core.type.Argument;
import org.particleframework.http.MediaType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Represents a codec for a particular media type. For example JSON.
 *
 * @author Graeme Rocher
 * @since 1.0
 */
public interface MediaTypeCodec {

    /**
     * @return The media type of the codec
     */
    MediaType getMediaType();

    /**
     * Decode the given type from the given {@link InputStream}
     *
     * @param type The type
     * @param inputStream The input stream
     * @param <T> The generic type
     * @return The decoded result
     * @throws CodecException When the result cannot be decoded
     */
    <T> T decode(Argument<T> type, InputStream inputStream) throws CodecException;

    /**
     * Encode the given type from the given {@link InputStream}
     *
     * @param object The object to encode
     * @param outputStream The input stream
     * @param <T> The generic type
     * @return The decoded result
     * @throws CodecException When the result cannot be decoded
     */
    <T> void encode(T object, OutputStream outputStream) throws CodecException;

    /**
     * Encode the given type returning the object as a byte[]
     *
     * @param object The object to encode
     * @param <T> The generic type
     * @return The decoded result
     * @throws CodecException When the result cannot be decoded
     */
    <T> byte[] encode(T object) throws CodecException;

    /**
     * Encode the given type returning the object as a {@link ByteBuffer}
     *
     * @param object The object to encode
     * @param allocator The allocator
     * @param <T> The generic type
     * @return The decoded result
     * @throws CodecException When the result cannot be decoded
     */
    <T> ByteBuffer encode(T object, ByteBufferFactory allocator) throws CodecException;

    /**
     * Decode the given type from the given {@link InputStream}
     *
     * @param type The type
     * @param inputStream The input stream
     * @param <T> The generic type
     * @return The decoded result
     * @throws CodecException When the result cannot be decoded
     */
    default <T> T decode(Class<T> type, InputStream inputStream) throws CodecException {
        return decode(Argument.of(type), inputStream);
    }

    /**
     * Decode the given type from the given bytes
     *
     * @param type The type
     * @param bytes The bytes
     * @param <T> The decoded type
     * @return The decoded result
     *
     * @throws CodecException When the result cannot be decoded
     */
    default <T> T decode(Class<T> type, byte[] bytes) throws CodecException {
        return decode(type, new ByteArrayInputStream(bytes));
    }

    /**
     * Decode the given type from the given bytes
     *
     * @param type The type
     * @param bytes The bytes
     * @param <T> The decoded type
     * @return The decoded result
     *
     * @throws CodecException When the result cannot be decoded
     */
    default <T> T decode(Argument<T> type, byte[] bytes) throws CodecException {
        return decode(type, new ByteArrayInputStream(bytes));
    }

    /**
     * Decode the given type from the given buffer. Implementations optimized to handle {@link ByteBuffer} instances should override this method.
     *
     * @param type The type
     * @param buffer the buffer
     * @param <T> The decoded type
     * @return The decoded result
     *
     * @throws CodecException When the result cannot be decoded
     */
    default <T> T decode(Class<T> type, ByteBuffer<?> buffer) throws CodecException {
        return decode(type, buffer.toInputStream());
    }

    /**
     * Decode the given type from the given buffer. Implementations optimized to handle {@link ByteBuffer} instances should override this method.
     *
     * @param type The type
     * @param buffer the buffer
     * @param <T> The decoded type
     * @return The decoded result
     *
     * @throws CodecException When the result cannot be decoded
     */
    default <T> T decode(Argument<T> type, ByteBuffer<?> buffer) throws CodecException {
        return decode(type, buffer.toInputStream());
    }

    /**
     * Decode the given type from the given bytes
     *
     * @param type The type
     * @param data The data as a string
     * @param <T> The decoded type
     * @return The decoded result
     *
     * @throws CodecException When the result cannot be decoded
     */
    default <T> T decode(Class<T> type, String data) throws CodecException {
        return decode(type, new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Decode the given type from the given bytes
     *
     * @param type The type
     * @param data The data as a string
     * @param <T> The decoded type
     * @return The decoded result
     *
     * @throws CodecException When the result cannot be decoded
     */
    default <T> T decode(Argument<T> type, String data) throws CodecException {
        return decode(type, new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Whether the codec can decode the given type
     * @param type The type
     * @return True if it can
     */
    default boolean supportsType(Class<?> type) {
        return true;
    }

}

