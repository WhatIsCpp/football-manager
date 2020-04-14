package com.app.demo.service.interfaces;

import com.app.demo.model.dto.FootballPlayerResponseDto;
import com.app.demo.model.dto.FootballPlayerTransferRequestDto;
import com.app.demo.model.dto.FootballPlayerTransferResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface TransferService {

    FootballPlayerTransferResponseDto getTransferFee(String uFTI, String uFPI, LocalDate joinDate, Long teamCommissionPercentage);

    @Transactional
    FootballPlayerResponseDto transferPlayer(FootballPlayerTransferRequestDto footballPlayerTransferRequestDto);
}
