package com.kstmsoft;

import java.io.Serializable;

public class Request implements Serializable {
    private final String query;
    private final String[] args;

    public Request(String query, String... args) {
        this.query = query;
        this.args = args;
    }

    public String getQuery() {
        return query;
    }

    public String[] getArgs() {
        return args;
    }
}
