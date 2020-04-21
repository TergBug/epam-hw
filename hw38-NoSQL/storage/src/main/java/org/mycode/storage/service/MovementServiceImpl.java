package org.mycode.storage.service;

import org.mycode.storage.assembler.MovementAssembler;
import org.mycode.storage.dao.MovementDao;
import org.mycode.storage.dto.MovementDto;
import org.mycode.storage.model.Movement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements MovementService {
    private MovementDao movementDao;
    private MovementAssembler movementAssembler;

    @Autowired
    public MovementServiceImpl(MovementDao movementDao, MovementAssembler movementAssembler) {
        this.movementDao = movementDao;
        this.movementAssembler = movementAssembler;
    }

    public List<MovementDto> getAllMovements(){
        return ((List<Movement>) movementDao.findAll(Sort.by(Sort.Direction.ASC, "date")))
                .stream().map(el -> movementAssembler.assemble(el)).collect(Collectors.toList());
    }
}
