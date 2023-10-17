package com.ssafy.benepick.global.util;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@Transactional
@SpringBootTest
class SmsServiceTest {

    @MockBean
    private SmsService smsService;

    @DisplayName("휴대폰번호로 SMS 문자를 발송할 수 있다.")
    @Test
    void sendSMS() throws CoolsmsException {
        // given
        String authenticationKey = "123456";
        given(smsService.sendAuthKey(anyString()))
                .willReturn(authenticationKey);

        // when
        String authKey = smsService.sendAuthKey("");

        // then
        assertThat(authKey).isEqualTo(authenticationKey);
    }
}