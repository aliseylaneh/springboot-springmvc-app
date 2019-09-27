package com.aliseylaneh.springmvcspringboot;

import com.aliseylaneh.springmvcspringboot.controllers.CustomerController;
import com.aliseylaneh.springmvcspringboot.domain.Customer;
import com.aliseylaneh.springmvcspringboot.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());
        when(customerService.listAllCustomers()).thenReturn((List) customers);
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attribute("customers", hasSize(2)));

    }

    @Test
    public void testShow() throws Exception {
        int id = 1;
        when(customerService.getCustomerById(id)).thenReturn(new Customer());
        mockMvc.perform(get("/customer/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("customer"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testEdit() throws Exception {
        int id = 1;
        when(customerService.getCustomerById(id)).thenReturn(new Customer());
        mockMvc.perform(get("/customer/edit/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNewCustomer() throws Exception {
        int id = 1;
        // Should not call service
        verifyZeroInteractions(customerService);
        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String firstName = "Ali";
        String lastName = "Seylaneh";
        String phoneNumber = "111-555-111";
        String zipCode = "1235125";
        String addressLineOne = "Street Two";
        String addressLineTwo = "Street Two";
        String city = "Tehran";
        Customer returnCustomer = new Customer();
        returnCustomer.setId(id);
        returnCustomer.setFirstName(firstName);
        returnCustomer.setLastName(lastName);
        returnCustomer.setPhoneNumber(phoneNumber);
        returnCustomer.setZipCode(zipCode);
        returnCustomer.setAddressLineTwo(addressLineTwo);
        returnCustomer.setAddressLineOne(addressLineOne);
        returnCustomer.setCity(city);
        when(customerService.saveOrUpdateCustomer(Matchers.<Customer>any())).thenReturn(returnCustomer);
        mockMvc.perform(post("/customer")
                .param("id", "1")
                .param("firstName", "Ali")
                .param("lastName", "Seylaneh")
                .param("city", "Tehran")
                .param("addressLineOne", "Street Two")
                .param("addressLineTwo", "Street Two")
                .param("zipCode", "1235125")
                .param("phoneNumber", "111-555-111")).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/1"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                .andExpect(model().attribute("customer", hasProperty("addressLineOne", is(addressLineOne))))
                .andExpect(model().attribute("customer", hasProperty("addressLineTwo", is(addressLineTwo))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("customer", hasProperty("zipCode", is(zipCode))));
        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdateCustomer(boundCustomer.capture());
        assertEquals(id, boundCustomer.getValue().getId());
        assertEquals(lastName, boundCustomer.getValue().getLastName());
        assertEquals(firstName, boundCustomer.getValue().getFirstName());
        assertEquals(city, boundCustomer.getValue().getCity());
        assertEquals(phoneNumber, boundCustomer.getValue().getPhoneNumber());
        assertEquals(addressLineOne, boundCustomer.getValue().getAddressLineOne());
        assertEquals(addressLineTwo, boundCustomer.getValue().getAddressLineTwo());
        assertEquals(zipCode, boundCustomer.getValue().getZipCode());


    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;
        mockMvc.perform(get("/customer/delete/1")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/customers"));
        verify(customerService, times(1)).deleteCustomer(id);
    }
}
