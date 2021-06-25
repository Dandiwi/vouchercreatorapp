package com.example.vouchercreator.Controller;

import com.example.vouchercreator.Model.Pakiet;
import com.example.vouchercreator.Model.VoucherForm;
import com.example.vouchercreator.Service.VoucherService;
import com.example.vouchercreator.Service.pakietyService;
import com.example.vouchercreator.repository.PakietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class VoucherFormController {

    @Autowired
    private PakietyRepository pakietyRepository;

    @Autowired pakietyService pakietyService;


    // display list of wishes
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("voucher", new VoucherForm());
        List<Pakiet> pakiety = pakietyService.getAllPakiets();
        System.out.println(Arrays.toString(pakiety.toArray()));
        model.addAttribute("pakiety", pakiety);
//        Pakiet pakiet = pakietyService.getPakietById(id);
//        model.addAttribute("pakiet", pakiet);
        return "index";
    }

    @PostMapping("/printVoucher")
    public String saveWish(@ModelAttribute("VoucherForm") VoucherForm voucherForm) {
        //save wish to db
        VoucherService.printVoucher(voucherForm);
        return "redirect:/";
    }

    @GetMapping("/pakiety")
    public void getAllPakiety(Model model) {
        List<Pakiet> pakiety = pakietyRepository.findAll();
        model.addAttribute("pakiety", pakiety);
    }
}