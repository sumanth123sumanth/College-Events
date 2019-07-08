package com.amrita.dscaseb;

import android.net.Uri;

public class event {
    private String name;
    Uri url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getUrl() {
        return url;
    }

    public event(String name, Uri url) {
        this.name = name;
        this.url = url;
    }

    public void setUrl(Uri url) {
        this.url = url;
    }
}
