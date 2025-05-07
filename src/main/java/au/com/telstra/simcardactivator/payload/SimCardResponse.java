package au.com.telstra.simcardactivator.payload;

public record SimCardResponse(String icicid, String customerEmail, boolean active) {

}
