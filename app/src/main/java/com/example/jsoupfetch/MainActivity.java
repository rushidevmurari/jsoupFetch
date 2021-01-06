package com.example.jsoupfetch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.label305.asynctask.AsyncTask;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txtitle = findViewById(R.id.txtTitle);
        final TextView txtprice = findViewById(R.id.txtPrice);
        final ImageView img = findViewById(R.id.imgView);
        final TextView txtlocation = findViewById(R.id.txtLocation);
        final TextView txtPayments = findViewById(R.id.txtPayments);
        final RatingBar ratingBar = findViewById(R.id.rating_bar);
        final TextView txtRating = findViewById(R.id.txtRating);
        final TextView txtCondtion = findViewById(R.id.txtCondition);
        new Thread(new Runnable()
        {
            final EbayProduct product = new EbayProduct();
            @Override
            public void run()
            {
                try
                {
                    Document doc = Jsoup.connect("https://www.ebay.com/itm/New-Microsoft-XBOX-ONE-Gamepad-Wireless-Game-Controller-Green/383463277975?_trkparms=aid%3D555021%26algo%3DPL.SIMRVI%26ao%3D1%26asc%3D225086%26meid%3D200c98e690bd48bfbdbee8cecc89278b%26pid%3D100752%26rk%3D6%26rkt%3D17%26mehot%3Dco%26sd%3D302508003098%26itm%3D383463277975%26pmt%3D1%26noa%3D0%26pg%3D2047675%26algv%3DSimplRVIAMLv5WebWithPLRVIOnTopCombiner&_trksid=p2047675.c100752.m1982#rpdCntId")
                            .timeout(6000).get();
                    Elements image = doc.select("#icImg");
                    Elements title = doc.select("#itemTitle");
                    Elements price = doc.select("#vi-mskumap-none #prcIsum");
                    Elements location = doc.select("#itemLocation [itemprop='availableAtOrFrom']");
                    Elements payments = doc.select("#payDet1 img");
                    ArrayList<String> paymentMethods=  new ArrayList<>();
                    for(Element payment : payments)
                    {
                        paymentMethods.add(payment.attr("title"));
                    }
                    Elements condition1 = doc.select("#vi-cond-addl-info");
                    Elements condtion2 = doc.select("#hiddenContent");
                    Elements ratingEle=  doc.select("#rwid .ebay-review-start-rating");

                    product.setTitle(title.text());
                    product.setImagUrl(image.attr("src"));
                    product.setPrice(price.text());
                    product.setLocation(location.text());
                    product.setPayment(paymentMethods.toString());
                    product.setCondition(condition1.text().concat(condtion2.text()));
                    product.setRating(Float.parseFloat(ratingEle.text()));
                }catch (Exception ex){}
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        txtitle.setText(product.getTitle());
                        Picasso.get().load(product.getImagUrl()).into(img);
                        txtprice.setText(product.getPrice());
                        txtlocation.setText(product.getLocation());
                        txtPayments.setText(product.getPayment());
                        txtCondtion.setText(product.getCondition());
                        ratingBar.setRating(product.getRating());
                        txtRating.setText(product.getRating()+"");
                    }
                });
            }
        }).start();
    }
}
