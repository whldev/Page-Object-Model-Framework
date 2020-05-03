package com.planittesting.jupitertoys.model.api.responses;

import com.planittesting.jupitertoys.model.api.toys.ToyModel;

import java.util.List;

public class ToysResponse {

    private List<ToyModel> toys;

    public List<ToyModel> getToys() {
        return toys;
    }

    public void setToys(List<ToyModel> toys) {
        this.toys = toys;
    }




}
