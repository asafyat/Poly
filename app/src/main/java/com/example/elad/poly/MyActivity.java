package com.example.elad.poly;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MyActivity extends Activity {

    int polyDeg=2,
        poly=0;
    float polyMember[],
          X1 =0,
          X2 =0;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // create an instance of NumbPad
        tv= (TextView) findViewById(R.id.screenText);
        NumbPad np = new NumbPad();


// optionally set additional title
        //np.setAdditionalText("Please enter a poly number deg");
// show the NumbPad to capture input.
        np.show(this, "Please enter a poly number deg", NumbPad.NOFLAGS,1,
                new NumbPad.numbPadInterface() {
                    // This is called when the user click the 'Ok' button on the dialog
                    // value is the captured input from the dialog.
                    public String numPadInputValue(List<String> values)
                    {
                        try {
                            polyDeg = Integer.parseInt(values.get(0).toString());
                        } catch(NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }
                        polyMember=new float[polyDeg+1];
                        EnterPolynom();
                       return null;
                    }

                    // This is called when the user clicks the 'Cancel' button on the dialog
                    public String numPadCanceled() {
                        // generate a toast message to inform the user that the pin
                        // capture was canceled
                        Toast.makeText(getApplicationContext(),
                                "canceled!", Toast.LENGTH_LONG).show();
                        return null;
                    }

                });
    }

    void EnterPolynom()
    {
        tv.setText("Poly deg is " + polyDeg);
        NumbPad np = new NumbPad();
        np.show(this, "Please enter a poly ^" + (polyDeg-poly), NumbPad.NOFLAGS,1,
                new NumbPad.numbPadInterface() {
                    // This is called when the user click the 'Ok' button on the dialog
                    // value is the captured input from the dialog.
                    public String numPadInputValue(List<String> values)
                    {
                        for (int index=0;index<values.size();index++) {
                            try {
                                polyMember[index] = Integer.parseInt(values.get(index).toString());
                            } catch (NumberFormatException nfe) {
                                System.out.println("Could not parse " + nfe);
                            }
                        }
                        calcPoly();
                        return null;
                    }

                    // This is called when the user clicks the 'Cancel' button on the dialog
                    public String numPadCanceled() {
                        // generate a toast message to inform the user that the pin
                        // capture was canceled
                        Toast.makeText(getApplicationContext(),
                                "canceled!", Toast.LENGTH_LONG).show();
                        return null;
                    }

                });
    }

    private void calcPoly()
    {

        if(polyDeg==2)
        {
            float   a=polyMember[2],
                    b=polyMember[1],
                    c=polyMember[0];
                X1 =(float)((-1*b)+ Math.sqrt(Math.pow((float)b,2)-4*a*c))/2;
                X2 =(float)((-1*b)- Math.sqrt(Math.pow((float)b,2)-4*a*c))/2;
                if(X1!=X2)
                tv.setText("X1 is " + X1 + " x2 is " + X2);
                else
                    tv.setText("X is "+ X1);



        }
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
