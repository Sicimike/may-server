package com.sicimike.mayserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicimike.mayserver.entity.User;
import com.sicimike.mayserver.repository.UserRepository;
import com.sicimike.mayserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author sicimike
 * @create 2018-12-26 16:46
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    public static final String WEIXIN_LOGIN_API = "https://api.weixin.qq.com/sns/jscode2session";

    @Value("${weixin.appid}")
    private String appid;

    @Value("${weixin.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String code) {
        try {
            StringBuilder builder = new StringBuilder(WEIXIN_LOGIN_API);
            builder.append("?appid=").append(appid);
            builder.append("&secret=").append(secret);
            builder.append("&js_code=").append(code);
            builder.append("&grant_type=authorization_code");
            HttpGet httpGet = new HttpGet(builder.toString());

            //设置超时
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(1000)
                    .setSocketTimeout(5000)
                    .build();
            httpGet.setConfig(requestConfig);

            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity httpEntity = response.getEntity();
                String result = EntityUtils.toString(httpEntity);
                User user = JSON.parseObject(result, User.class);
                userRepository.save(user);
                return result;
            }
        } catch (IOException e) {
            log.error("network error : {}", e);
        }
        return null;
    }
}
