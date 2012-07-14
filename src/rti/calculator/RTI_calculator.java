package rti.calculator;

import android.app.Activity;
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
	public static final String PREFS_NAME = "RTI_Perfs";
	private float angle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rti_calculator);
        wheelbase = (EditText) findViewById(R.id.editText1);
        distance = (EditText) findViewById(R.id.editText2);
        
        //wheelbase.setOnClickListener();
        
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
    	return true;
    }
    
    public void CalcClickHandler(View view) {
        switch (view.getId()) {
        case R.id.button1:
          if (wheelbase.getText().length() == 0 || distance.getText().length() == 0) {
            Toast.makeText(this, "Please enter a valid number",
                Toast.LENGTH_LONG).show();
            return;
          }

          InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
          imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
          
          float wheelbase_value = Float.parseFloat(wheelbase.getText().toString());
          float distance_value = Float.parseFloat(distance.getText().toString());
          
          float rti = calc_rti(wheelbase_value, distance_value);
          float artic = articulation_height(angle, distance_value);
          
          EditText rti_txt = (EditText) findViewById(R.id.editText3);
          rti_txt.setText("RTI: " + String.valueOf(rti));
          EditText artic_txt = (EditText) findViewById(R.id.editText4);
          artic_txt.setText(String.valueOf(artic));
        }
    }
    
    public float calc_rti(float wb, float dis) {
    	return ((dis/wb)*1000);
    }
    
    public float articulation_height(float angle, float distance) {
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
    		wheelbase.setText("");
    		break;
    	case R.id.editText2:
    		distance.setText("");
    		break;
    }
    }
}

