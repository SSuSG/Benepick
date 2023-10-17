package com.ssafy.benepick.domain.user.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @DisplayName("간편 비밀번호 변경할 수 있다.")
    @Test
    void changeSimplePassword() {
        // given
        User user1 = User.builder()
                .userId("user1user1user1")
                .userName("김싸피")
                .userPhoneNumber("01012345678")
                .userSocialNumber("990101")
                .userSimplePassword("123456")
                .userGenderAndGenerationCode("1")
                .isPushActive(false)
                .isAutoLoginActive(false)
                .userCardList(new ArrayList<>())
                .build();

        String changePassword = "654321";

        // when
        user1.changeSimplePassword(changePassword);

        // then
        assertThat(user1.getUserSimplePassword()).isEqualTo(changePassword);
    }

}