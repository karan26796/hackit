package app.hackit.hackamigo.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.hackit.hackamigo.R;

public class SoilDetectionActivity extends BaseActivity implements View.OnClickListener {

    EditText mMoistureEt, mNitrogenEt, mPhosphorousEt, mPotassiumEt;
    Button mAnalyseSoilBtn;
    TextView mAnalysisTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_detection);
        setUpToolbar(this);

        mAnalysisTv = findViewById(R.id.text_soil_test_result);

        mAnalyseSoilBtn = findViewById(R.id.button_analyze);

        mMoistureEt = findViewById(R.id.edit_text_moisture);
        mNitrogenEt = findViewById(R.id.edit_text_nitrogen);
        mPhosphorousEt = findViewById(R.id.edit_text_phosphorous);
        mPotassiumEt = findViewById(R.id.edit_text_potassium);

        mAnalyseSoilBtn.setOnClickListener(this);
    }

    @Override
    protected int getToolbarID() {
        return R.id.soil_detection_activity_toolbar;
    }

    private String parameterDisplay() {
        if ((TextUtils.isEmpty(mPotassiumEt.getText().toString())
                || TextUtils.isEmpty(mNitrogenEt.getText().toString())
                || TextUtils.isEmpty(mPhosphorousEt.getText().toString())
                || TextUtils.isEmpty(mMoistureEt.getText().toString()))) {
            Toast.makeText(this, "One or more text values empty",
                    Toast.LENGTH_SHORT).show();
            return "One or more text values empty";
        } else {
            return "Moisture: " + mMoistureEt.getText().toString() +"\n"+
                    "Nitrogen: " + mNitrogenEt.getText().toString() +"\n"+
                    "Phosphorous: " + mPhosphorousEt.getText().toString() +"\n"+
                    "Potassium: " + mPotassiumEt.getText().toString();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_analyze:
                mAnalysisTv.setText(parameterDisplay());
                break;
        }
    }
}
