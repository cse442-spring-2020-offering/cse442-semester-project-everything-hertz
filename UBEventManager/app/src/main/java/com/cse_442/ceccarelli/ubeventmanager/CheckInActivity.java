package com.cse_442.ceccarelli.ubeventmanager;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckInActivity extends AppCompatActivity implements View.OnClickListener  {

    private TextView checkInResult, checkInResultPoints;
    private IntentIntegrator qrScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        Button buttonScan;
        buttonScan = findViewById(R.id.buttonScan);
        checkInResult = findViewById(R.id.checkInResult);
        checkInResultPoints = findViewById(R.id.checkInResult_points);
        //intializing scan object
        qrScan = new IntentIntegrator(this);
        //attaching onclick listener
        buttonScan.setOnClickListener(this);
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
//                displayResult.setText("Unable to check in");
            } else {
                //if qr contains data
                try {
                    System.out.println(result.getContents());
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    checkInResult.setText(obj.getString("event_id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode to a toast
//                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    checkInResult.setText("Unable to check in to the event");
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onClick(View view) {
        qrScan.initiateScan();
    }

}
