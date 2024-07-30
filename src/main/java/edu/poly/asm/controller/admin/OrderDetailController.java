package edu.poly.asm.controller.admin;

import edu.poly.asm.domain.Customer;
import edu.poly.asm.domain.Order;
import edu.poly.asm.domain.OrderDetail;
import edu.poly.asm.domain.Product;
import edu.poly.asm.model.CustomerDto;
import edu.poly.asm.model.OrderDetailDto;
import edu.poly.asm.model.OrderDto;
import edu.poly.asm.model.ProductDto;
import edu.poly.asm.service.CustomerService;
import edu.poly.asm.service.OrderDetailService;
import edu.poly.asm.service.OrderService;
import edu.poly.asm.service.ProductService;
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
@RequestMapping("admin/orderDetails")
public class OrderDetailController {
    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderDetailService orderDetailService;



    @ModelAttribute("products")
    public List<ProductDto> getProducts() {
        return productService.findAll().stream().map(item -> {
            ProductDto dto = new ProductDto();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).toList();
    }

    @ModelAttribute("orders")
    public List<OrderDto> getOrder() {
        return orderService.findAll().stream().map(item -> {
            OrderDto dto = new OrderDto();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).toList();
    }


    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("orderDetail", new OrderDetailDto());
        return "admin/orderDetails/addOrEdit";
    }

    @GetMapping("edit/{orderDetailId}")
    public ModelAndView edit(ModelMap model, @PathVariable("orderDetailId") Long orderDetailId) {
        Optional<OrderDetail> opt = orderDetailService.findById(orderDetailId);
        OrderDetailDto dto = new OrderDetailDto();
        if (opt.isPresent()) {
            OrderDetail entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);
            model.addAttribute("orderDetail", dto);
            return new ModelAndView("admin/orderDetails/addOrEdit", model);
        }
        model.addAttribute("message", "Order Detail not found");
        return new ModelAndView("redirect:admin/orderDetails", model);
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("orderDetail") OrderDetailDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/orderDetails/addOrEdit", model);
        }

        OrderDetail entity = new OrderDetail();
        BeanUtils.copyProperties(dto, entity);

        Order order = new Order();
        order.setOrderId(dto.getOrderId());
        entity.setOrder(order);

        Product  product = new Product();
        product.setProductId(dto.getProductId());
        entity.setProduct(product);

        orderDetailService.save(entity);
        model.addAttribute("message", "Order Detail saved");

        return new ModelAndView("redirect:/admin/orderDetails/searchpaginated", model);
    }

    @GetMapping("delete/{orderDetailId}")
    public ModelAndView delete(ModelMap model, @PathVariable("orderDetailId") Long orderDetailId) {
        orderDetailService.deleteById( orderDetailId);
        model.addAttribute("message", "Order Detail deleted successfully");
        return new ModelAndView("forward:/admin/orderDetails/searchpaginated", model);
    }

    @RequestMapping("")
    public String list(ModelMap model) {
        List<OrderDetail> list = orderDetailService.findAll();

        model.addAttribute("orderDetails", list);
        return "admin/orderDetails/list";
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
public String search(ModelMap model, @RequestParam(name = "orderDetailId", required = false) Long orderDetailId,
                     @RequestParam("page") Optional<Integer> page,
                     @RequestParam("size") Optional<Integer> size) {

    int currentPage = page.orElse(1);
    int pageSize = size.orElse(5);

    Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("orderDetailId"));
    Page<OrderDetail> resultPage = null;

    if (orderDetailId != null) {
        resultPage = orderDetailService.findByOrderDetailId(orderDetailId, pageable);
        model.addAttribute("name", orderDetailId);

    } else {
        resultPage = orderDetailService.findAll(pageable);
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


//        model.addAttribute("orderDetailPage", resultPage);
    model.addAttribute("page", resultPage);

    return "admin/orderDetails/searchpaginated";
}


}
