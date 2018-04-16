package com.shira.hw1;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends Activity implements View.OnClickListener {

    private final int MAX_CARDS = 20;

    private int [] cardsId = {R.id.card_0, R.id.card_1, R.id.card_2, R.id.card_3, R.id.card_4,
            R.id.card_5, R.id.card_6, R.id.card_7, R.id.card_8, R.id.card_9, R.id.card_10,
            R.id.card_11, R.id.card_12, R.id.card_13, R.id.card_14,
            R.id.card_15, R.id.card_16, R.id.card_17, R.id.card_18, R.id.card_19};

    private int[] drawableId = {R.drawable.pic0, R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8,
            R.drawable.pic9, R.drawable.back_card};

    private int[] cardsPositions;    //Holds the assigned positions of the cards

    private ImageView[] imageviews;
    //private TextView foundPairsLabel;
    //private TextView turns_taken_label;
    private TextView timerTextField;
    private TextView timelable;
    private Bundle bund;
    private Handler handler;
    private CountDownTimer timerThread;
    private boolean back;
    private int delay;
    private int flippedCards;
    private int currentIndex = -1;
    private int lastIndex = -1;
    private int foundPairs = 0;
    //private int turns_taken = 0;
    private int numCrads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        bund = intent.getExtras();
        int level = (int) bund.get("level");
        int time;

        ((TextView)findViewById(R.id.text_view_player)).setText(bund.getString("my_name"));
        handler = new Handler();
        timerTextField = (TextView) this.findViewById(R.id.text_view_timer);
        timelable = (TextView) this.findViewById(R.id.text_view_time);

        time = settingsByLevel(level);

        timerThread = new CountDownTimer(time, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextField.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                Toast.makeText(GameActivity.this, "Your Time Is Up!", Toast.LENGTH_LONG).show();
                timelable.setText("You ");
                timerThread.cancel();
                timerTextField.setText("Lose!");
                quit();
            }
        }.start();

        foundPairs = 0;
//        foundPairsLabel = (TextView) GameActivity.this.findViewById(R.id.pairs_counter);
//        turns_taken_label = (TextView) MainActivity.this.findViewById(R.id.turns);

        //create a new cards array by the selected level
        cardsPositions = new int[numCrads];

        //set the card at each position to -1 (unset)
        for (int i = 0; i < numCrads; i++) {
            cardsPositions[i] = -1;
        }

        imageviews = new ImageView[MAX_CARDS];
        for (int i = 0; i < MAX_CARDS; i++) {
            if (i < numCrads) {
                imageviews[i] = (ImageView) findViewById(cardsId[i]);
            } else {
                ((ImageView) findViewById(cardsId[i])).setVisibility(View.INVISIBLE);
            }
        }


        //for each card loop through.
        setImageRandomPosition();

        //set click listeners for each view & set image to back card
        for (int i = 0; i < numCrads; i++) {
            ((ImageView) findViewById(cardsId[i])).setOnClickListener(this);
            ((ImageView) findViewById(cardsId[i])).setImageResource(R.drawable.back_card);
        }


    }

    private void setImageRandomPosition() {
        Random random = new Random();
        for (int i = 0; i < numCrads / 2; i++) {
            //each card goes in 2 slots
            for (int j = 0; j < 2; j++) {
                //generate a random slot
                int randomSlot = random.nextInt(numCrads);
                //make sure that the slot isn't already populated
                while (cardsPositions[randomSlot] != -1) {
                    randomSlot = random.nextInt(numCrads);
                }
                //set this card to that slot
                cardsPositions[randomSlot] = i;
            }
        }
    }

    @Override
    public void onClick(View v) {

        back = false;
        delay = 0;
        int index = Integer.parseInt((String) v.getTag());

        for (int i = 0; i < numCrads; i++) {
            //determine which id we're dealing with
            if (v.getId() == cardsId[i]) {
                //set the face up image for each
                index = i;
                ((ImageView) findViewById(cardsId[i])).setImageResource(drawableId[cardsPositions[i]]);
                imageviews[i].setFocusable(false);
                imageviews[i].setClickable(false);
                break;
            }
        }
        flippedCards++;

        if (flippedCards == 2) {

            currentIndex = index;

            for (int i = 0; i < MAX_CARDS; i++) {
                if (i < numCrads) {
                    imageviews[i].setFocusable(false);
                    imageviews[i].setClickable(false);
                }
            }
            flippedCards = 0;
            handler.postDelayed(flipCardsBack, 1000);
        } else {
            lastIndex = index;
        }
    }

    Runnable flipCardsBack = new Runnable() {
        public void run() {
            //Check if the two cards with the same image
            if (cardsPositions[currentIndex] == cardsPositions[lastIndex]) {
                ((ImageView) findViewById(cardsId[lastIndex])).setVisibility(View.VISIBLE);
                ((ImageView) findViewById(cardsId[currentIndex])).setVisibility(View.VISIBLE);

                foundPairs++;
                //foundPairsLabel.setText(String.valueOf(foundPairs));    //Update label of matches

                if (foundPairs == numCrads / 2) {
                    win();
                }

            }
            //Cards with different image
            else {
                ((ImageView) findViewById(cardsId[currentIndex])).setImageResource(R.drawable.back_card);
                ((ImageView) findViewById(cardsId[lastIndex])).setImageResource(R.drawable.back_card);
            }

            for (int i = 0; i < MAX_CARDS; i++) {
                if (i < numCrads) {
                    imageviews[i].setFocusable(true);
                    imageviews[i].setClickable(true);
                }
            }
        }
    };

    @Override
    public void onBackPressed() {
        back = true;
        quit();
    }

    public void quit() {
        if (back) {
            delay = 0;
            back = false;
        } else {
            delay = 4000;
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timerThread.cancel();
                GameActivity.this.finish();
            }
        }, delay);

    }

    private void win() {

        ((LinearLayout) this.findViewById(R.id.outcome_layout)).setVisibility(View.VISIBLE);
        timelable.setText("Good ");
        timerThread.cancel();
        timerTextField.setText("Job!");
        quit();

    }

    private int settingsByLevel(int level) {
        int time;
        if(level == 1) {
            time = 30000;
            numCrads = 12;
        }
        else if(level == 2) {
            time = 45000;
            numCrads = 16;
        }
        else {
            time = 60000;
            numCrads = 20;
        }
        return time;
    }
}
