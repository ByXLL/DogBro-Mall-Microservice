package cn.byxll.oauth.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import entity.Result;
import entity.StatusCode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 自定义权限异常数据序列化器
 * @author By-Lin
 */

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //可以不用下面的代码，只用 jsonGenerator.writeRawValue(myJsonString)  输出自定义字符串
        jsonGenerator.writeStartObject();
        jsonGenerator.writeBooleanField("flag", false);
        jsonGenerator.writeNumberField("code", StatusCode.ACCESSERROR);
        jsonGenerator.writeObjectField("data", null);
        jsonGenerator.writeObjectField("message", value.getHttpErrorCode() +  Arrays.asList(value.getOAuth2ErrorCode(),value.getMessage()).toString());
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jsonGenerator.writeStringField(key, add);
            }
        }
        jsonGenerator.writeEndObject();
    }
}
