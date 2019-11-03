package com.sample.batch.jobs;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PartitionInfo implements Serializable {

    private static final long serialVersionUID = 6650718199648553346L;

    int index;

    int gridSize;

    public PartitionInfo(int index, int gridSize) {
        this.index = index;
        this.gridSize = gridSize;
    }
}
