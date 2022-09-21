package com.engobytes.addressor.api.controller;

import com.engobytes.addressor.service.LocationAddressService;
import com.engobytes.addressor.service.model.GeoPoint;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LocationControllerTest {

    private final static double CORRECT_LNG = 18.688550;
    private final static double CORRECT_LAT = 50.313263;
    private final static String DESCRIPTION = "example_description";
    private final static String ADDRESS_REQUEST = "Searched Text";

    private GeoPoint geoPoint;


    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @MockBean
    private LocationAddressService locationAddressService;

    @InjectMocks
    private LocationController locationController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        geoPoint = new GeoPoint(CORRECT_LNG, CORRECT_LAT, DESCRIPTION);
    }

    @Test
    public void shouldResponseWithOk() throws Exception{
        when(locationAddressService.getAddressFromGeoCords(CORRECT_LNG, CORRECT_LAT)).thenReturn(geoPoint);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAddress")
                        .queryParam("lng", Double.toString(CORRECT_LNG))
                        .queryParam("lat", Double.toString(CORRECT_LAT)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.description", is(DESCRIPTION)));
    }

    @Test
    public void shouldReturnBadRequest_whenLongitudeLowerThanPossible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAddress")
                        .queryParam("lng", Double.toString(-180.1))
                        .queryParam("lat", Double.toString(CORRECT_LAT)))
                .andExpect(status().is(400));
    }

    @Test
    public void shouldReturnBadRequest_whenLongitudeHigherThanPossible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAddress")
                        .queryParam("lng", Double.toString(180.1))
                        .queryParam("lat", Double.toString(CORRECT_LAT)))
                .andExpect(status().is(400));
    }

    @Test
    public void shouldReturnBadRequest_whenLatitudeLowerThanPossible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAddress")
                        .queryParam("lng", Double.toString(CORRECT_LNG))
                        .queryParam("lat", Double.toString(-90.1)))
                .andExpect(status().is(400));
    }

    @Test
    public void shouldReturnBadRequest_whenLatitudeHigherThanPossible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAddress")
                        .queryParam("lng", Double.toString(CORRECT_LNG))
                        .queryParam("lat", Double.toString(90.1)))
                .andExpect(status().is(400));
    }

    @Test
    public void shouldReturnOk_whenValidAddressValuePassedToAutoSearch() throws Exception {
        when(locationAddressService.getLocationsSuggestionsByNamePart(DESCRIPTION)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/autoFill/"+ADDRESS_REQUEST))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldReturnBadRequest_whenEmptyAddressPassed() throws Exception {
        when(locationAddressService.getLocationsSuggestionsByNamePart(DESCRIPTION)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/autoFill/"+" "))
                .andExpect(status().is(400));
    }

    @Test
    public void shouldReturnOk_whenMinCorrectLengthAddressPassed() throws Exception {
        when(locationAddressService.getLocationsSuggestionsByNamePart(DESCRIPTION)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/autoFill/"+ RandomString.make(1)))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldReturnOk_whenMaxCorrectLengthAddressPassed() throws Exception {
        when(locationAddressService.getLocationsSuggestionsByNamePart(DESCRIPTION)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/autoFill/"+ RandomString.make(50)))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldReturnBadRequest_whenToLongAddressPassed() throws Exception {
        when(locationAddressService.getLocationsSuggestionsByNamePart(DESCRIPTION)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/autoFill/"+ RandomString.make(51)))
                .andExpect(status().is(400));
    }

}