package org.mycode.storage.assembler;

import org.mycode.storage.dto.MovementDto;
import org.mycode.storage.model.Movement;

public interface MovementAssembler {
    Movement assemble(MovementDto dto);
    MovementDto assemble(Movement model);
}
