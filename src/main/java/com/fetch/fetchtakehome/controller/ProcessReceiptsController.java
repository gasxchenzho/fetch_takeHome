package com.fetch.fetchtakehome.controller;

import com.fetch.fetchtakehome.entity.Receipt;
import com.fetch.fetchtakehome.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/***
 * This is the controller of this webService, it takes the url and return the json object
 */
@RestController
// @ResponseBody + @Controller
@RequestMapping(value = "/receipts")
public class ProcessReceiptsController {
    @Autowired
    private ReceiptService receiptService;

    // post method, submits a receipt for processing
    // Returns the ID assigned to the receipt
    @PostMapping(value = "/process")
    public ResponseEntity<String> processReceipts(@RequestBody Receipt receipt) {
        return receiptService.storeReceipts(receipt);
    }

    @GetMapping(value = "/{id}/points")
    public ResponseEntity<String> getPoints(@PathVariable("id") String id) {
        return receiptService.getPoints(id);
    }
}
