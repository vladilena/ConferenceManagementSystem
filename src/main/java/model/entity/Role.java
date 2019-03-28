package model.entity;

public enum Role {
    MODERATOR,
    SPEAKER,
    USER;

    private long id;

    Role() {}

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
