package edu.poly.asm.controller.admin;

import edu.poly.asm.domain.Customer;
import edu.poly.asm.domain.Order;
import edu.poly.asm.model.CategoryDto;
import edu.poly.asm.model.CustomerDto;
import edu.poly.asm.model.OrderDto;
import edu.poly.asm.service.CustomerService;
import edu.poly.asm.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @ModelAttribute("customers")
    public List<CustomerDto> getCustomers() {
        return customerService.findAll().stream().map(item -> {
            CustomerDto dto = new CustomerDto();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).toList();
    }


    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("order", new OrderDto());
        return "admin/orders/addOrEdit";
    }

    @GetMapping("edit/{orderId}")
    public ModelAndView edit(ModelMap model, @PathVariable("orderId") Long orderId) {
        Optional<Order> opt = orderService.findById(orderId);
        OrderDto dto = new OrderDto();
        if (opt.isPresent()) {
            Order entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);
            model.addAttribute("order", dto);
            return new ModelAndView("admin/orders/addOrEdit", model);
        }
        model.addAttribute("message", "Order not found");
        return new ModelAndView("redirect:admin/orders", model);
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("order") OrderDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/orders/addOrEdit", model);
        }

        Order entity = new Order();
        BeanUtils.copyProperties(dto, entity);

        Customer customer = new Customer();
        customer.setCustomerId(dto.getCustomerId());
        entity.setCustomer(customer);

        orderService.save(entity);
        model.addAttribute("message", "Order saved");

        return new ModelAndView("redirect:/admin/orders/searchpaginated", model);
    }

    @GetMapping("delete/{orderId}")
    public ModelAndView delete(ModelMap model, @PathVariable("orderId") Long orderId) {
        orderService.deleteById(orderId);
        model.addAttribute("message", "Order deleted successfully");
        return new ModelAndView("forward:/admin/orders/searchpaginated", model);
    }

    @RequestMapping("")
    public String list(ModelMap model) {
        List<Order> list = orderService.findAll();

        model.addAttribute("orders", list);
        return "admin/orders/list";
    }

//    @GetMapping("search")
//    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
//        List<Category> list = null;
//
//        if (StringUtils.hasText(name)) {
//            list = categoryService.findByNameContaining(name);
//
//        } else {
//            list = categoryService.findAll();
//        }
//        model.addAttribute("categories", list);
//
//        return "admin/categories/search";
//    }
//
@GetMapping("searchpaginated")
public String search(ModelMap model, @RequestParam(name = "orderId", required = false) Long orderId,
                     @RequestParam("page") Optional<Integer> page,
                     @RequestParam("size") Optional<Integer> size) {

    int currentPage = page.orElse(1);
    int pageSize = size.orElse(5);

    Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("orderId"));
    Page<Order> resultPage = null;

    if (orderId != null) {
        resultPage = orderService.findByOrderId(orderId, pageable);
        model.addAttribute("name", orderId);

    } else {
        resultPage = orderService.findAll(pageable);
    }

    int totalPages = resultPage.getTotalPages();

    if (totalPages > 1) {
        int start = Math.max(1, currentPage - 2);
        int end = Math.min(currentPage + 2, totalPages);

        if (totalPages > 5) {
            if (end == totalPages) start = end + 5;
            else if (start == 1) end = start + 5;
        }
        List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                .boxed().collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);
    }


    model.addAttribute("orderPage", resultPage);

    return "admin/orders/searchpaginated";
}


}
