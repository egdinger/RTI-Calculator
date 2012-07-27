package rti.calculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class OptionsPage extends Activity {
	private float angle;
	private EditText angle_box;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SharedPreferences settings = getSharedPreferences(RTI_calculator.PREFS_NAME, 0);
        
        setContentView(R.layout.activity_options_page);
        
        this.angle = settings.getFloat("angle", (float)Math.toRadians(30));
        angle_box = (EditText) findViewById(R.id.editText1);
        
        angle_box.setText(String.valueOf((float)Math.toDegrees(this.angle)));
    }
	
	public void saveHandler(View view) {
		SharedPreferences settings = getSharedPreferences(RTI_calculator.PREFS_NAME, 0);

		switch(view.getId()) {
    	case R.id.button1:
    		float entered_angle = Float.parseFloat(angle_box.getText().toString());
    		SharedPreferences.Editor editor = settings.edit();
    		editor.putFloat("angle", (float)Math.toRadians(entered_angle));
    		editor.commit();
		}
	}

}
