package com.example.demo.controllers;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Order;
import com.example.demo.repositories.OrderItemRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.services.OrderService;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



// This code defines a RESTful API controller for orders. 
// The @RestController and @RequestMapping annotations are used to define the base URL endpoint for this controller, 
// which is "/orders". The ArrayList<Order> orders variable is used to store a list of orders.
@Controller
@RequestMapping("/myOrders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
   


    // This method handles GET requests to the "/orders" endpoint 
    // and returns a ResponseEntity object containing the list of orders 
    // and an HTTP status of 200 OK.
    @GetMapping("")
    public ResponseEntity getOrders(){
        orderService.log("User fetched all orders");
        List<Order> orders=this.orderRepository.findAll();
        return new ResponseEntity(orders, HttpStatus.OK);
    }

    // This method handles GET requests to the "/orders/{OrderId}" endpoint 
    // and takes a path variable OrderId to specify which Order to return. 
    // It loops through the list of orders and returns the Order with the specified ID if it exists,
    // along with an HTTP status of 200 OK. 
    // If no Order is found, it returns an HTTP status of 404 Not Found.
    @GetMapping("{orderId}")
    public ResponseEntity getOrder(@PathVariable("orderId") Long orderId){
        Order order=this.orderRepository.findById(orderId).orElse(null);
        if(order==null)
        {
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(order, HttpStatus.OK);
    }

    // This method handles Order requests to the "/orders" endpoint 
    // and takes a request body containing a Map with a "caption" field.
    // It creates a new Order object with a generated ID and the caption from the request body, 
    // adds it to the list of orders, and returns a ResponseEntity object 
    // containing the new Order and an HTTP status of 201 Created.
    @PostMapping("")
    public ResponseEntity createOrder(@RequestBody Map<String,String> body){
        Timestamp timestamp;
        try {
            timestamp = getCurrentTime();
            Order order = new Order();
            this.orderRepository.save(order);
            ResponseEntity response = new ResponseEntity<>(order, HttpStatus.OK);
            return response;    
        } catch (ParseException e) {
            
            return ResponseEntity.badRequest().build();
        }
    }


    // This method handles PUT requests to the "/orders/{OrderId}" endpoint 
    // and takes a path variable OrderId to specify which Order to update, 
    // and a request body containing a Map with a "caption" field. 
    // It loops through the list of orders and updates the Order with the specified ID if it exists, 
    // setting its caption to the value from the request body. 
    // It returns a ResponseEntity object containing the updated Order and an HTTP status of 200 OK. 
    // If no Order is found, it returns an HTTP status of 404 Not Found.
    @PutMapping("{orderId}")
    public ResponseEntity updateOrder(@PathVariable("orderId") Long orderId, @RequestBody Map<String,String> body){
        Order order=this.orderRepository.findById(orderId).orElse(null);
        if(order==null)
        {
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }
        //Order order = new Order(body.get("billNo"), body.get("menu"), Integer.parseInt(body.get("quantity")));
        this.orderRepository.save(order);
        return new ResponseEntity(order, HttpStatus.OK);

    }

    // This method handles DELETE requests to the "/orders/{OrderId}" endpoint 
    // and takes a path variable OrderId to specify which Order to delete. 
    // It loops through the list of orders and removes the Order with the specified ID if it exists.
    // It returns a ResponseEntity object containing a message saying the Order was deleted 
    // and an HTTP status of 200 OK. If no Order is found, it returns an HTTP status of 404 Not Found.
    @DeleteMapping("{orderId}")
    public ResponseEntity deleteOrder(@PathVariable("orderId") Long orderId){
        Order order=this.orderRepository.findById(orderId).orElse(null);
        if(order==null)
        {
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }
   
        return new ResponseEntity("Deleted", HttpStatus.OK);
    }

    public Timestamp getCurrentTime() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = df.format(new Date());
        Date orderedDate = df.parse(dateStr);
        return new java.sql.Timestamp(orderedDate.getTime());
    }
}