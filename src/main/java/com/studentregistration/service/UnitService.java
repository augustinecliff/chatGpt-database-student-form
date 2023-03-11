package com.studentregistration.service;

import org.springframework.stereotype.Service;

import com.studentregistration.model.Unit;
import com.studentregistration.repository.UnitRepository;

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

    public Unit getUnitById(int id) {
        return unitRepository.findById(id).orElse(null);
    }

    public void addUnit(Unit unit) {
        unitRepository.save(unit);
    }

    public void updateUnit(Unit unit) {
        unitRepository.save(unit);
    }

    public void deleteUnitById(int id) {
        unitRepository.deleteById(id);
    }

}
