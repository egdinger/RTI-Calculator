package rti.calculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class OptionsPage extends Activity {
	private float angle;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SharedPreferences settings = getSharedPreferences(RTI_calculator.PREFS_NAME, 0);
        
        setContentView(R.layout.activity_options_page);
        
        this.angle = settings.getFloat("angle", (float)Math.toRadians(30));
        EditText angle_box = (EditText) findViewById(R.id.editText1);
        
        angle_box.setText(String.valueOf((float)Math.toDegrees(this.angle)));
    }

}
