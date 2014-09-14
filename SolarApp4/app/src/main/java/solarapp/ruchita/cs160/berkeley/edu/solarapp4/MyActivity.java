package solarapp.ruchita.cs160.berkeley.edu.solarapp4;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MyActivity extends Activity {
    private RadioGroup radioGroupAgeFrom;
    private RadioGroup radioGroupAgeTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button btnCalculate = (Button) findViewById(R.id.btn_submit);
        Button btnClearAll = (Button) findViewById(R.id.btn_clear);

        radioGroupAgeFrom = (RadioGroup) findViewById(R.id.radioGroupAgeFrom);
        radioGroupAgeTo = (RadioGroup) findViewById(R.id.radioGroupAgeTo);

        final TextView textResult = (TextView) findViewById(R.id.text_result_age);
        final EditText inputAge = (EditText) findViewById(R.id.input_age);
        final EditText inputWeight = (EditText) findViewById(R.id.input_weight);

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputAge.setText("");
                inputWeight.setText("");
                textResult.setText("");
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "it works";

                // Input should not be empty
                if (inputAge.getText().toString().isEmpty() || inputWeight.getText().toString().isEmpty()) {
                    textResult.setText("Input Age Entered is Empty, Please try again!!");
                } else {
                    double inputAgeInYears = Double.valueOf(inputAge.getText().toString());
                    double inputWeightPlanet = Double.valueOf(inputWeight.getText().toString());

                    //Get Input Planet index in the radio button
                    int ageFromPlanetIndex = radioGroupAgeFrom.indexOfChild(findViewById(radioGroupAgeFrom.getCheckedRadioButtonId()));
                    int selectedRadioId = radioGroupAgeTo.getCheckedRadioButtonId();
                    int ageToPlanetIndex = radioGroupAgeTo.indexOfChild(findViewById(selectedRadioId));

                    View checkedRadio = radioGroupAgeTo.findViewById(selectedRadioId);
                    int radioId = radioGroupAgeTo.indexOfChild(checkedRadio);
                    RadioButton btnTargetPlanet = (RadioButton) findViewById(selectedRadioId);
                    String targetPlanet = btnTargetPlanet.getText().toString();

                    //Get converted Age and Weight
                    double convertedEarthAgeYears = getConvertedAge(ageFromPlanetIndex, ageToPlanetIndex, inputAgeInYears);
                    double convertedEarthWeight = getConvertedWeight(ageFromPlanetIndex, ageToPlanetIndex, inputWeightPlanet);

                    textResult.setText(" On " + targetPlanet + " age is " + String.format("%.2f", convertedEarthAgeYears) + " years and weight is " + String.format("%.2f", convertedEarthWeight) + " pounds. ");
                }
            }

            public double getConvertedAge(int ageFromPlanetIndex, int ageToPlanetIndex, double inputAgeYears) {
                double[] trans_table = new double[]{87.96, 224.68, 365.26, 686.96, (365.0 * 11.862), (365.0 * 29.456), (365.0 * 84.07), (365.0 * 164.81)};
                double convertedAge = ((trans_table[ageFromPlanetIndex] * inputAgeYears) / trans_table[ageToPlanetIndex]);
                return convertedAge;
            }

            public double getConvertedWeight(int ageFromPlanetIndex, int ageToPlanetIndex, double inputWeightPounds) {
                double[] trans_table = new double[]{0.38, 0.91, 1.0, 0.38, 2.36, 0.91, 0.89, 1.12};
                double convertedWeight = trans_table[ageToPlanetIndex] * inputWeightPounds;
                return convertedWeight;
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}