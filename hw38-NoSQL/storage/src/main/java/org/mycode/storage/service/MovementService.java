package org.mycode.storage.service;

import org.mycode.storage.dto.MovementDto;

import java.util.List;

public interface MovementService {
    List<MovementDto> getAllMovements();
}
