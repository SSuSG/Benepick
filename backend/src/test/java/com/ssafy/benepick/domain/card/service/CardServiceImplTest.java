package com.ssafy.benepick.domain.card.service;

import com.ssafy.benepick.domain.card.dto.response.BenefitSearchResponseDto;
import com.ssafy.benepick.domain.user.entity.*;
import com.ssafy.benepick.domain.user.repository.UserCardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;


@Transactional
@SpringBootTest
public class CardServiceImplTest {

    @MockBean
    private UserCardRepository userCardRepository;

    @DisplayName("카드ID 바탕으로 카드 혜택을 조회할 수 있다.")
    @Test
    void 카드_혜택_조회() {
        // given
        UserCard userCard = createUserCard();
        given(userCardRepository.findById(1L))
                .willReturn(Optional.of(userCard));

        // when
        UserCard saveUserCard = userCardRepository.findById(1L).get();

        // then
        assertThat(saveUserCard)
                .extracting("userCardName")
                .isEqualTo("카드");

        assertThat(saveUserCard.getUserCardCategory1List())
                .extracting(UserCardCategory1::getUserCardCategory1Name)
                .contains("카테고리1");

        assertThat(saveUserCard.getUserCardCategory1List().get(0).getUserCardBenefitList())
                .extracting(UserCardBenefit::getUserCardBenefitDiscountPercent)
                .contains(10);

    }

    @DisplayName("키워드 기반으로 내 카드 혜택을 검색할 수 있다.")
    @Test
    void 키워드_기반_카드_혜택_검색() {
        // given
        String keyword = "스타벅스";
        UserCard userCard = createUserCard();

        // when
        UserCardCategory3 userCardCategory3 =
                userCard.getUserCardCategory1List()
                        .get(0).getUserCardCategory2List()
                        .get(0).getUserCardCategory3List()
                        .get(0);

        // then
        assertThat(userCardCategory3)
                .extracting(UserCardCategory3::getUserCardCategory3Name)
                .isEqualTo(keyword);
    }




    private UserCard createUserCard(){
        UserCardCategory3 userCardCategory3 = UserCardCategory3.builder()
                .userCardCategory3Name("스타벅스")
                .build();

        List<UserCardCategory3> userCardCategory3List = new ArrayList<>();
        userCardCategory3List.add(userCardCategory3);

        UserCardCategory2 userCardCategory2 = UserCardCategory2.builder()
                .userCardCategory3List(userCardCategory3List)
                .build();

        List<UserCardCategory2> userCardCategory2List = new ArrayList<>();
        userCardCategory2List.add(userCardCategory2);

        UserCardBenefit userCardBenefit = UserCardBenefit.builder()
                .cardBenefitPerformanceLevel(1L)
                .userCardBenefitDiscountPercent(10)
                .userCardBenefitPerformanceStart(0)
                .userCardBenefitPerformanceEnd(10000)
                .build();

        List<UserCardBenefit> userCardBenefitList = new ArrayList<>();
        userCardBenefitList.add(userCardBenefit);

        UserCardCategory1 userCardCategory1 = UserCardCategory1.builder()
                .userCardCategory1Name("카테고리1")
                .userCardCategory2List(userCardCategory2List)
                .userCardBenefitList(userCardBenefitList)
                .build();

        List<UserCardCategory1> userCardCategory1List = new ArrayList<>();
        userCardCategory1List.add(userCardCategory1);

        return UserCard.builder()
                .userCardCompanyName("test1")
                .userCardName("카드")
                .userCardCategory1List(userCardCategory1List)
                .build();

    }

}
