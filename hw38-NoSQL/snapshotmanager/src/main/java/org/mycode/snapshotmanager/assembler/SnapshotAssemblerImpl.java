package org.mycode.snapshotmanager.assembler;

import org.mycode.snapshotmanager.model.MovementInStorage;
import org.mycode.snapshotmanager.model.Product;
import org.mycode.snapshotmanager.model.Snapshot;
import org.mycode.snapshotmanager.model.Storage;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SnapshotAssemblerImpl implements SnapshotAssembler {
    @Override
    public Snapshot assemble(MovementInStorage movement) {
        return new Snapshot(movement.getDate(), new Storage(movement.getStorageId(), movement.getStorageName()),
                Arrays.asList(new Product(movement.getProductId(), movement.getProductName(), movement.getQuantity())));
    }
}
