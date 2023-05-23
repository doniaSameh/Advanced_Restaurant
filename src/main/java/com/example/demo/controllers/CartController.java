package com.example.demo.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.models.FoodItem;
import com.example.demo.models.Order;
import com.example.demo.models.OrderItem;
import com.example.demo.models.User;
import com.example.demo.repositories.CartItemRepository;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.FoodItemRepository;
import com.example.demo.repositories.OrderItemRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.services.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private FoodItemRepository foodItemRepository; 
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository; 
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("")
    public ModelAndView getCartView(@AuthenticationPrincipal User user){
        ModelAndView mav= new ModelAndView("cart.html");
        Cart cart = cartRepository.findByUser(user).orElse(null);
        mav.addObject("cart",cart);
        return mav;
    }
    @PostMapping("/add")
    public String addItemToCart(@RequestParam(name = "id") int id,@RequestParam(name="quantity")int quantity, @AuthenticationPrincipal User user) {
        CartItem item = new CartItem();
        item.setQuantity(quantity);
        FoodItem fooditem = foodItemRepository.findById(id).orElse(null);
        item.setItems(fooditem);

        Cart cart = cartRepository.findByUser(user).orElse(null);

        if(cart == null){
            cart = new Cart();
            cart.setUser(user);
            this.cartRepository.save(cart);
        }

        item.setCart(cart);
        cartItemRepository.save(item);
        // cartService.addItemToCart(cart,item);
        return "redirect:/cart";
    }

    // @PostMapping("/remove/{index}")
    // public void removeItemFromCart(@PathVariable int index) {
    //     cartService.removeItemFromCart(index);
    // }

    // @PostMapping("/update/{index}")
    // public void updateItemInCart(@PathVariable int index, @RequestBody CartItem item) {
    //     cartService.updateItemInCart(index, item);
    // }

    // @GetMapping("/items")
    // public List<CartItem> getCartItems() {
    //     return cartService.getCartItems();
    // }

    @PostMapping("/checkout")
    public String checkout(@AuthenticationPrincipal User user) {

        Order order = new Order();
        order.setUser(user);
        orderRepository.save(order); 
        List<OrderItem> items = new ArrayList<>();
        Cart cart = cartRepository.findByUser(user).orElse(null);
        for(CartItem c:cart.getItems()){
           OrderItem orderItem = new OrderItem();
           orderItem.setItems(c.getItems());
           orderItem.setQuantity(c.getQuantity());
           orderItem.setOrder(order);
           items.add(orderItem);
        }
        orderItemRepository.saveAll(items);
        cartItemRepository.deleteAll(cart.getItems());
        return "redirect:/cart";
        // cartService.checkout();
        // cartService.clearCart();
    }

    // @PostMapping("/save")
    // public void saveCartItems() {
    //     cartService.saveCartItems();
    // }
}


