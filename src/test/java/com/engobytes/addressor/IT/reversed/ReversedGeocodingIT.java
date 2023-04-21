package com.engobytes.addressor.IT.reversed;

import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import com.engobytes.addressor.api.controller.LocationController;
import com.engobytes.addressor.api.model.TagApi;
import com.engobytes.addressor.configuration.SearchProperties;
import com.engobytes.addressor.photon.ConnectionPhotonAutoSearchGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.maps.model.LatLng;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestWithResources
public class ReversedGeocodingIT {

    private final static LatLng P1 = new LatLng(50.667960, 17.923840);
    private final static LatLng P2 = new LatLng(50.66759, 17.921166);
    private final static LatLng P3 = new LatLng(50.662960, 17.9238);
    private final static LatLng P4 = new LatLng(50.670028, 17.917389);
    private final static LatLng P5 = new LatLng(50.720507, 17.948719);
    private static final String PHOTON_IP = "localhost";
    private static final String PHOTON_PORT = "2300";
    private static final String PHOTON_BASE_URL = "localhost:2300";
    private static final String REVERSE_GEOCODE_URL = "/api/v1/getAddress";
    private static final String REVERSE_GEOCODE_TAG_CONTROLLER_URL = "/reverse/tags";

    @GivenTextResource(from = "it/reversedTest/P1_body.json")
    public String P1_EXPECTED_BODY;
    @GivenTextResource(from = "it/reversedTest/P2_body.json")
    public String P2_EXPECTED_BODY;
    @GivenTextResource(from = "it/reversedTest/P3_body.json")
    public String P3_EXPECTED_BODY;
    @GivenTextResource(from = "it/reversedTest/P4_body.json")
    public String P4_EXPECTED_BODY;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private RestTemplate restTemplate;
    private MockRestServiceServer mockServer;

    @Spy
    private SearchProperties propertySettings;
    private ConnectionPhotonAutoSearchGateway photonGateway;

    @InjectMocks
    private LocationController locationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        photonGateway = new ConnectionPhotonAutoSearchGateway(restTemplate, propertySettings);
        mockServer = MockRestServiceServer.createServer(restTemplate);
        when(propertySettings.getPhotonIp()).thenReturn(PHOTON_IP);
        when(propertySettings.getPhotonPort()).thenReturn(PHOTON_PORT);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldParseCorrectlyBuildingType() throws Exception {

        mockServer.expect(MockRestRequestMatchers.anything())
                .andRespond(MockRestResponseCreators.withSuccess(P1_EXPECTED_BODY, MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get(REVERSE_GEOCODE_URL)
                        .queryParam("lng", Double.toString(P1.lng))
                        .queryParam("lat", Double.toString(P1.lat)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.description").value("Krakowska 9, Opole"));
    }

    @DirtiesContext
    @Test
    public void shouldNotReturnDescription_whenRemoveTagPair() throws Exception {
        TagApi tagToRemove = new TagApi("building", "yes");

        mockServer.expect(ExpectedCount.manyTimes(), MockRestRequestMatchers.anything())
                .andRespond(MockRestResponseCreators.withSuccess(P1_EXPECTED_BODY, MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get(REVERSE_GEOCODE_URL)
                        .queryParam("lng", Double.toString(P1.lng))
                        .queryParam("lat", Double.toString(P1.lat)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.description").value("Krakowska 9, Opole"));

        mockMvc.perform(MockMvcRequestBuilders.delete(REVERSE_GEOCODE_TAG_CONTROLLER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectAsString(tagToRemove)));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(REVERSE_GEOCODE_URL)
                        .queryParam("lng", Double.toString(P1.lng))
                        .queryParam("lat", Double.toString(P1.lat)))
                .andExpect(status().is(404))
                .andReturn();

        assertEquals("Address not found", mvcResult.getResolvedException().getMessage());
    }

    private String getObjectAsString(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            //Nothing. just a test
        }
        return null;
    }

    @Test
    public void shouldParseCorrectlyHighwayType() throws Exception {

        mockServer.expect(MockRestRequestMatchers.anything())
                .andRespond(MockRestResponseCreators.withSuccess(P2_EXPECTED_BODY, MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get(REVERSE_GEOCODE_URL)
                        .queryParam("lng", Double.toString(P2.lng))
                        .queryParam("lat", Double.toString(P2.lat)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.description").value("Zamkowa, Opole"));
    }

    @Test
    public void shouldParseCorrectlyPlaceType() throws Exception {

        mockServer.expect(MockRestRequestMatchers.anything())
                .andRespond(MockRestResponseCreators.withSuccess(P3_EXPECTED_BODY, MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get(REVERSE_GEOCODE_URL)
                        .queryParam("lng", Double.toString(P3.lng))
                        .queryParam("lat", Double.toString(P3.lat)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.description").value("Wojciecha Korfantego 2, Opole"));
    }

    @Test
    public void shouldParseCorrectlyHouseTypeWithName() throws Exception {

        mockServer.expect(MockRestRequestMatchers.anything())
                .andRespond(MockRestResponseCreators.withSuccess(P4_EXPECTED_BODY, MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get(REVERSE_GEOCODE_URL)
                        .queryParam("lng", Double.toString(P4.lng))
                        .queryParam("lat", Double.toString(P4.lat)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.description").value("Hotel Piast, Piastowska, Opole"));

    }


}
