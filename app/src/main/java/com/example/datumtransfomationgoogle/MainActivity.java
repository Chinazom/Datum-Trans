package com.example.datumtransfomationgoogle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity implements OnItemSelectedListener {
    EditText latText;
    EditText longitudeText;
    EditText heightText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListToSpinner();
        //addListToSpinner2();
        addListToSpinner3();
        latText = findViewById(R.id.lat);
        longitudeText = findViewById(R.id.longitude);
       heightText = findViewById(R.id.height);
        Intent intent = getIntent();
        String lat = intent.getStringExtra("lat");
        String longitude = intent.getStringExtra("longitude");
        //Spinner spinner2 = findViewById(R.id.spinner_epoch);
        latText.setText(lat);
        longitudeText.setText(longitude);

    }

    // this method adds the string array list to the spinner
    public void addListToSpinner() {


        Spinner spinner = findViewById(R.id.spinner_datum);
// Create an ArrayAdapter using the string array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.datum_list, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    // this method adds the string array list to the spinner
    public void addListToSpinner3() {

        Spinner spinner3 = findViewById(R.id.spinner_transformation_technique);
        // Create an ArrayAdapter using the string array
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.method_list, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);
        editTextListener();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        String datum[] = {"ITRF_2005", "ITRF_2000", "ITRF_1997", "ITRF_1996", "ITRF_1994", "ITRF_1993", "ITRF_1992", "ITRF_1991", "ITRF_1990", "ITRF_1989","ETRF_2014","ETRF_2000"};

        Toast.makeText(MainActivity.this,
                "Selected Option" + position,
                Toast.LENGTH_LONG).show();

        int datumLength = datum.length;
        EditText epochText = findViewById(R.id.epochDate);
        CheckBox epochCheck = findViewById(R.id.checkbox);
        for (int i = 0; i < datumLength; i++) {
            String text = datum[i];

            if (item.equals(text)){
                if (position <= 9){
                    epochCheck.setChecked(true);
                    epochText.setVisibility(View.VISIBLE);
                    epochText.setText("2000");
                }else if (position > 9){
                    epochCheck.setChecked(true);
                    epochText.setVisibility(View.VISIBLE);
                    epochText.setText("2010");
                }
            }else if (item.equals(text) && position > 9){
                epochCheck.setChecked(true);
                epochText.setVisibility(View.VISIBLE);
                epochText.setText("2010");
            }
        }
        TextView latText = findViewById(R.id.XTextView);
        TextView longitudeText = findViewById(R.id.YTextView);
        TextView heightText = findViewById(R.id.ZTextView);
        if (item.equals("Abridged Molodenski")){
            latText.setText("Latitude");
            longitudeText.setText("Longitude");
            heightText.setText("height");

        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //TODO Auto-generated method stub
    }

    // this Checks and throws error when the number is less than 4 digit.
    public void editTextListener() {
        final EditText epochTextView = findViewById(R.id.epochDate);
        epochTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (epochTextView.getText().toString().length() < 4) {
                    epochTextView.setError("Epoch should not be less than 4 numbers");
                } else {
                    epochTextView.setError(null);
                }
            }
        });
    }

    // this method is used to make the epoch edit text visible
    public void displayEpoch(View view) {
        CheckBox epochCheckBox = findViewById(R.id.checkbox);
        EditText epochTextView = findViewById(R.id.epochDate);

        if (epochCheckBox.isChecked()) {
            epochTextView.setVisibility(View.VISIBLE);
        } else if (!epochCheckBox.isChecked()) {
            epochTextView.setVisibility(View.GONE);
        }
    }

    String datum;
    String epoch;
    String method;
    String X;
    String Y;
    String Z;

    // onclick method is used to get the text from the spinner list
    public void getSelectedDisplay(View view) {
        Spinner spinner = findViewById(R.id.spinner_datum);
        EditText epochTextView = findViewById(R.id.epochDate);

        Spinner spinner3 = findViewById(R.id.spinner_transformation_technique);
        datum = (String) spinner.getSelectedItem();
        //epoch = (String) spinner2.getSelectedItem();
        epoch = epochTextView.getText().toString();
        X = latText.getText().toString();
        Y = longitudeText.getText().toString();
         Z= heightText.getText().toString();
        method = (String) spinner3.getSelectedItem();
        Toast.makeText(MainActivity.this,
                "Selected Option" +
                        "\nDatum: " + datum +
                        "\nEpoch: " + epoch +
                        "\nX: " + X +
                        "\nY: " + Y +
                        "\nheight: " + Z +
                        "\nTransformation Technique : " + method,
                Toast.LENGTH_LONG).show();
        getIneachparam indiviParam = new getIneachparam();
        indiviParam.getparameters(datum,method,X,Y,Z);
        Toast.makeText(MainActivity.this,
                "Selected Option"  ,
                Toast.LENGTH_LONG).show();
        disp();
    }
    public void disp () {
        String E = TransformationTechnique.getCoordinate();
        TextView xyzText = findViewById(R.id.xyzText);
        xyzText.setText(E);
    }

}
