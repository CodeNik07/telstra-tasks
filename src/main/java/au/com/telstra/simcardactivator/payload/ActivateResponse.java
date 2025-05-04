package au.com.telstra.simcardactivator.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ActivateResponse(boolean success) {
}
