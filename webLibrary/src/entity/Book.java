package entity;

public class Book {
    int id;
    String name;
    float price;
    String author;
    String publishName;

    public Book(int id, String name, float price, String author, String publishName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.publishName = publishName;
    }

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }
}
