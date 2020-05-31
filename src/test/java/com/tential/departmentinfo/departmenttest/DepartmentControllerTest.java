package com.tential.departmentinfo.departmenttest;

import com.tential.departmentinfo.entity.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class DepartmentControllerTest extends BaseTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createDepartment() throws Exception {
        String uri = "http://localhost:8090/department";
        Department dept=new Department();
        dept.setDepartment_count(20);
        dept.setDepartment_name("newdept");
        String inputJson = super.mapToJson(dept);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
        //String content = mvcResult.getResponse().getContentAsString();
        //Assert.assertEquals(content, "Department is created successfully");
    }

    @Test
    public void updateDepartment() throws Exception {
        String uri = "http://localhost:8090/department/4";
        Department dept=new Department();
            dept.setDepartment_count(20);
        dept.setDepartment_name("Sales");
        String inputJson = super.mapToJson(dept);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
        //String content = mvcResult.getResponse().getContentAsString();
        //Assert.assertEquals(content, "Department is created successfully");
    }

    @Test
    public void getDepartmentWithInvalidId() throws Exception {
        String uri = "http://localhost:8090/department/22";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404, status);
    }

    @Test
    public void getallDepartments() throws Exception {
        String uri = "http://localhost:8090/department";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }


    @Test
    public void createDepartmentWithInvalidData() throws Exception {
        String uri = "http://localhost:8090/department";
        Department dept=new Department();
        dept.setDepartment_count(20);
        String inputJson = super.mapToJson(dept);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(500, status);
        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertTrue(content.contains("Input Data is Not proper"));
    }

//    @Test
//    public void deleteDepartment() throws Exception {
//        String uri = "http://localhost:8090/department/10";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        Assert.assertEquals(200, status);
//        //String content = mvcResult.getResponse().getContentAsString();
//        //Assert.assertEquals(content, "Product is deleted successsfully");
//    }

    @Test
    public void deleteDepartmentWithInvalidId() throws Exception {
        String uri = "http://localhost:8090/department/50";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404, status);
        //String content = mvcResult.getResponse().getContentAsString();
        //Assert.assertEquals(content, "Product is deleted successsfully");
    }


}
