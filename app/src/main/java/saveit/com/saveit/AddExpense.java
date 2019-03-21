package saveit.com.saveit;

import android.app.Activity;
import android.os.Bundle;

// Add Expense page
public class AddExpense extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Reference to xml file
        setContentView(R.layout.add_expense);
    }

}
