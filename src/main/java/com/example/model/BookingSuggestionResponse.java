package com.example.model;

import java.util.Map;

public class BookingSuggestionResponse {
	// Fields based on requirements
	private String bookerName;
	private String bookingNumber;
	private String cottageAddress;
	private String cottageImageUrl;
	private int numberOfPlaces;
	private int numberOfBedrooms;
	private int distanceToLake;
	private String nearestCity;
	private int distanceToCity;
	private String bookingStartDate;
	private String bookingEndDate;

	// Constructor
	public BookingSuggestionResponse(String bookerName, String bookingNumber, String cottageAddress,
			String cottageImageUrl, int numberOfPlaces, int numberOfBedrooms, int distanceToLake, String nearestCity,
			int distanceToCity, String bookingStartDate, String bookingEndDate) {
		this.bookerName = bookerName;
		this.bookingNumber = bookingNumber;
		this.cottageAddress = cottageAddress;
		this.cottageImageUrl = cottageImageUrl;
		this.numberOfPlaces = numberOfPlaces;
		this.numberOfBedrooms = numberOfBedrooms;
		this.distanceToLake = distanceToLake;
		this.nearestCity = nearestCity;
		this.distanceToCity = distanceToCity;
		this.bookingStartDate = bookingStartDate;
		this.bookingEndDate = bookingEndDate;
	}

	public BookingSuggestionResponse(Map<String, String> data) {
		this.bookerName = data.getOrDefault("bookerName", null);
		this.bookingNumber = data.getOrDefault("bookingNumber", null);
		this.cottageAddress = data.getOrDefault("cottageAddress", null);
		this.cottageImageUrl = data.getOrDefault("cottageImageUrl", null);
		this.numberOfPlaces = Integer.parseInt(data.getOrDefault("numberOfPlaces", "0"));
		this.numberOfBedrooms = Integer.parseInt(data.getOrDefault("numberOfBedrooms", "0"));
		this.distanceToLake = Integer.parseInt(data.getOrDefault("distanceToLake", "0"));
		this.nearestCity = data.getOrDefault("nearestCity", null);
		this.distanceToCity = Integer.parseInt(data.getOrDefault("distanceToCity", "0"));
		this.bookingStartDate = data.getOrDefault("bookingStartDate", null);
		this.bookingEndDate = data.getOrDefault("bookingEndDate", null);
	}

	// Getters and Setters
	public String getBookerName() {
		return bookerName;
	}

	public void setBookerName(String bookerName) {
		this.bookerName = bookerName;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public String getCottageAddress() {
		return cottageAddress;
	}

	public void setCottageAddress(String cottageAddress) {
		this.cottageAddress = cottageAddress;
	}

	public String getCottageImageUrl() {
		return cottageImageUrl;
	}

	public void setCottageImageUrl(String cottageImageUrl) {
		this.cottageImageUrl = cottageImageUrl;
	}

	public int getNumberOfPlaces() {
		return numberOfPlaces;
	}

	public void setNumberOfPlaces(int numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
	}

	public int getNumberOfBedrooms() {
		return numberOfBedrooms;
	}

	public void setNumberOfBedrooms(int numberOfBedrooms) {
		this.numberOfBedrooms = numberOfBedrooms;
	}

	public int getDistanceToLake() {
		return distanceToLake;
	}

	public void setDistanceToLake(int distanceToLake) {
		this.distanceToLake = distanceToLake;
	}

	public String getNearestCity() {
		return nearestCity;
	}

	public void setNearestCity(String nearestCity) {
		this.nearestCity = nearestCity;
	}

	public int getDistanceToCity() {
		return distanceToCity;
	}

	public void setDistanceToCity(int distanceToCity) {
		this.distanceToCity = distanceToCity;
	}

	public String getBookingStartDate() {
		return bookingStartDate;
	}

	public void setBookingStartDate(String bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}

	public String getBookingEndDate() {
		return bookingEndDate;
	}

	public void setBookingEndDate(String bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

}
