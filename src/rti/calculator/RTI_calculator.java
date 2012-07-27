package rti.calculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class RTI_calculator extends Activity {
	private EditText wheelbase;
	private EditText distance;
	private EditText rti;
	private EditText height;
	public static final String PREFS_NAME = "RTI_Perfs";
	private float angle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rti_calculator);
        wheelbase = (EditText) findViewById(R.id.editText1);
        distance = (EditText) findViewById(R.id.editText2);
        rti = (EditText) findViewById(R.id.editText3);
        height = (EditText) findViewById(R.id.editText4);
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	//Preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        this.angle = settings.getFloat("angle", (float)Math.toRadians(30));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_rti_calculator, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	//respond to menu item selection
    	//Launch the settings activity
    	Intent intent = new Intent(this, OptionsPage.class);
    	startActivity(intent);
    	return true;
    }
    
    public void CalcClickHandler(View view) {
        switch (view.getId()) {
        case R.id.button1:
        	//Check to make sure valid numbers have been entered
          if (wheelbase.getText().length() == 0 || distance.getText().length() == 0) {
            Toast.makeText(this, "Please enter a valid number",
                Toast.LENGTH_LONG).show();
            return;
          }

          InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
          imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
          
          float wheelbase_value = Float.parseFloat(wheelbase.getText().toString());
          float distance_value = Float.parseFloat(distance.getText().toString());
          
          float rti_val = calc_rti(wheelbase_value, distance_value);
          float height_val = distance_to_height(angle, distance_value);
          
          rti.setText("RTI: " + String.valueOf(rti_val));
          height.setText(String.valueOf(height_val));
        }
    }
    
    public float calc_rti(float wb, float dis) {
    	//Can find this formula many places, wikipedia also has a good article
    	//http://en.wikipedia.org/wiki/Ramp_travel_index
    	return ((dis/wb)*1000);
    }
    
    public float distance_to_height(float angle, float distance) {
    	//This is a simple trig math
    	return (distance/FloatMath.sin((float) (Math.PI/2)))*FloatMath.sin(angle);
    }
    
    public void WbClickHandler(View view) {
    	switch (view.getId()) {
    	case R.id.button2:
    		wheelbase.setText(String.valueOf(100));
    		break;
    	case R.id.button3:
    		wheelbase.setText(String.valueOf(118));
    		break;
    	case R.id.button4:
    		wheelbase.setText(String.valueOf(119));
    		break;
    	}
    }
    
    public void clearClickHandler(View view) {
    	switch(view.getId()) {
    	case R.id.editText1:
    		//wheelbase.setText("");
    		wheelbase.selectAll();
    		break;
    	case R.id.editText2:
    		//distance.setText("");
    		distance.selectAll();
    		break;
    }
    }
}

