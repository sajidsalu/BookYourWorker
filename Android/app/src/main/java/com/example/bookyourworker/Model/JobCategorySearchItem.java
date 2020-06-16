package com.example.bookyourworker.Model;

import ir.mirrajabi.searchdialog.core.Searchable;

public class JobCategorySearchItem implements Searchable {

    private String title;

    public JobCategorySearchItem(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
