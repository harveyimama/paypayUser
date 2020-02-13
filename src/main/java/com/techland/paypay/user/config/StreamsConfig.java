package com.techland.paypay.user.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.techland.paypay.user.contracts.UserMessage;

@EnableBinding(UserMessage.class)
public class StreamsConfig {

}
