package saveit.com.saveit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import java.text.DecimalFormat;

public class Overview extends Activity {

    private int cat = 6;        // 6 categories: Clothing, Food, Housing, Medical, Transport, Other
    private double[] amount = new double[cat];
    private double[] percent = new double[cat];
    private DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Reference to xml file
        setContentView(R.layout.overview);

        Button[] btnAmt = { (Button)findViewById(R.id.clothingAmt), (Button)findViewById(R.id.foodAmt),
                (Button)findViewById(R.id.housingAmt), (Button)findViewById(R.id.medicalAmt),
                (Button)findViewById(R.id.transportAmt), (Button)findViewById(R.id.otherAmt)};

        Button[] btnPerc = { (Button)findViewById(R.id.clothingPerc), (Button)findViewById(R.id.foodPerc),
                (Button)findViewById(R.id.housingPerc), (Button)findViewById(R.id.medicalPerc),
                (Button)findViewById(R.id.transportPerc), (Button)findViewById(R.id.otherPerc)};

        // get data for amount and percent
        // set amount and percent text
        // mock data

        double total = 0; // to calculate percentage
        for(int i = 0; i < cat; i++){
            amount[i] = Math.random() * 1000;
            total += amount[i];

            btnAmt[i].setText("" + df.format(amount[i]));
        }

        for(int i = 0; i< cat; i++){
            percent[i] = amount[i] / total * 100;
            btnPerc[i].setText("" + df.format(percent[i]));
        }
    }

}
