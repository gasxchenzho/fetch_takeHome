package com.fetch.fetchtakehome.service;


import com.fetch.fetchtakehome.entity.Receipt;
import org.springframework.http.ResponseEntity;

public interface ReceiptService {
    ResponseEntity<String> storeReceipts(Receipt receipt);

    ResponseEntity<String> getPoints(String uuid);
}
