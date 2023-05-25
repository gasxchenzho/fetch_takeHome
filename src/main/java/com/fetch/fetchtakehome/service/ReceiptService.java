package com.fetch.fetchtakehome.service;


import com.fetch.fetchtakehome.entity.Receipt;
import org.springframework.http.ResponseEntity;

/**
 * This is the package for the interface
 */
public interface ReceiptService {
    ResponseEntity<String> storeReceipts(Receipt receipt);

    ResponseEntity<String> getPoints(String uuid);
}
