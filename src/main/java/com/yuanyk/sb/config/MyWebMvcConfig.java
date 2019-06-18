package com.yuanyk.sb.config;

import com.yuanyk.sb.messageConvert.MyMsgConvert;
import com.yuanyk.sb.pojo.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

import java.util.List;

@Configuration
public class MyWebMvcConfig extends DelegatingWebMvcConfiguration {
    /**
     * 扩展
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        HttpMessageConverter<Person> converter = new MyMsgConvert();
        converters.add(0, converter);
    }
}
