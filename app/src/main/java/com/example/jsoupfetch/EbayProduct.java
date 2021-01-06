package com.example.jsoupfetch;

public class EbayProduct
{
    public String getImagUrl()
    {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl)
    {
        this.imagUrl = imagUrl;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getPayment()
    {
        return payment;
    }

    public void setPayment(String payment)
    {
        this.payment = payment;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public float getRating()
    {
        return rating;
    }

    public void setRating(float rating)
    {
        this.rating = rating;
    }

    public String getCondition()
    {
        return condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }


    private String imagUrl;
    private String title;
    private String price;
    private String payment;
    private String location;
    private float rating;
    private String condition;

}
