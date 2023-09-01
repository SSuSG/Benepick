package com.ssafy.benepick.domain.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.benepick.domain.card.entity.CardCompany;

public interface CardCompanyRepository extends JpaRepository<CardCompany, Long> {
}