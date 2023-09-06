package com.ssafy.benepick.domain.card.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.benepick.domain.card.dto.response.CardCompanyResponseDto;
import com.ssafy.benepick.domain.card.entity.CardCompany;
import com.ssafy.benepick.domain.card.repository.CardCompanyRepository;
import com.ssafy.benepick.domain.user.entity.User;
import com.ssafy.benepick.domain.user.entity.UserCardCompany;
import com.ssafy.benepick.domain.user.repository.UserRepository;
import com.ssafy.benepick.domain.user.service.UserService;
import com.ssafy.benepick.global.exception.NotExistCardCompanyException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CardCompanyServiceImpl implements CardCompanyService {

	private final CardCompanyRepository cardCompanyRepository;
	private final UserService userService;
	private final UserRepository userRepository;

	@Override
	public List<CardCompanyResponseDto> getAllCardCompany() {
		log.info("CardCompanyServiceImpl_getAllCardCompany | 모든 카드사 조회");

		return cardCompanyRepository.findAll()
			.stream()
			.map(cardCompany -> cardCompany.toCardCompanyResponseDto())
			.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void linkAndRenewCardCompany(List<Long> cardCompanyIdList, HttpServletRequest request) {
		log.info("CardCompanyServiceImpl_linkAndRenewCardCompany | 카드사 연동 및 연동 기간 갱신");
		// User loginUser = userService.getUserFromRequest(request);
		User loginUser = userRepository.findById("ex1").get();
		List<UserCardCompany> userCardCompanyList = loginUser.getUserCardCompanyList();

		for (Long cardCompanyId : cardCompanyIdList) {
			boolean isExist = false;

			for (UserCardCompany userCardCompany : userCardCompanyList){
				// 카드사가 이미 연동 되어있다면 기간 갱신
				if(userCardCompany.getUserCardCompanyId().equals(cardCompanyId)){
					userCardCompany.renewDate();
					isExist = true;
					break;
				}
			}

			// 카드사가 연동이 안된상태일경우 새로 연동
			if(!isExist)
				loginUser.linkCardCompany(cardCompanyRepository.findById(cardCompanyId).orElseThrow(NotExistCardCompanyException::new));
		}
	}
}
