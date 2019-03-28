package model.validation;



import java.time.LocalDateTime;

public interface ConferenceValidation {

boolean titleValid(String title);
boolean titleEnValid(String title);
boolean descriptionValid(String description);
boolean descriptionEnValid(String description);
boolean dateTimeValid(LocalDateTime dateTime);
boolean placeValid(String place);
boolean placeEnValid(String place);
boolean lecturesCapacityValid(int lecturesCapacity);
boolean placeCapacity(int placeCapacity);
}
