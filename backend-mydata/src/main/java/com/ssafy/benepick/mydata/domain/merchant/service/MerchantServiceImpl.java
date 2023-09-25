package com.ssafy.benepick.mydata.domain.merchant.service;

import com.ssafy.benepick.mydata.domain.merchant.dto.response.ApiMerchantResponseDto;
import com.ssafy.benepick.mydata.domain.merchant.entity.Merchant;
import com.ssafy.benepick.mydata.domain.merchant.repository.MerchantRepository;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Service
public class MerchantServiceImpl implements MerchantService{
    private final MerchantRepository merchantRepository;
    @Override
    public ApiMerchantResponseDto findNearestMerchant(double x, double y) {
        return merchantRepository.findNearestMerchant(x, y).toApiMerchantResponseDto();
    }
}
