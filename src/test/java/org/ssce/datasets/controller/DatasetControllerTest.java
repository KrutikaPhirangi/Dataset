package org.ssce.datasets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.ssce.datasets.model.Dataset;
import org.ssce.datasets.respository.DatasetRepository;
import org.ssce.datasets.service.DatasetService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(DatasetController.class)
@ExtendWith(MockitoExtension.class)
public class DatasetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DatasetService datasetService;

    @MockBean
    DatasetRepository datasetRepository;

    private Dataset dataset;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateDatasetSuccess() throws Exception {
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset = new Dataset(uuid,"datasetEmployee",new HashMap<>(),new HashMap<>(),Dataset.Status.DRAFT,
                "datasetEmployee","datasetEmployee",System.currentTimeMillis(), System.currentTimeMillis());
         datasetRepository.save(dataset);
        when(datasetService.createData(any(Dataset.class))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(post("/dataset/create").content(objectMapper.writeValueAsString(dataset))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap= objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("result");
        String id = (String) result.get("id");
        assertEquals(uuid.toString(), id);
        assertEquals(200,status);
    }

    @Test
    void testCreateDatasetInvalidNameInput() throws Exception {
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset = new Dataset(uuid,"12",new HashMap<>(),new HashMap<>(),Dataset.Status.DRAFT,
                "datasetEmployee","datasetEmployee",System.currentTimeMillis(), System.currentTimeMillis());
        when(datasetService.createData(any(Dataset.class))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(post("/dataset/create").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap = objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String msg = (String) result.get("errMsg");
        assertEquals("[$.name: does not match the regex pattern ^[a-zA-Z!@#$%^&?|_<>]+$]",msg);
        assertEquals(400, status);

    }

    @Test
    void testCreateDatasetInvalidStatus() throws Exception {
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset = new Dataset(uuid,"datasetEmployeeOne",new HashMap<>(),new HashMap<>(),null,
                "datasetEmployee","datasetEmployee",System.currentTimeMillis(), System.currentTimeMillis());
        when(datasetService.createData(any(Dataset.class))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(post("/dataset/create").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap = objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String msg = (String) result.get("errMsg");
        assertEquals("[$.status: does not have a value in the enumeration [LIVE, DRAFT, RETIRED]]",msg);
        assertEquals(400, status);
    }

    @Test
    void testCreateDatasetInvalidCreatedByName() throws Exception {
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset = new Dataset(uuid,"datasetEmployeeOne",new HashMap<>(),new HashMap<>(), Dataset.Status.DRAFT,
                "datasetEmployee12","datasetEmployee",System.currentTimeMillis(), System.currentTimeMillis());
        when(datasetService.createData(any(Dataset.class))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(post("/dataset/create").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap = objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String msg = (String) result.get("errMsg");
        assertEquals("[$.createdBy: does not match the regex pattern ^[a-zA-Z!@#$%^&?|_<>]+$]",msg);
        assertEquals(400, status);
    }

    @Test
    void testCreateDatasetInvalidUpdatedByName() throws Exception {
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset = new Dataset(uuid,"datasetEmployeeOne",new HashMap<>(),new HashMap<>(), Dataset.Status.DRAFT,
                "datasetEmployee","datasetEmployee12",System.currentTimeMillis(), System.currentTimeMillis());
        when(datasetService.createData(any(Dataset.class))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(post("/dataset/create").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap = objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String msg = (String) result.get("errMsg");
        assertEquals("[$.updatedBy: does not match the regex pattern ^[a-zA-Z!@#$%^&?|_<>]+$]",msg);
        assertEquals(400, status);
    }


    @Test
    void testGetDataset() throws Exception {
        UUID uuid = UUID.fromString("74059a86-98c1-45ea-b7ce-1609b71c87f9");
        dataset = new Dataset(uuid, "datasetEmployee", new HashMap<>(), new HashMap<>(), Dataset.Status.DRAFT,
                "datasetEmployee", "datasetEmployee", System.currentTimeMillis(), System.currentTimeMillis());
        when(datasetService.findByUuid(UUID.fromString("74059a86-98c1-45ea-b7ce-1609b71c87f9"))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(get("/dataset/get/74059a86-98c1-45ea-b7ce-1609b71c87f9").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap = objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("result");
        Map<String,Object> msg = (Map<String, Object>) result.get("dataset");
        assertEquals(200, status);
    }

    @Test
    void testGetDatasetNotFound() throws Exception {
        UUID uuid = UUID.fromString("74059a86-98c1-45ea-b7ce-1609b71c87f9");
        dataset = new Dataset(uuid, "datasetEmployee", new HashMap<>(), new HashMap<>(), Dataset.Status.DRAFT,
                "datasetEmployee", "datasetEmployee", System.currentTimeMillis(), System.currentTimeMillis());
        when(datasetService.findByUuid(UUID.fromString("74059a86-98c1-45ea-b7ce-1609b71c87f9"))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(get("/dataset/get/73059a86-98c1-45ea-b7ce-1609b71c87f8").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap = objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String msg = (String) result.get("errMsg");
        assertEquals(404, status);
        assertEquals("Dataset Not Found",msg);
    }

    @Test
    void testGetDatasetInvalidId() throws Exception {
        UUID uuid = UUID.fromString("74059a86-98c1-45ea-b7ce-1609b71c87f9");
        dataset = new Dataset(uuid, "datasetEmployee", new HashMap<>(), new HashMap<>(), Dataset.Status.DRAFT,
                "datasetEmployee", "datasetEmployee", System.currentTimeMillis(), System.currentTimeMillis());
        when(datasetService.findByUuid(uuid)).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(get("/dataset/get/73059a86-98c1").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap = objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String msg = (String) result.get("errMsg");
        assertEquals(400, status);
        assertEquals("Invalid id",msg);
    }
}