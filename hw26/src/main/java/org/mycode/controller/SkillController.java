package org.mycode.controller;

import org.mycode.model.Skill;
import org.mycode.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
public class SkillController {
    private SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping(value = "skills/{id}")
    public Skill getById(@PathVariable Long id) {
        return skillService.getById(id);
    }

    @GetMapping(value = "skills")
    public List<Skill> getById() {
        return skillService.getAll();
    }
}
