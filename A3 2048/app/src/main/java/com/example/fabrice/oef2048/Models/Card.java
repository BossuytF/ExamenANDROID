package com.example.fabrice.oef2048.Models;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fabrice.oef2048.R;

import java.io.IOException;
import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Fabrice on 22/12/2015.
 */
public class Card extends FrameLayout implements Serializable{

    @Bind(R.id.card_frame_layout_textView)
    TextView txtNumber;

    private int number;

    public void setTxtNumber(TextView txtNumber) {
        this.txtNumber = txtNumber;
    }

    public TextView getTxtNumber() {
        return this.txtNumber;
    }

    public void setNumber(int number) {
        this.number = number;
        if (number != 0){
            txtNumber.setText(Integer.toString(number));
        }
    }

    public int getNumber() {
        return this.number;
    }

    public Card(Context context, AttributeSet attributeSet, int defaultStyle) {
        super(context, attributeSet, defaultStyle);
        inflateLayout();
        ButterKnife.bind(this);
    }

    public Card(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        inflateLayout();
        ButterKnife.bind(this);
    }

    public Card(Context context) {
        super(context);
        inflateLayout();
        ButterKnife.bind(this);
    }

    private void inflateLayout() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater)getContext().getSystemService(infService);
        li.inflate(R.layout.card_frame_layout, this, true);
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeInt(number);
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        number = stream.readInt();
        setNumber(number);
    }
}
