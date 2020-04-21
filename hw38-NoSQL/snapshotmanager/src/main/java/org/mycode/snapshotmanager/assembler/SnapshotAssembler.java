package org.mycode.snapshotmanager.assembler;

import org.mycode.snapshotmanager.model.MovementInStorage;
import org.mycode.snapshotmanager.model.Snapshot;

public interface SnapshotAssembler {
    Snapshot assemble(MovementInStorage movement);
}
