package com.example.elad.poly;



    import android.app.Activity;
    import android.app.AlertDialog;
    import android.app.AlertDialog.Builder;
    import android.content.DialogInterface;
    import android.graphics.Color;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;

    import java.util.ArrayList;
    import java.util.List;

public class NumbPad {
    // flag values
    public static int NOFLAGS = 0;
    public static int HIDE_INPUT = 1;
    public static int HIDE_PROMPT = 2;

    static Float amountDue;

    static TextView prompt;
    static TextView promptValue;
    static TextView polyText;

    static Button btn1;
    static Button btn2;
    static Button btn3;
    static Button btn4;
    static Button btn5;
    static Button btn6;
    static Button btn7;
    static Button btn8;
    static Button btn9;
    static Button btn0;
    static Button btnC;
    static Button btnDot;
    static Button buttonNext;

    private String value = "";
    private String addl_text = "";
    private NumbPad me;

    List<String> listOfString = new ArrayList<String>();

    private int mNumOfParameters;
    private int index;

    private int flag_hideInput = 0;
    private int flag_hidePrompt = 0;

    public interface numbPadInterface {
        public String numPadInputValue(List<String> values );
        public String numPadCanceled();
    }

    public String getValue() {
        return value;
    }

    public void setAdditionalText(String inTxt) {
        addl_text = inTxt;
    }

    public void show(final Activity a, final String promptString, int inFlags,int numOfParam,
                     final numbPadInterface postrun) {
        me = this;
        index=0;
        mNumOfParameters=numOfParam;
        flag_hideInput = inFlags % 2;
        flag_hidePrompt = (inFlags / 2) % 2;

        Builder dlg = new AlertDialog.Builder(a);
        if (flag_hidePrompt == 0) {
            dlg.setTitle(promptString);
        }
        // Inflate the dialog layout
        LayoutInflater inflater = a.getLayoutInflater();
        View iView = inflater.inflate(R.layout.numb_pad, null, false);

        // create code to handle the change tender
        prompt = (TextView) iView.findViewById(R.id.promptText);
        prompt.setText(addl_text);
        if (addl_text.equals("")) {
            prompt.setVisibility(View.GONE);
        }
        promptValue = (TextView) iView.findViewById(R.id.promptValue);

        // Defaults
        value = "";
        promptValue.setText("");

        btn1 = (Button) iView.findViewById(R.id.button1);
        btn2 = (Button) iView.findViewById(R.id.button2);
        btn3 = (Button) iView.findViewById(R.id.button3);
        btn4 = (Button) iView.findViewById(R.id.button4);
        btn5 = (Button) iView.findViewById(R.id.button5);
        btn6 = (Button) iView.findViewById(R.id.button6);
        btn7 = (Button) iView.findViewById(R.id.button7);
        btn8 = (Button) iView.findViewById(R.id.button8);
        btn9 = (Button) iView.findViewById(R.id.button9);
        btn0 = (Button) iView.findViewById(R.id.button0);
        btnC = (Button) iView.findViewById(R.id.buttonC);
        btnDot = (Button) iView.findViewById(R.id.buttonDot);
        buttonNext= (Button) iView.findViewById(R.id.buttonNext);

        polyText = (TextView)iView.findViewById(R.id.polygonText);

        btnC.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                value = "";
                promptValue.setText("");
            }
        });
        btn1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                appendNumber("1");
            }
        });
        btn2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                appendNumber("2");
            }
        });
        btn3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                appendNumber("3");
            }
        });
        btn4.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                appendNumber("4");
            }
        });
        btn5.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                appendNumber("5");
            }
        });
        btn6.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                appendNumber("6");
            }
        });
        btn7.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                appendNumber("7");
            }
        });
        btn8.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                appendNumber("8");
            }
        });
        btn9.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                appendNumber("9");
            }
        });
        btn0.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                appendNumber("0");
            }
        });
        btnDot.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                appendNumber(".");
            }
        });

        listOfString = new ArrayList<String>();


        buttonNext.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                SetNextParameter();
            }
        });

        dlg.setView(iView);
        dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int sumthin) {
                listOfString.add(promptValue.getText().toString());
                dlg.dismiss();
                postrun.numPadInputValue(listOfString);
            }
        });
        dlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int sumthin) {
                dlg.dismiss();
                postrun.numPadCanceled();
            }
        });
        dlg.show();
    }

    void appendNumber(String inNumb) {
        value = value + inNumb;
        if (flag_hideInput == 1) {
            promptValue.setText(promptValue.getText() + "*");
        } else {
            promptValue.setText(promptValue.getText() + inNumb);
        }
    }

    void SetNextParameter()
    {
        listOfString.add(promptValue.getText().toString());
        if ((mNumOfParameters-index)>0)
            polyText.setText(polyText.getText()+ "+" + promptValue.getText().toString()+"X^"+(mNumOfParameters-index));
        else
            polyText.setText(polyText.getText()+ "+" + promptValue.getText().toString());

        promptValue.setText("");
        index++;
    }
}
