package com.glasses_store.order.entity;

public class Custom {
    private String custom_name;
    private String custom_id;
    private String custom_ph;

    public Custom(String custom_name, String custom_id, String custom_ph) {
        this.custom_name = custom_name;
        this.custom_id = custom_id;
        this.custom_ph = custom_ph;
    }

    public String getCustom_name() {
        return custom_name;
    }

    public void setCustom_name(String custom_name) {
        this.custom_name = custom_name;
    }

    public String getCustom_id() {
        return custom_id;
    }

    public void setCustom_id(String custom_id) {
        this.custom_id = custom_id;
    }

    public String getCustom_ph() {
        return custom_ph;
    }

    public void setCustom_ph(String custom_ph) {
        this.custom_ph = custom_ph;
    }
}
