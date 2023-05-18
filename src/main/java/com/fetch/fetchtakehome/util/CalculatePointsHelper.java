package com.fetch.fetchtakehome.util;


import com.fetch.fetchtakehome.entity.Item;
import com.fetch.fetchtakehome.entity.Receipt;

public class CalculatePointsHelper {
    public int calculatePoints(Receipt receipt) {
        int totalPoints = 0;
        int retailerNamePoints = pointsByRetailerName(receipt.getRetailer());
        int totalDollarPoints = pointsByTotalDollar(receipt.getTotal());
        int itemPoints = pointsByNumberOfItems(receipt.getItem());
        int descriptionPoints = pointsByDescription(receipt.getItem());
        int datePoints = pointsByDate(receipt.getPurchaseDate());
        int timePoints = pointsByTime(receipt.getPurchaseTime());

        totalPoints = totalPoints + retailerNamePoints + totalDollarPoints
                + itemPoints + descriptionPoints + datePoints + timePoints;
        return totalPoints;

    }

    // One point for every alphanumeric character in the retailer name.
    private int pointsByRetailerName(String retailer) {
        int points = 0;
        for (char ch: retailer.toCharArray()) {
            if (isAlphaNumeric(ch)) {
                points++;
            }
        }
        return points;
    }

    // helper method to tell if char is alphanumeric character
    private boolean isAlphaNumeric(char ch) {
        if ((ch >= '0' & ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
            return true;
        return false;
    }

    // 50 points if the total is a round dollar amount with no cents.
    // 25 points of total is a multiple of 0.25.
    private int pointsByTotalDollar(String total) {
        // calculate if it's round dollar
        int points = 0;
        String[] str = total.split("\\.");
        String decimal = str[1];
        if (decimal.equals("00")) {
            points += 50;
        }

        // calculate if is a multiple of 0.25, which means decimal is either 00 or 25 or 50 or 75
        // ignore the part before decimal point
        if (decimal.equals("00") || decimal.equals("25") || decimal.equals("50") || decimal.equals("75")) {
            points += 25;
        }
        return points;
    }

    // 5 points for every two items on the receipt. Assume if there is 5 items, then 10 points, the last item does not count
    private int pointsByNumberOfItems(Item[] item) {
        int points = 0;
        // get number of items / 2 and 5 points for every 2 items
        points += item.length / 2 * 5;
        return points;
    }

    // If the trimmed length of the item description is a multiple of 3,
    // multiply the price by 0.2 and round up to the nearest integer.
    // The result is the number of points earned.
    private int pointsByDescription(Item[] items) {
        int points = 0;
        for (Item item: items) {
            String shortDescription = item.getShortDescription();
            String trimmedDescription = shortDescription.trim();
            if (trimmedDescription.length() % 3 == 0) {
                points += Math.ceil(Double.parseDouble( item.getPrice()) * 0.2);
            }
        }
        return points;
    }

    // 6 points if the day in the purchase date is odd.
    private int pointsByDate(String purchaseDate) {
        int points = 0;
        String[] str = purchaseDate.split("-");
        // str[0] is year e.g 2020, str[1] is month, str[2] is date
        int date = Integer.parseInt(str[2]);
        if (date % 2 == 1) {
            points += 6;
        }
        return points;
    }

    // 10 points if the time of purchase is after 2:00pm and before 4:00pm. Assume 2:00 get 0 points and 2:01 get 10 points
    private int pointsByTime(String purchaseTime) {
        int points = 0;
        // turn time into 60 * hours + minute
        int startTime = 14 * 60;
        int endTime = 16 * 60;
        String[] str = purchaseTime.split(":");
        // turn time into int, e.g 14:20 --> 14 * 60 + 20
        int convertedPurchaseTime = Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
        if (convertedPurchaseTime > startTime && convertedPurchaseTime < endTime) {
            points += 10;
        }
        return points;
    }
}
