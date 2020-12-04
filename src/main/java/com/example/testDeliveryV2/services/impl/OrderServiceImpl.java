package com.example.testDeliveryV2.services.impl;

import com.example.testDeliveryV2.chainOfResp.*;
import com.example.testDeliveryV2.exceptions.ErrorCode;
import com.example.testDeliveryV2.exceptions.ServiceException;
import com.example.testDeliveryV2.iterator.PriceCounter;
import com.example.testDeliveryV2.iterator.MyIterator;
import com.example.testDeliveryV2.models.DTO.OrderDTO;
import com.example.testDeliveryV2.models.entities.Meal;
import com.example.testDeliveryV2.models.entities.Order;
import com.example.testDeliveryV2.repositories.MealRepository;
import com.example.testDeliveryV2.repositories.OrderRepository;
import com.example.testDeliveryV2.services.MealService;
import com.example.testDeliveryV2.services.OrderService;
import com.example.testDeliveryV2.state.Context;
import com.example.testDeliveryV2.state.StartState;
import com.example.testDeliveryV2.state.StopState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
    public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MealService mealService;
    private final MealRepository mealRepository;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<Order> findAllByUserId(Long id) {
        return orderRepository.findAllByDeletedAtIsNullAndUserId(id);
    }

    @Override
    public Order findById(Long id) throws ServiceException {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("order not found")
                .build());
    }

    @Override
    public Order update(OrderDTO order) throws ServiceException {
        if (order.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("order is null")
                    .build();
        }
        Order orderEntity = new Order();
        orderEntity.setStatus(order.getStatus());
        orderEntity.setOverallQuantity(order.getOverallQuantity());
        orderEntity.setOverallPrice(order.getOverallPrice());
        orderEntity.setMealList(order.getMealList());
        orderEntity.setPaymentType(order.getPaymentType());
        return  orderRepository.save(orderEntity);
    }


    @Override
    public Order add(OrderDTO order) throws ServiceException {
        if(order.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("order already exists")
                    .build();
        }

        Order orderEntity = new Order();
        int quantity = 0;
        List<Meal> meals = order.getMealList();
        ArrayList<Meal> meals1 = new ArrayList<Meal>();
        for (int i = 0; i < meals.size(); i++){
            Long n = meals.get(i).getId();
            quantity += meals.get(i).getQuantity();
            Meal meal = mealService.findById(n);
            meal.setQuantity(meals.get(i).getQuantity());
            meals1.add(i, meal);
        }
        orderEntity.setStatus(order.getStatus());
        orderEntity.setOverallQuantity(quantity);
        orderEntity.setOverallPrice(order.getOverallPrice());
        orderEntity.setMealList(meals1);
        orderEntity.setPaymentType(order.getPaymentType());



        OrderHandler orderHandler1 = new ProductSelection();
        OrderAction orderAction = new OrderAction("selectprod");
        orderHandler1.processRequest(orderAction);

        return  orderRepository.save(orderEntity);
    }

    public boolean payment(Long id) throws ServiceException {
        Order order = findById(id);
        String selected = "not selectprod";
        String payment = "not payment";
        if(order != null){
            selected = "selectprod";
            if(order.getPaid() == null){
                payment = "payment";
            }else {
                if(!order.getPaid()){
                    payment = "payment";
                }
            }
            OrderHandler orderHandler1 = new ProductSelection();
            OrderHandler orderHandler4 = new Payment();
            orderHandler1.nextHandler(orderHandler4);
            OrderAction orderAction = new OrderAction(selected);
            orderHandler1.processRequest(orderAction);
            orderAction = new OrderAction(payment);
            order.setPaid(orderHandler4.processRequest(orderAction));
            orderRepository.save(order);
            return orderHandler4.processRequest(orderAction);
        }else {
            return false;
        }
    }

    public boolean confirm(Long id) throws ServiceException {
        Order order = findById(id);
        String selected = "not selectprod";
        String payment = "not payment";
        if(order != null){
            selected = "selectprod";
            if(order.getPaid() != null){
                if(order.getPaid()){
                    payment = "payment";
                }
            }
            OrderHandler orderHandler1 = new ProductSelection();
            OrderHandler orderHandler4 = new Payment();
            OrderHandler orderHandler5 = new Confirmation();

            orderHandler1.nextHandler(orderHandler4);
            orderHandler4.nextHandler(orderHandler5);

            OrderAction orderAction = new OrderAction(selected);
            orderHandler1.processRequest(orderAction);
            orderAction = new OrderAction(payment);
            order.setPaid(orderHandler4.processRequest(orderAction));
            orderAction = new OrderAction("confirm");
            order.setConfirmed(orderHandler5.processRequest(orderAction));



            Context context = new Context();

            StartState startState = new StartState();
            order.setCompleted(startState.doAction(context)); // set false


            orderRepository.save(order);

            return orderHandler5.processRequest(orderAction);
        }else {
            return false;
        }
    }

    public String complete(Long id) throws ServiceException {
        Order order = findById(id);
        Context context = new Context();

        StopState stopState = new StopState();
        order.setCompleted(stopState.doAction(context));
        orderRepository.save(order);
        return "Order #" + order.getId() + " completed";
    }

    @Override
    public void delete(Order order) throws ServiceException {
        if (order.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("x is null")
                    .build();
        }
        order = findById(order.getId());
        order.setDeletedAt(new Date());
        orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        Order order = findById(id);
        order.setDeletedAt(new Date());
        orderRepository.save(order);
    }


    public Double countOverallPrice(Long id) throws ServiceException {
        Double response = 0.0;
//        Order order = orderRepository.findById(id).get();
        List<Meal> meals = mealRepository.findAllByOrderId(id);
        int size = meals.size();
        Double[] prices = new Double[size];
        for(int i = 0; i < size; i++){
            prices[i] = meals.get(i).getPrice();
        }
        PriceCounter priceCounter = new PriceCounter(prices);
        for (MyIterator myIterator = priceCounter.getMyIterator(); myIterator.hasNext();) {
            Double designation = (Double) myIterator.next();
            response += designation;
//            System.out.println("Designation : " + designation);
        }
        return response;
    }






























}
