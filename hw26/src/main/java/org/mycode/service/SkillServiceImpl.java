package org.mycode.service;

import org.mycode.model.Skill;
import org.mycode.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    private SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill getById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return (List<Skill>) skillRepository.findAll();
    }
}
