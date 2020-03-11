package org.mycode.service;

import org.mycode.model.Skill;

import java.util.List;

public interface SkillService {
    Skill getById(Long id);

    List<Skill> getAll();
}
