package edu.poly.asm.controller.admin;

import edu.poly.asm.domain.Category;
import edu.poly.asm.model.AccountDto;
import edu.poly.asm.model.CategoryDto;
import edu.poly.asm.service.CategoryService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("category", new CategoryDto());
        return "admin/categories/addOrEdit";
    }

    @GetMapping("edit/{categoryId}")
    public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long categoryId) {
        Optional<Category> opt = categoryService.findById(categoryId);
        CategoryDto dto = new CategoryDto();
        if (opt.isPresent()) {
            Category entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);
            model.addAttribute("category", dto);
            return new ModelAndView("admin/categories/addOrEdit", model);
        }
        model.addAttribute("message", "Category not found");
        return new ModelAndView("redirect:admin/categories. li", model);
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/categories/addOrEdit", model);
        }

        Category entity = new Category();
        BeanUtils.copyProperties(dto, entity);
        categoryService.save(entity);
        model.addAttribute("message", "Category saved");

        return new ModelAndView("redirect:/admin/categories/searchpaginated", model);
    }

    @GetMapping("delete/{categoryId}")
    public ModelAndView delete(ModelMap model, @PathVariable("categoryId") Long categoryId) {
        categoryService.deleteById(categoryId);
        model.addAttribute("message", "Category deleted successfully");
        return new ModelAndView("forward:/admin/categories/searchpaginated", model);
    }

    @RequestMapping("")
    public String list(ModelMap model) {
        List<Category> list = categoryService.findAll();

        model.addAttribute("categories", list);
        return "admin/categories/list";
    }

    @GetMapping("search")
    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
        List<Category> list = null;

        if (StringUtils.hasText(name)) {
            list = categoryService.findByNameContaining(name);

        } else {
            list = categoryService.findAll();
        }
        model.addAttribute("categories", list);

        return "admin/categories/search";
    }

    @GetMapping("searchpaginated")
    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
        Page<Category> resultPage = null;

        if (StringUtils.hasText(name)) {
            resultPage = categoryService.findByNameContaining(name, pageable);
            model.addAttribute("name", name);

        } else {
            resultPage = categoryService.findAll(pageable);
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


        model.addAttribute("categoryPage", resultPage);

        return "admin/categories/searchpaginated";
    }


}
