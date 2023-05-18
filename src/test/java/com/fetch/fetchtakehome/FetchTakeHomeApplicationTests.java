package com.fetch.fetchtakehome;

import com.fetch.fetchtakehome.entity.Item;
import com.fetch.fetchtakehome.entity.Receipt;
import com.fetch.fetchtakehome.service.ReceiptService;
import com.fetch.fetchtakehome.service.ReceiptServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FetchTakeHomeApplicationTests {

	private ReceiptService service;
	private String uuid;

	@BeforeEach
	void setUp() {
		service = new ReceiptServiceImpl();
	}

	@Test
	void storeAndFetchReceipts() {
		Receipt receipt = new Receipt();
		receipt.setRetailer("Target");
		receipt.setPurchaseDate("2022-01-01");
		receipt.setPurchaseTime("13:01");
		receipt.setTotal("35.35");

		Item[] items = new Item[5];
		items[0] = new Item();
		items[0].setShortDescription("Mountain Dew 12PK");
		items[0].setPrice("6.49");

		items[1] = new Item();
		items[1].setShortDescription("Emils Cheese Pizza");
		items[1].setPrice("12.25");

		items[2] = new Item();
		items[2].setShortDescription("Knorr Creamy Chicken");
		items[2].setPrice("1.26");

		items[3] = new Item();
		items[3].setShortDescription("Doritos Nacho Cheese");
		items[3].setPrice("3.35");

		items[4] = new Item();
		items[4].setShortDescription("   Klarbrunn 12-PK 12 FL OZ  ");
		items[4].setPrice("12.00");

		receipt.setItem(items);

		// Store receipt
		ResponseEntity<String> storeResponse = service.storeReceipts(receipt);
		assertEquals(200, storeResponse.getStatusCodeValue());
		assertTrue(storeResponse.getBody().contains("id"));

		// Parse the UUID from the response
		try {
			JSONObject jsonObject = new JSONObject(storeResponse.getBody());
			this.uuid = jsonObject.getString("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Fetch points
		ResponseEntity<String> getResponse = service.getPoints(this.uuid);
		assertEquals(200, getResponse.getStatusCodeValue());
		assertTrue(getResponse.getBody().contains("28"));
	}

	@Test
	void storeAndFetchReceipts_2() throws JSONException {
		Receipt receipt = new Receipt();
		receipt.setRetailer("M&M Corner Market");
		receipt.setPurchaseDate("2022-03-20");
		receipt.setPurchaseTime("14:33");
		receipt.setTotal("9.00");

		Item[] items = new Item[4];
		for (int i = 0; i < items.length; i++) {
			items[i] = new Item();
			items[i].setShortDescription("Gatorade");
			items[i].setPrice("2.25");
		}

		receipt.setItem(items);

		// Store receipt
		ResponseEntity<String> storeResponse = service.storeReceipts(receipt);
		assertEquals(200, storeResponse.getStatusCodeValue());
		assertTrue(storeResponse.getBody().contains("id"));

		// Parse the UUID from the response
		JSONObject jsonObject = new JSONObject(storeResponse.getBody());
		this.uuid = jsonObject.getString("id");

		// Fetch points
		ResponseEntity<String> getResponse = service.getPoints(this.uuid);
		assertEquals(200, getResponse.getStatusCodeValue());
		JSONObject getJson = new JSONObject(getResponse.getBody());
		assertEquals(109, getJson.getInt("points"));
	}

	@Test
	void getPointsForNonExistingId() {
		String nonExistingUuid = UUID.randomUUID().toString();
		ResponseEntity<String> response = service.getPoints(nonExistingUuid);
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
	}

	@Test
	void storeNullReceipt() {
		Receipt receipt = new Receipt();
		// we don't set any field, so all parts of the receipt are null

		ResponseEntity<String> response = service.storeReceipts(receipt);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
	}

	@Test
	void storeReceiptWithNullItem() {
		Receipt receipt = new Receipt();
		receipt.setRetailer("Target");
		receipt.setPurchaseDate("2022-01-01");
		receipt.setPurchaseTime("13:01");
		receipt.setTotal("35.35");
		// we don't set items, so items is null

		ResponseEntity<String> response = service.storeReceipts(receipt);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
	}

}
