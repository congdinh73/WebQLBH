package edu.poly.asm.controller.admin;

import edu.poly.asm.domain.Account;
import edu.poly.asm.domain.Category;
import edu.poly.asm.model.AccountDto;
import edu.poly.asm.service.AccountService;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;


    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("account", new AccountDto());
        return "admin/accounts/addOrEdit";
    }

    @GetMapping("edit/{username}")
    public ModelAndView edit(ModelMap model, @PathVariable("username") String username) {
        Optional<Account> opt = accountService.findById(username);
        AccountDto dto = new AccountDto();
        if (opt.isPresent()) {
            Account entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);
            dto.setPassword("");
            model.addAttribute("account", dto);
            return new ModelAndView("admin/accounts/addOrEdit", model);
        }
        model.addAttribute("message", "Account not found");
        return new ModelAndView("redirect:admin/accounts", model);
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("account") AccountDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/accounts/addOrEdit", model);
        }

        Account entity = new Account();
        BeanUtils.copyProperties(dto, entity);
        accountService.save(entity);
        model.addAttribute("message", "Account saved");

        return new ModelAndView("redirect:/admin/accounts/searchpaginated", model);
    }

    @GetMapping("delete/{username}")
    public ModelAndView delete(ModelMap model, @PathVariable("username") String username) {
        accountService.deleteById(username);
        model.addAttribute("message", "Account deleted successfully");
        return new ModelAndView("forward:/admin/accounts/searchpaginated", model);
    }

    @RequestMapping("")
    public String list(ModelMap model) {
        List<Account> list = accountService.findAll();

        model.addAttribute("accounts", list);
        return "admin/accounts/list";
    }
//
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
public String search(ModelMap model, @RequestParam(name = "username", required = false) String username,
                     @RequestParam("page") Optional<Integer> page,
                     @RequestParam("size") Optional<Integer> size) {

    int currentPage = page.orElse(1);
    int pageSize = size.orElse(5);

    Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("username"));
    Page<Account> resultPage = null;

    if (StringUtils.hasText(username)) {
        resultPage = accountService.findByUsernameContaining(username, pageable);
        model.addAttribute("name", username);

    } else {
        resultPage = accountService.findAll(pageable);
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


    model.addAttribute("accountPage", resultPage);

    return "admin/accounts/searchpaginated";
}


}
