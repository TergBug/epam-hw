package org.mycode.repository;

import org.mycode.model.Skill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends PagingAndSortingRepository<Skill, Long> {
}
