package com.kstmsoft;

import java.io.Serializable;

public class Course implements Serializable {
    private String id;
    private String name;
    private int credits;
    private int quota;

    public Course(String id, String name, int credits, int quota) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.quota = quota;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public int getQuota() {
        return quota;
    }
}
