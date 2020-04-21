package org.mycode.snapshotmanager.service;

import org.mycode.snapshotmanager.assembler.SnapshotAssembler;
import org.mycode.snapshotmanager.client.SnapshotClient;
import org.mycode.snapshotmanager.client.StorageClient;
import org.mycode.snapshotmanager.model.MovementInStorage;
import org.mycode.snapshotmanager.model.Product;
import org.mycode.snapshotmanager.model.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SnapshotManagementImpl implements SnapshotManagement {
    private StorageClient storageClient;
    private SnapshotClient snapshotClient;
    private SnapshotAssembler snapshotAssembler;

    @Autowired
    public SnapshotManagementImpl(StorageClient storageClient, SnapshotClient snapshotClient,
                                  SnapshotAssembler snapshotAssembler) {
        this.storageClient = storageClient;
        this.snapshotClient = snapshotClient;
        this.snapshotAssembler = snapshotAssembler;
    }

    @Override
    public void makeSnapshot(int storageId, Date date) {
        List<MovementInStorage> movements = storageClient.getAllMovementInStorage();
        List<Snapshot> snapshots = movements.stream().filter(el -> date.getTime()>el.getDate().getTime())
                .filter(el -> el.getStorageId()==storageId)
                .map(snapshotAssembler::assemble).sorted(Comparator.comparingInt(el -> el.getProducts().get(0).getId()))
                .collect(Collectors.toList());
        Snapshot outputSnapshot = snapshots.get(0);
        List<Product> outputProduct = new ArrayList<>();
        Product lastProduct = null;
        for (int i = 0; i < snapshots.size(); i++) {
            if(lastProduct!=null && snapshots.get(i).getProducts().get(0).getId()==lastProduct.getId()){
                lastProduct.setQuantity(
                        lastProduct.getQuantity() + snapshots.get(i).getProducts().get(0).getQuantity());
            } else {
                outputProduct.add(snapshots.get(i).getProducts().get(0));
            }
            lastProduct = outputProduct.get(outputProduct.size()-1);
        }
        outputSnapshot.setProducts(outputProduct);
        snapshotClient.createSnapshot(outputSnapshot);
    }
}
