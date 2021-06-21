package com.example.vouchercreator.Controller;

import com.example.vouchercreator.Model.VoucherForm;
import com.example.vouchercreator.Service.VoucherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VoucherFormController {


    // display list of wishes
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("voucher", new VoucherForm());
        return "index";
    }

    @PostMapping("/printVoucher")
    public String saveWish(@ModelAttribute("VoucherForm") VoucherForm voucherForm) {
        //save wish to db
        VoucherService.printVoucher(voucherForm);
        return "redirect:/";
    }
}