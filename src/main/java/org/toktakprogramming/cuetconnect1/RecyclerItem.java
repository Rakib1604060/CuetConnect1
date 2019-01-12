package org.toktakprogramming.cuetconnect1;

public class RecyclerItem {
    String post;
    String id;
    String name;
    String like;

    public RecyclerItem(String post, String id, String name, String like) {
        this.post = post;
        this.id = id;
        this.name = name;
        this.like = like;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
