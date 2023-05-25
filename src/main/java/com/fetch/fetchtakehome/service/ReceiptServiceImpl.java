package com.fetch.fetchtakehome.service;


import com.fetch.fetchtakehome.entity.Receipt;
import com.fetch.fetchtakehome.util.CalculatePointsHelper;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Implementation of Receipt Service
 */
@Service
public class ReceiptServiceImpl implements ReceiptService{

    private final CalculatePointsHelper helper = new CalculatePointsHelper();
    private final Map<String, Integer> record = new HashMap<>();

    @Override
    public ResponseEntity<String> storeReceipts(Receipt receipt) {
        JSONObject json = new JSONObject();
        if (receipt.checkNull()) {
            String errorMessage = "The receipt is invalid";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        UUID uuid = calculateAndStorePoints(receipt);
        json.put("id", uuid.toString());
        return new ResponseEntity<>(json.toString(), HttpStatus.OK);
    }

    private UUID calculateAndStorePoints(Receipt receipt) {
        int totalPoints = helper.calculatePoints(receipt);
        UUID uuid = UUID.randomUUID();
        record.put(uuid.toString(), totalPoints);
        return uuid;
    }

    @Override
    public ResponseEntity<String> getPoints(String uuid) {
        Integer points = record.getOrDefault(uuid, null);
        if (points != null) {
            JSONObject json = new JSONObject();
            json.put("points", points);
            return ResponseEntity.ok(json.toString());
        }

        String errorMessage = "No receipt found for that id";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

    }
}
