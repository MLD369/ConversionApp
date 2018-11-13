package com.ledlumtech.conversionapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Weight extends AppCompatActivity {

    // create variables
    private EditText editText;
    private TextView unitText;
    private RadioButton radioButton;
    private TextView result;
    private Button calculate;
    private RadioGroup radioGroup;
    private Button back;

    // on create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        // assign variable to xml layout
        editText = findViewById(R.id.editText);
        unitText = findViewById(R.id.unit);
        result = findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);
        radioGroup = findViewById(R.id.radioGroup);
        back = findViewById(R.id.back);

        // set unit text
        unitText.setText(R.string.lbs);
        // set result to empty string
        result.setText("");

        // create on click method for convert button
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // call convert method when button is clicked
                convert();
            }
        }); // end of calculate on click

        // create onclick method for back button so user can move from page back to main menu
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // call method  to open mainactivity
                Intent intent = MainActivity.makeIntent(Weight.this);
                startActivity(intent);

            }
        }); // end of back on click

    }

    // Method that is ran every time a radio button is clicked
    public void radioButtonClick(View v){
        // int to get the id of the checked radio button
        int radioId = radioGroup.getCheckedRadioButtonId();

        // set variable to checked radio button
        radioButton = findViewById(radioId);

        // set the unit text to match the units of the value to be converted
        if(radioGroup.getCheckedRadioButtonId() == Integer.valueOf(R.id.pounds))
        {
            unitText.setText(R.string.lbs);
        }
        else
        {
            unitText.setText(R.string.kg);
        }

        // display a toast to show that the radio button has been changed
        Toast.makeText(this,"Selected to convert from " + radioButton.getText(),Toast.LENGTH_SHORT).show();
    }// end of radio button click method

    // Method that is called whem regular button is clicked to calculate the converted result
    private void convert() {

        // convert the user input into a double
        double weight = Double.parseDouble(editText.getText().toString());
        String text;



        // find out if the user is converting pounds or kilograms and set the result text to the
        // converted answer
        if(radioGroup.getCheckedRadioButtonId() != Integer.valueOf(R.id.pounds))
        {
            text =editText.getText().toString() + unitText.getText().toString() + " = " + String.format("%.2f",(weight * 2.2046)) + "lbs";
            // set result with the user text , unit text , and the calculated value and units
            result.setText(text);
        }
        else
        {
            text = editText.getText().toString() + unitText.getText().toString() + " = " + String.format("%.2f",(weight/2.2046)) +"kg";
            // set result with the user text , unit text , and the calculated value and units
            result.setText(text);
        }// end if

    }// end of convert method


    // pass intent from any activity to this activity
    // lets this activities make itself in other activity
    public static Intent makeIntent(Context context)
    {
        // context whats passed in
        // weight class is destination
        return new Intent(context, Weight.class);
    }
}
