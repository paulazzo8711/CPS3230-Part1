package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.junit.jupiter.api.Assertions.*;

public class LocationServiceTest {
    private LocationService locationService;
    private HttpHelper mockHttpHelper;

    @BeforeEach
    void setUp() {

        mockHttpHelper = Mockito.mock(HttpHelper.class);

        locationService = new LocationService(mockHttpHelper);
    }

    @Test
    void testGetCurrentLocationWithPrimaryService() throws Exception {

        String validJsonResponse = "{\"city\": \"New York\", \"countryCode\": \"US\", \"status\": \"success\"}";
        Mockito.when(mockHttpHelper.makeHttpRequest(LocationService.PRIMARY_API_URL, false))
                .thenReturn(validJsonResponse);


        String location = locationService.getCurrentLocation();


        assertNotNull(location);
        assertEquals("New York US", location);
    }



    @Test
    void testGetCurrentLocationWithBackupService() throws Exception {

        Mockito.when(mockHttpHelper.makeHttpRequest(LocationService.PRIMARY_API_URL, false))
                .thenReturn("Unknown Location");

        Mockito.when(mockHttpHelper.makeHttpRequest(LocationService.BACKUP_API_URL, true))
                .thenReturn("{\"city\": \"London\", \"country\": \"GB\"}");


        String location = locationService.getCurrentLocation();


        assertNotNull(location);
        assertEquals("London GB", location);
    }
}
