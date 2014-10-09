package com.example.elad.poly;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;



public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // create an instance of NumbPad
        TextView tv= (TextView) findViewById(R.id.screenText);
        NumbPad np = new NumbPad();
// optionally set additional title
        np.setAdditionalText("Please enter a poly number");
// show the NumbPad to capture input.
        np.show(this, "Please enter element", NumbPad.NOFLAGS,
                new NumbPad.numbPadInterface() {
                    // This is called when the user click the 'Ok' button on the dialog
                    // value is the captured input from the dialog.
                    public String numPadInputValue(String value) {
                        if (value.equals("1234")) {
                            // do something her
                            Toast.makeText(getApplicationContext(),
                                    "Pin is CORRECT! What do you want me to do?", Toast.LENGTH_LONG).show();
                        } else {
                            // generate a toast message to inform the user that
                            // the captured input is not valid
                            Toast.makeText(getApplicationContext(),
                                    "Manager Pin is incorrect", Toast.LENGTH_LONG).show();
                        }
                        return null;
                    }

                    // This is called when the user clicks the 'Cancel' button on the dialog
                    public String numPadCanceled() {
                        // generate a toast message to inform the user that the pin
                        // capture was canceled
                        Toast.makeText(getApplicationContext(),
                                "Pin capture canceled!", Toast.LENGTH_LONG).show();
                        return null;
                    }
                });
        tv.setText("aaaaaaaaa" + np.getValue());
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
