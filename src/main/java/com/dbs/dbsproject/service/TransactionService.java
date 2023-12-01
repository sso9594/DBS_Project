package com.dbs.dbsproject.service;

import com.dbs.dbsproject.domain.Transaction;
import com.dbs.dbsproject.dto.TransDto;
import com.dbs.dbsproject.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction save(TransDto transDto){
        return transactionRepository.save(transDto.toEntity());
    }

    public Page<Transaction> findAllbyBuyerAndState(PageRequest pageRequest, String id){
        return transactionRepository.findAllByBuyerIdAndAndState(pageRequest, id,true);
    }
}
