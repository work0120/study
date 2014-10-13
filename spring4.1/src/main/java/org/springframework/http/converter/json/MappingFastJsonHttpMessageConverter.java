package org.springframework.http.converter.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * spring mvc 与 fastjson结合
 */
public class MappingFastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    public static final Charset UTF8=Charset.forName("UTF-8");

    private Charset charset=UTF8;

    private SerializerFeature[] serializerFeature;

    @Override
    protected boolean supports(Class aClass) {
        return true;
    }

    @Override
    protected Object readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        InputStream in = httpInputMessage.getBody();

        byte[] buf = new byte[1024];
        for (;;) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }

            if (len > 0) {
                baos.write(buf, 0, len);
            }
        }

        byte[] bytes = baos.toByteArray();
        if (charset == UTF8) {
            return JSON.parseObject(bytes, aClass);
        } else {
            return JSON.parseObject(bytes, 0, bytes.length, charset.newDecoder(), aClass);
        }
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

        OutputStream out = httpOutputMessage.getBody();
        byte[] bytes;

        if (charset == UTF8) {
            if (serializerFeature != null) {
                bytes = JSON.toJSONBytes(o, serializerFeature);
            } else {
                bytes = JSON.toJSONBytes(o, SerializerFeature.WriteDateUseDateFormat);
            }

        } else {
            String text;
            if (serializerFeature != null) {
                text = JSON.toJSONString(o, serializerFeature);
            } else {
                text = JSON.toJSONString(o, SerializerFeature.WriteDateUseDateFormat);
            }
            bytes = text.getBytes(charset);
        }

        out.write(bytes);
    }
}
