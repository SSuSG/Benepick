package com.ssafy.benepick.domain.card.service;

import com.ssafy.benepick.domain.user.entity.User;
import com.ssafy.benepick.domain.user.entity.UserCard;
import com.ssafy.benepick.domain.user.entity.UserCardCompany;
import com.ssafy.benepick.domain.user.repository.UserCardCompanyRepository;
import com.ssafy.benepick.domain.user.repository.UserCardRepository;
import com.ssafy.benepick.domain.user.repository.UserRepository;
import com.ssafy.benepick.global.api.dto.response.ApiCardCompanyResponseDto;
import com.ssafy.benepick.global.api.service.ApiService;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@Transactional
@SpringBootTest
class CardCompanyServiceImplTest {

    @MockBean
    private ApiService apiService;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("전체 카드사를 조회한다. ")
    @Test
    void 전체_카드사_조회() {
        // given
        List<ApiCardCompanyResponseDto> mockResponse = new ArrayList<>();
        mockResponse.add(CreateApiCardCompanyResponseDto("test1"));
        mockResponse.add(CreateApiCardCompanyResponseDto("test2"));

        given(apiService.getCardCompanyListFromMyDataServer())
                .willReturn(mockResponse);

        // when
        List<ApiCardCompanyResponseDto> result = apiService.getCardCompanyListFromMyDataServer();

        // then
        assertThat(result).hasSize(2)
                .extracting("cardCompanyId","cardCompanyImgUrl","cardCompanyName")
                .containsExactlyInAnyOrder(
                        tuple(1L,"img","test1"),
                        tuple(1L,"img","test2")
                );
    }

    @DisplayName("카드사 연동시 카드사가 연동이 되어있는 상태면 연동 유효기간이 갱신된다.")
    @Test
    void 카드사_유효기간_갱신() {
        // given
        User user1 = createUser();

        ApiCardCompanyResponseDto company1 = ApiCardCompanyResponseDto.builder()
                .cardCompanyId(1L)
                .cardCompanyName("자바")
                .cardCompanyImgUrl("img")
                .build();

        user1.linkCardCompany(company1);
        User saveUser = userRepository.save(user1);

        // when
        if(saveUser.getUserCardCompanyList().get(0).getCardCompanyId().equals(1L))
            saveUser.getUserCardCompanyList().get(0).renewDate();

        // then
        assertThat(saveUser.getUserCardCompanyList().get(0).getUserCardCompanyExpirationDate())
                .isNotEqualTo(LocalDateTime.of(2023, 3, 1,0,0,0));

    }

    @DisplayName("사용자는 새로운 카드사를 연동한다.")
    @Test
    void 카드사_연동() {
        // given
        User user1 = createUser();

        ApiCardCompanyResponseDto company1 = ApiCardCompanyResponseDto.builder()
                .cardCompanyId(1L)
                .cardCompanyName("자바")
                .cardCompanyImgUrl("img")
                .build();

        // when
        user1.linkCardCompany(company1);
        User saveUser = userRepository.save(user1);

        // then
        assertThat(saveUser.getUserCardCompanyList()).hasSize(1)
                .extracting("cardCompanyId","userCardCompanyName","userCardCompanyImgUrl")
                .containsExactlyInAnyOrder(
                        tuple(1L,"자바","img")
                );

    }

    @DisplayName("카드사 연동을 해제 할 수 있다.")
    @Test
    void 카드사_연동_해제(){
        // given
        User user1 = createUser();
        ApiCardCompanyResponseDto company1 = CreateApiCardCompanyResponseDto("test1");
        ApiCardCompanyResponseDto company2 = CreateApiCardCompanyResponseDto("test2");
        user1.linkCardCompany(company1);
        user1.linkCardCompany(company2);
        User save = userRepository.save(user1);
        int linkCardCompanyCount = save.getUserCardCompanyList().size();

        // when
        save.cancelLinkCardCompany(save.getUserCardCompanyList().get(0));

        // then
        assertThat(linkCardCompanyCount-1).isEqualTo(save.getUserCardCompanyList().size());
    }

    private ApiCardCompanyResponseDto CreateApiCardCompanyResponseDto(String cardCompanyName){
        return ApiCardCompanyResponseDto.builder()
                .cardCompanyId(1l)
                .cardCompanyImgUrl("img")
                .cardCompanyName(cardCompanyName)
                .build();
    }

    private User createUser(){
        return User.builder()
                .userId("user1")
                .userName("김싸피")
                .userPhoneNumber("01012345678")
                .userSocialNumber("990101")
                .userSimplePassword("123456")
                .userGenderAndGenerationCode("1")
                .isPushActive(false)
                .isAutoLoginActive(false)
                .userCardCompanyList(new ArrayList<>())
                .build();

    }
}