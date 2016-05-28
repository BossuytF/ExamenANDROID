package com.example.fabrice.oef2048.Models;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.GridLayout;

import com.example.fabrice.oef2048.R;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Fabrice on 22/12/2015.
 */
public class Board extends GridLayout implements Serializable{

    Card[][] cardBoard;

    public Card[][] getCardBoard(){
        return cardBoard;
    }

    public void setCardBoard(Card[][] cardBoard){
        this.cardBoard = cardBoard;
    }

    public Board(Context context, AttributeSet attributeSet, int defaultStyle) {
        super(context, attributeSet, defaultStyle);
        initBoard(context);
    }

    public Board(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initBoard(context);
    }

    public Board(Context context) {
        super(context);
        initBoard(context);
    }

    public void initBoard(Context context) {
        //Initialize the cardBoard[][] and populate it
        cardBoard = new Card[4][4];

        for(int rij=0; rij<4; rij++) {
            for(int kolom=0; kolom<4; kolom++) {
                Card card = new Card(getContext());

                cardBoard[rij][kolom] = card;
            }
        }
        addNumbers();
        inflateLayout(context);
    }

    public void inflateLayout(Context context){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater)getContext().getSystemService(infService);
        li.inflate(R.layout.board_grid_layout, this, true);

        int cardMeasure = getCardMeasure(context);
        addCardBoardToGridLayout(cardMeasure);
    }

    private void addCardBoardToGridLayout(int cardMeasure) {
        setRowCount(4);
        setColumnCount(4);

        removeAllViews();

        for(int rij=0; rij<4; rij++) {
            for(int kolom=0; kolom<4; kolom++) {
                Card card = cardBoard[rij][kolom];
                addView(card, cardMeasure, cardMeasure);
            }
        }
    }

    private int getCardMeasure(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        return screenWidth/4;
    }

    public void addNumbers(){
        Random random = new Random();
        for (int i = 0; i < 2; i++){
            int randomInt = random.nextInt(4);
            int randomInt2 = random.nextInt(4);

            Card card = cardBoard[randomInt][randomInt2];
            if (card.getNumber() == 0){
                card.setNumber(2);
            }else{
                i--;
            }
        }
    }
}
