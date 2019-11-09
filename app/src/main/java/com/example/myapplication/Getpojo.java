package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Getpojo {


    /**
     * message : Famous spots get successfully
     * spots : {"current_page":1,"data":[{"id":113,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/dummynews.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":112,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":111,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":110,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":109,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":108,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":107,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":106,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":105,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":104,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""}],"first_page_url":"http://1.6.98.141/Mobile/beaver/api/famousSpot?page=1","from":1,"last_page":12,"last_page_url":"http://1.6.98.141/Mobile/beaver/api/famousSpot?page=12","next_page_url":"http://1.6.98.141/Mobile/beaver/api/famousSpot?page=2","path":"http://1.6.98.141/Mobile/beaver/api/famousSpot","per_page":10,"prev_page_url":"","to":10,"total":113}
     */

    @SerializedName("message")
    private String message;
    @SerializedName("spots")
    private SpotsBean spots;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SpotsBean getSpots() {
        return spots;
    }

    public void setSpots(SpotsBean spots) {
        this.spots = spots;
    }

    public static class SpotsBean {
        /**
         * current_page : 1
         * data : [{"id":113,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/dummynews.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":112,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":111,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":110,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":109,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":108,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":107,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":106,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":105,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""},{"id":104,"name":"Chandigarh","description":"Chandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beautyChandigarh the city beauty\r\nChandigarh the city beauty","address":"Chandigarh","image":"http://1.6.98.141/Mobile/beaver/public/images/news/image1.jpg","created_at":"2019-05-07 00:00:00","updated_at":""}]
         * first_page_url : http://1.6.98.141/Mobile/beaver/api/famousSpot?page=1
         * from : 1
         * last_page : 12
         * last_page_url : http://1.6.98.141/Mobile/beaver/api/famousSpot?page=12
         * next_page_url : http://1.6.98.141/Mobile/beaver/api/famousSpot?page=2
         * path : http://1.6.98.141/Mobile/beaver/api/famousSpot
         * per_page : 10
         * prev_page_url :
         * to : 10
         * total : 113
         */

        @SerializedName("current_page")
        private int currentPage;
        @SerializedName("first_page_url")
        private String firstPageUrl;
        @SerializedName("from")
        private int from;
        @SerializedName("last_page")
        private int lastPage;
        @SerializedName("last_page_url")
        private String lastPageUrl;
        @SerializedName("next_page_url")
        private String nextPageUrl;
        @SerializedName("path")
        private String path;
        @SerializedName("per_page")
        private int perPage;
        @SerializedName("prev_page_url")
        private String prevPageUrl;
        @SerializedName("to")
        private int to;
        @SerializedName("total")
        private int total;
        @SerializedName("data")
        private List<DataBean> data;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public String getFirstPageUrl() {
            return firstPageUrl;
        }

        public void setFirstPageUrl(String firstPageUrl) {
            this.firstPageUrl = firstPageUrl;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public String getLastPageUrl() {
            return lastPageUrl;
        }

        public void setLastPageUrl(String lastPageUrl) {
            this.lastPageUrl = lastPageUrl;
        }

        public String getNextPageUrl() {
            return nextPageUrl;
        }

        public void setNextPageUrl(String nextPageUrl) {
            this.nextPageUrl = nextPageUrl;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }

        public String getPrevPageUrl() {
            return prevPageUrl;
        }

        public void setPrevPageUrl(String prevPageUrl) {
            this.prevPageUrl = prevPageUrl;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }


    }

    public static class DataBean {
        /**
         * id : 113
         * name : Chandigarh
         * description : Chandigarh the city beauty
         Chandigarh the city beauty
         Chandigarh the city beauty
         Chandigarh the city beautyChandigarh the city beauty
         Chandigarh the city beautyChandigarh the city beauty
         Chandigarh the city beautyChandigarh the city beauty
         Chandigarh the city beautyChandigarh the city beauty
         Chandigarh the city beauty
         * address : Chandigarh
         * image : http://1.6.98.141/Mobile/beaver/public/images/news/dummynews.jpg
         * created_at : 2019-05-07 00:00:00
         * updated_at :
         */

        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("description")
        private String description;
        @SerializedName("address")
        private String address;
        @SerializedName("image")
        private String image;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}


