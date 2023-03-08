package com.chatgpt.studentregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatgpt.studentregistration.model.Unit;
import com.chatgpt.studentregistration.repository.UnitRepository;

import java.util.List;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    public Unit getUnitById(Long id) {
        return unitRepository.findById(id).orElse(null);
    }

    public void addUnit(Unit unit) {
        unitRepository.save(unit);
    }

    public void updateUnit(Unit unit) {
        unitRepository.save(unit);
    }

    public void deleteUnitById(Long id) {
        unitRepository.deleteById(id);
    }

}
