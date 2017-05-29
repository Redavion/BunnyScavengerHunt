package cs251.vandy.myfirstapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.view.KeyEvent;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static cs251.vandy.myfirstapp.R.id.rightArrow;
import static cs251.vandy.myfirstapp.R.layout.activity_main;
import static cs251.vandy.myfirstapp.R.layout.arrow;
import static cs251.vandy.myfirstapp.R.layout.kitchen;
import static cs251.vandy.myfirstapp.R.layout.test;

public class MainActivity extends AppCompatActivity {
    private Button pillow;
    private Button cookingpot;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    private TextView MainText;
    private int score = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bedroom();
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void decrementScore(){

        ImageView heart1= (ImageView) findViewById(R.id.heart1);

        ImageView heart2= (ImageView) findViewById(R.id.heart2);

        ImageView heart3= (ImageView) findViewById(R.id.heart3);

        if (score==3){
            heart1.setImageResource(R.drawable.emptyheart);
        } else if (score==2){
            heart1.setImageResource(R.drawable.emptyheart);
            heart2.setImageResource(R.drawable.emptyheart);
        } else if (score ==1){
            heart1.setImageResource(R.drawable.emptyheart);
            heart2.setImageResource(R.drawable.emptyheart);
            heart3.setImageResource(R.drawable.emptyheart);
            Toast.makeText(MainActivity.this, "You lose!", Toast.LENGTH_LONG).show();
        }
        score--;
    }
    public void poster(){
        final Button Poster = (Button) findViewById(R.id.poster);
        Poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                layoutInflater= (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final ViewGroup container = (ViewGroup) layoutInflater.inflate(test, null);

                popupWindow= new PopupWindow(container,400,1700,true);
                popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, 500, 500);
                final TextView question= (TextView) container.findViewById(R.id.question);
                question.setText("Great job! What is the 4+9?");
                final EditText response = (EditText)container.findViewById(R.id.answer);
                Button submitAnswer = (Button)container.findViewById(R.id.finalanswer);


                submitAnswer.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){

                        String answer = response.getText().toString();
                if (!answer.equals("13")){
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    decrementScore();

                } else {
                    Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    question.setText("Now go to the kitchen!");
                    MainText.setText("Now go to the kitchen!");
                    Poster.setEnabled(false);
                    ImageView arrow = (ImageView)findViewById(R.id.rightArrow);
                    arrow.setVisibility(View.VISIBLE);
                    arrow.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View arg0, MotionEvent arg1) {
                            kitchen();
                           // ImageView bedroom = (ImageView)findViewById(R.id.bed);
                           // bedroom.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.kitchen));
                            return true;
                        }
                    });
                }
                    }
                });

            }
        });
    }
    public void bedroom(){
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        MainText = (TextView) findViewById(R.id.MainText);
        MainText.setText("Find where Bunny rests his head!");
        pillow = (Button) findViewById(R.id.pillow);

       makeAClickable(pillow, "7", "Now find Bunny's favorite band!");


    }

     public boolean makeAClickable(Button button, final String enteranswer, final String MainTextChange){
        final Button newButton= button;
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final ViewGroup container = (ViewGroup) layoutInflater.inflate(test, null);

                popupWindow = new PopupWindow(container, 400, 1700, true);
                popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, 500, 500);
                final TextView question = (TextView) container.findViewById(R.id.question);
                final EditText text = (EditText) container.findViewById(R.id.answer);
                final Button submitAnswer = (Button) container.findViewById(R.id.finalanswer);

                submitAnswer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String answer = text.getText().toString();
                        if (!answer.equals(enteranswer)) {
                            Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                            decrementScore();
                        } else {
                            Toast.makeText(MainActivity.this, "Good job! Correct!", Toast.LENGTH_SHORT).show();
                            submitAnswer.setVisibility(View.GONE);
                            question.setText("Nice!");
                            MainText.setText(MainTextChange);
                            newButton.setEnabled(false); //turn the current button off
                            poster();

                        }

                    }
                });

                container.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        popupWindow.dismiss();
                        return true;
                    }
                });


            }
        });
         return true;
    }
    public void kitchen(){
        setContentView(R.layout.kitchen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        MainText = (TextView) findViewById(R.id.MainText);
        MainText.setText("Find where Bunny cooks his carrot soup!");

        cookingpot = (Button) findViewById(R.id.cookingpot);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        cookingpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final ViewGroup container = (ViewGroup) layoutInflater.inflate(test, null);

                popupWindow = new PopupWindow(container, 400, 1700, true);
                popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, 500, 500);
                final TextView question = (TextView) container.findViewById(R.id.question);
                question.setText("What does 4! equal?");
                final EditText text = (EditText) container.findViewById(R.id.answer);
                final Button submitAnswer = (Button) container.findViewById(R.id.finalanswer);

                submitAnswer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String answer = text.getText().toString();
                        if (!answer.equals("24")) {
                            Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                            decrementScore();
                        } else {
                            Toast.makeText(MainActivity.this, "Good job! Correct!", Toast.LENGTH_SHORT).show();
                            submitAnswer.setVisibility(View.GONE);
                            question.setText("Nice!");
                            MainText.setText("Now find his BlueBunny ice cream!");
                            freezer();

                        }

                    }
                });

                container.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        popupWindow.dismiss();
                        return true;
                    }
                });


            }

        });

    }
    public void freezer(){
            final Button Freezer = (Button) findViewById(R.id.freezer);
            Freezer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    layoutInflater= (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    final ViewGroup container = (ViewGroup) layoutInflater.inflate(test, null);

                    popupWindow= new PopupWindow(container,400,1700,true);
                    popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, 500, 500);
                    final TextView question= (TextView) container.findViewById(R.id.question);
                    question.setText("Great job! What is the 10*4?");
                    final EditText response = (EditText)container.findViewById(R.id.answer);
                    Button submitAnswer = (Button)container.findViewById(R.id.finalanswer);


                    submitAnswer.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){

                            String answer = response.getText().toString();
                            if (!answer.equals("40")){
                                Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                                decrementScore();

                            } else {
                                Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                                question.setText("Now head to the living room!");
                                MainText.setText("Now head to the living room!");
                                Freezer.setEnabled(false);
                                ImageView arrow = (ImageView)findViewById(R.id.rightArrow);
                                arrow.setVisibility(View.VISIBLE);
                                arrow.setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View arg0, MotionEvent arg1) {
                                        livingRoom();
                                        return true;
                                    }
                                });
                            }
                        }
                    });

                }
            });

    }

    public void livingRoom(){
        setContentView(R.layout.livingroom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        MainText = (TextView) findViewById(R.id.MainText);
        MainText.setText("Find Bunny's grandma!");

        Button grandma = (Button) findViewById(R.id.portrait);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        grandma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final ViewGroup container = (ViewGroup) layoutInflater.inflate(test, null);

                popupWindow = new PopupWindow(container, 400, 1700, true);
                popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, 500, 500);
                final TextView question = (TextView) container.findViewById(R.id.question);
                question.setText("What does the square root of 144 equal?");
                final EditText text = (EditText) container.findViewById(R.id.answer);
                final Button submitAnswer = (Button) container.findViewById(R.id.finalanswer);

                submitAnswer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String answer = text.getText().toString();
                        if (!answer.equals("12")) {
                            Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                            decrementScore();
                        } else {
                            Toast.makeText(MainActivity.this, "Good job! Correct!", Toast.LENGTH_SHORT).show();
                            submitAnswer.setVisibility(View.GONE);
                            question.setText("Nice!");
                            MainText.setText("Now find Bunny's flower!");
                            flower();
                        }

                    }
                });

                container.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        popupWindow.dismiss();
                        return true;
                    }
                });


            }

        });

    }
    public void flower(){
        final Button flower = (Button) findViewById(R.id.flower);
        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                layoutInflater= (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final ViewGroup container = (ViewGroup) layoutInflater.inflate(test, null);

                popupWindow= new PopupWindow(container,400,1700,true);
                popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, 500, 500);
                final TextView question= (TextView) container.findViewById(R.id.question);
                question.setText("Great job! What is the denominator of 54/2?");
                final EditText response = (EditText)container.findViewById(R.id.answer);
                Button submitAnswer = (Button)container.findViewById(R.id.finalanswer);


                submitAnswer.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){

                        String answer = response.getText().toString();
                        if (!answer.equals("2")){
                            Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                            decrementScore();

                        } else {
                            Toast.makeText(MainActivity.this, "You win!", Toast.LENGTH_SHORT).show();
                            question.setText("Congrats you win! Press the arrow to play again");
                            MainText.setText("Congrats you win!");
                            flower.setEnabled(false);
                            ImageView arrow = (ImageView)findViewById(R.id.rightArrow);
                            arrow.setVisibility(View.VISIBLE);
                            arrow.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View arg0, MotionEvent arg1) {
                                    bedroom();
                                    return true;
                                }
                            });
                        }
                    }
                });

            }
        });

    }





}
