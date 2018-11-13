package com.ledlumtech.conversionapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Temperature extends AppCompatActivity {

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
        setContentView(R.layout.activity_temperature);

        // assign variable to xml layout
         editText = findViewById(R.id.editText);
        editText.addTextChangedListener(editTextWatcher);
         unitText = findViewById(R.id.unit);
         result = findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);
         radioGroup = findViewById(R.id.radioGroup);
         back = findViewById(R.id.back);

        // set unit text
        unitText.setText("°F");
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
                Intent intent = MainActivity.makeIntent(Temperature.this);
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
       if(radioGroup.getCheckedRadioButtonId() == Integer.valueOf(R.id.fahrenheit))
        {
            unitText.setText(R.string.f);
        }
        else
        {
            unitText.setText(R.string.c);
        }

        if(editText.getText().toString().trim().length() != 0 )
        {
            convert();
        }
        // display a toast to show that the radio button has been changed
        Toast.makeText(this,"Selected to convert from " + radioButton.getText(),Toast.LENGTH_SHORT).show();
    }// end of radio button click method

    // Method that is called whem regular button is clicked to calculate the converted temperature
    private void convert() {

        // convert the user input into a double
        double temperature = Double.parseDouble(editText.getText().toString());

        String text;


        // find out if the user is converting fahrenheit or celsius and set the result text to the
        // converted answer
        if(radioGroup.getCheckedRadioButtonId() != Integer.valueOf(R.id.fahrenheit))
        {
            calculateF(temperature);
        }
        else
        {
            calculateC(temperature);
        }// end if

    }// end of convert method

    private final TextWatcher editTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,int before, int count) {
            try {
                convert();
            }
            catch (NumberFormatException e) {
                result.setText("");

            }

        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
    };

    private void calculateF(double temperature )
    {
        String text;
        text = editText.getText().toString() + unitText.getText().toString() + " = " + String.format("%.2f", (32 + 1.8 * temperature)) + "°F";
        // set result with the user text , unit text , and the calculated value and units
        result.setText(text);
    }

    private void calculateC(double  temperature)
    {
        String text;
        text = editText.getText().toString() + unitText.getText().toString() + " = " + (String.format("%.2f", (temperature - 32)/1.8)) +"°C";
        // set result with the user text , unit text , and the calculated value and units
        result.setText(text);
    }

    // pass intent from any activity to this activity
    // lets this activities make itself in other activity
   public static Intent makeIntent(Context context)
    {
        // context whats passed in
        // Temperature class is destination
        return new Intent(context, Temperature.class);
    }// end of makeintent method




} // end of temperature class
