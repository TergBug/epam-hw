package org.mycode.storage.assembler;

import org.mycode.storage.dto.MovementDto;
import org.mycode.storage.model.Movement;
import org.mycode.storage.model.Product;
import org.mycode.storage.model.Storage;
import org.springframework.stereotype.Component;

@Component
public class MovementAssemblerImpl implements MovementAssembler {

    @Override
    public Movement assemble(MovementDto dto) {
        return new Movement(dto.getId(), dto.getDate(), new Storage(dto.getStorageId(), dto.getStorageName()),
                new Product(dto.getProductId(), dto.getProductName()), dto.getQuantity());
    }

    @Override
    public MovementDto assemble(Movement model) {
        return new MovementDto(model.getId(), model.getDate(), model.getStorage().getId(), model.getStorage().getName(),
                model.getProduct().getId(), model.getProduct().getName(), model.getQuantity());
    }
}
