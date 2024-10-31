package com.baiyina.fmcommon.protocol.kyro;

import com.baiyina.fmcommon.enums.ProtocolExceptionEnum;
import com.baiyina.fmcommon.exception.FmException;
import com.baiyina.fmcommon.protocol.FmRequest;
import com.baiyina.fmcommon.protocol.FmResponse;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/30 20:39
 * @project: fm
 */
@Slf4j
public class KryoSerializer{

    private final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(FmRequest.class);
        kryo.register(FmResponse.class);
        return kryo;
    });

    public byte[] serialize(Object obj) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             Output output = new Output(byteArrayOutputStream)) {
            Kryo kryo = kryoThreadLocal.get();
            // Object->byte:将对象序列化为byte数组
            kryo.writeObject(output, obj);
            kryoThreadLocal.remove();
            return output.toBytes();
        } catch (Exception e) {
            throw new FmException(ProtocolExceptionEnum.PROTOCOL_SERIALIZE_EXCEPTION.getCode(),
                    ProtocolExceptionEnum.PROTOCOL_SERIALIZE_EXCEPTION.getMessage());
        }
    }

    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             Input input = new Input(byteArrayInputStream)) {
            Kryo kryo = kryoThreadLocal.get();
            // byte->Object:从byte数组中反序列化出对象
            Object o = kryo.readObject(input, clazz);
            kryoThreadLocal.remove();
            return clazz.cast(o);
        } catch (Exception e) {
            throw new FmException(ProtocolExceptionEnum.PROTOCOL_DESERIALIZE_EXCEPTION.getCode(),
                    ProtocolExceptionEnum.PROTOCOL_DESERIALIZE_EXCEPTION.getMessage());
        }
    }

}

