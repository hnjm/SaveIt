package saveit.com.saveit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SpendingDetail extends Activity {

    private Spinner spinner;
    private LinearLayout display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Reference to xml file
        setContentView(R.layout.spending_detail);

        spinner = findViewById(R.id.category_spinner);
        display = findViewById(R.id.record_view);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                display.removeAllViews();
                if(i != 0){ // first item in spinner is not a category

                    String category = spinner.getSelectedItem().toString(); // get category

                    // retrieve entries of given category from database
                    // Example data
                    String[] title = {"Gas", "Subway", "Bus"};
                    String[] date = {"3/22", "3/21", "3/3"};
                    double[] amount = {20.0, 2.25, 1.75};

                    // display data by adding TextView to LinearLayout
                    for (int j = 0; j < title.length; j++) {
                        String temp = title[j] + "\n";
                        temp += date[j] + "\n";
                        temp += "$" + amount[j] + "\n";

                        TextView text = createNewTextView(temp);

                        if(j % 2 == 0){
                            text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                        }
                        else{
                            text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryLight));
                        }
                        display.addView(text);
                    }
                }
                else{
                    TextView text = createNewTextView("Please choose category");
                    text.setTextSize(30f);
                    text.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    display.addView(text);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    // Returns a TextView with String text
    private TextView createNewTextView(String text) {
        final TextView textView = new TextView(this);
        textView.setText(text);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setTextSize(18f);
        textView.setLineSpacing(3, 1);
        return textView;
    }

    public void onBack(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
