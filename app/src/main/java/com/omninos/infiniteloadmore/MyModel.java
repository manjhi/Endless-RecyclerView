package com.omninos.infiniteloadmore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Manjinder Singh on 11 , December , 2019
 */
public class MyModel {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public static class Detail {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("sortname")
        @Expose
        private String sortname;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("phonecode")
        @Expose
        private String phonecode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSortname() {
            return sortname;
        }

        public void setSortname(String sortname) {
            this.sortname = sortname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhonecode() {
            return phonecode;
        }

        public void setPhonecode(String phonecode) {
            this.phonecode = phonecode;
        }

    }
}
