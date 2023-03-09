package com.chatgpt.studentregistration.controller;

import com.chatgpt.studentregistration.model.Unit;
import com.chatgpt.studentregistration.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/units")
public class UnitController {

    @Autowired
    private UnitRepository unitRepository;

    @GetMapping("/myUnits")
    public String showUnits(Model model) {
        List<Unit> units = unitRepository.findAll();
        model.addAttribute("units",units);
        return "units";
    }

    @GetMapping("/add")
    public String showAddUnitForm(Model model) {
        model.addAttribute("unit",new Unit());
        return "add-unit";
    }

    @PostMapping("/add")
    public String addUnit(@ModelAttribute("unit") Unit unit) {
        unitRepository.save(unit);
        return "redirect:/units/myUnits";
    }

    @GetMapping("/edit/{id}")
    public String showEditUnitForm(@PathVariable("id") int id,Model model) {
        Optional<Unit> unit = unitRepository.findById(id);
        if(unit.isPresent()) {
            model.addAttribute("unit", unit.get());
            return "edit-unit";
        }
        else return "redirect:/units/";
    }

    @PostMapping("/edit")
    public String editUnit(@ModelAttribute("unit") Unit unit) {
        unitRepository.save(unit);
        return "redirect:/units";
    }

    @GetMapping("/delete/{id}")
    public String deleteUnit(@PathVariable("id") int id) {
        unitRepository.deleteById(id);
        return "redirect:/units";
    }
}
