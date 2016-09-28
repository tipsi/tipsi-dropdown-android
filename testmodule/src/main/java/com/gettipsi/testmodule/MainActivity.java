package com.gettipsi.testmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
//
//    private static final String STYLE = "{" +
//            "\"style\":" +
//            "    {\"backgroundColor\":\"0xBBBBBB\"," +
//            "    \"borderWidth\":4," +
//            "    \"borderColor\":\"0xFF0000\"," +
//            "    \"cornerRadius\":20," +
//            "    \"separatorHeight\":1," +
//            "    \"separatorColor\":\"0xAAAAAA\"," +
//            "    \"fontName\":\"Arial\"," +
//            "    \"fontSize\":20," +
//            "    \"textColor\":\"0xCCCCCC\"," +
//            "    \"indicatorImageName\":\"custom_triangle.png\"," + //
//            "    \"textAlignment\":\"Left\"" +
//            "    }" +
//            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DropdownContainer dropdownContainer = (DropdownContainer) findViewById(R.id.dropdown);
//        dropdownContainer.setupWithElements(Arrays.<Object>asList("One", "Two", "Three", "Four"));
//        dropdownContainer.setStyle(STYLE);
    }
}
