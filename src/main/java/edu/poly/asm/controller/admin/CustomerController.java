package edu.poly.asm.controller.admin;

import edu.poly.asm.domain.Category;
import edu.poly.asm.domain.Customer;
import edu.poly.asm.model.CategoryDto;
import edu.poly.asm.model.CustomerDto;
import edu.poly.asm.service.CategoryService;
import edu.poly.asm.service.CustomerService;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;


    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "admin/customers/addOrEdit";
    }

    @GetMapping("edit/{customerId}")
    public ModelAndView edit(ModelMap model, @PathVariable("customerId") Long customerId) {
        Optional<Customer> opt = customerService.findById(customerId);
        CustomerDto dto = new CustomerDto();
        if (opt.isPresent()) {
            Customer entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);
            model.addAttribute("customer", dto);
            return new ModelAndView("admin/customers/addOrEdit", model);
        }
        model.addAttribute("message", "Customer not found");
        return new ModelAndView("redirect:admin/customers", model);
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/customers/addOrEdit", model);
        }

        Customer entity = new Customer();
        BeanUtils.copyProperties(dto, entity);
        customerService.save(entity);
        model.addAttribute("message", "Customer saved");

        return new ModelAndView("redirect:/admin/customers/searchpaginated", model);
    }

    @GetMapping("delete/{customerId}")
    public ModelAndView delete(ModelMap model, @PathVariable("customerId") Long customerId) {
        customerService.deleteById(customerId);
        model.addAttribute("message", "Customer deleted successfully");
        return new ModelAndView("forward:/admin/customers/searchpaginated", model);
    }

    @RequestMapping("")
    public String list(ModelMap model) {
        List<Customer> list = customerService.findAll();

        model.addAttribute("customers", list);
        return "admin/customers/list";
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
    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
        Page<Customer> resultPage = null;

        if (StringUtils.hasText(name)) {
            resultPage = customerService.findByNameContaining(name, pageable);
            model.addAttribute("name", name);

        } else {
            resultPage = customerService.findAll(pageable);
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


        model.addAttribute("customerPage", resultPage);

        return "admin/customers/searchpaginated";
    }


}
