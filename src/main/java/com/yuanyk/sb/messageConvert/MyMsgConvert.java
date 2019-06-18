package com.yuanyk.sb.messageConvert;

import com.yuanyk.sb.pojo.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Properties;

public class MyMsgConvert extends AbstractHttpMessageConverter<Person> {
    public MyMsgConvert() {
        // 设置支持的mediaType 和默认编码
        super.setSupportedMediaTypes(Arrays.asList(MediaType.valueOf("application/properties")));
        super.setDefaultCharset(Charset.forName("UTF-8"));
    }

    @Override
    protected boolean supports(Class aClass) {
        return aClass.isAssignableFrom(Person.class);
    }

    @Override
    protected Person readInternal(Class<? extends Person> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        // 从请求体里获取参数 封装成person对象
        InputStream is = httpInputMessage.getBody();
        Properties properties = new Properties();
        properties.load(new InputStreamReader(is, getDefaultCharset()));
        Person p = new Person();
        p.setName(properties.getProperty("person.name"));
        p.setId(Integer.valueOf(properties.getProperty("person.id")));
        return p;
    }

    @Override
    protected void writeInternal(Person person, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        // 将person对象写到响应体中
        OutputStream os = httpOutputMessage.getBody();
        Properties properties = new Properties();
        properties.setProperty("person.id", person.getId()+"");
        properties.setProperty("person.name", person.getName());
        properties.store(new OutputStreamWriter(os,getDefaultCharset()),"i am comments");
    }
}
