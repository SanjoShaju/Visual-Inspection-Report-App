package com.santechs.zfapp2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CheckPoints extends AppCompatActivity implements GestureDetector.OnGestureListener{


    private static int step_no = 0;

    public static final String[] text_check_points = {"Step 1 - Check for rust on the piece",
                                                        "Step 2 - Diameter should be thick as shown in the figure",
                                                        "Step 3 - HSS Bore casting should be thick as shown in figure",
                                                        "Step 4 - HSS bore casting should be 280 bore wall thickness",
                                                        "Step 5 - Check the dimension of the lap groove wall thickness",
                                                        "Step 6 - Check the thickness of the PLC bore wall thickness",
                                                        "Step 7 - Check for casting flow shift"};

    public static final int[] img_id ={R.drawable.partpicture,R.drawable.step2,R.drawable.step3,
            R.drawable.step4,R.drawable.step5,R.drawable.step6,R.drawable.step7};

    public static ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();
    private static int bitmapArrayNo =0;

    public static final String phoneNo = "7095883305";

    CheckBox _cb_ok, _cb_notok;
    ImageView _img_pic;
    TextView _tx_steps;
    EditText _in_remarks;
    FloatingActionButton _bt_camera;

    static final int REQUEST_IMAGE_CAPTURE = 1;



    private GestureDetectorCompat detector;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_points1);

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Swipe for next Step",Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        int snackbarTextId = android.support.design.R.id.snackbar_text;
        TextView textView = snackbarView.findViewById(snackbarTextId);
        textView.setTextColor(Color.WHITE);
        snackbar.show();

        initViews();
        _tx_steps.setText(text_check_points[step_no]);

        _img_pic.setImageResource(img_id[step_no]);


        detector = new GestureDetectorCompat(this, this);

        _bt_camera.setOnClickListener(cameraOnClickListener);

        _cb_ok.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if((_cb_ok.isChecked())) {
                    _cb_notok.setChecked(false);
                }
            }
        });

        _cb_notok.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if((_cb_notok.isChecked())) {
                    _cb_ok.setChecked(false);
                    if(step_no == 0){
                        ImageDialogHelper cdd=new ImageDialogHelper(CheckPoints.this);
                        cdd.show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        FileHelper.empty_txt_file();
        step_no = 0;
    }

    private void initViews() {
        _tx_steps = findViewById(R.id.tx_steps);
        _img_pic = findViewById(R.id.img_pic);
        _cb_ok = findViewById(R.id.cb_ok);
        _cb_notok = findViewById(R.id.cb_notok);
        _in_remarks = findViewById(R.id.in_remarks);
        _bt_camera = findViewById(R.id.bt_camera);



    }

    private View.OnClickListener cameraOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            try {

                Bitmap imgbitmap = (Bitmap) extras.get("data");
                bitmapArray.add(imgbitmap);



                _in_remarks.setText("Fig : " + (bitmapArrayNo+1));
                bitmapArrayNo++;
            }
            catch (Exception e) {

                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    private void stepChanger(){
        boolean checked = (_cb_ok).isChecked();
        String ok_OR_notok = "";
        if(checked) {
            ok_OR_notok = "OK";
        }

        else {
            ok_OR_notok = "NOT OK";
        }
        String full_str = text_check_points[step_no] + "," + ok_OR_notok + "," + _in_remarks.getText().toString()+"\n";
        FileHelper.saveToFile(full_str);
        if(step_no<=5) {
            step_no = step_no + 1;
            _tx_steps.setText(text_check_points[step_no]);
            _in_remarks.setText("");

            _cb_ok.setChecked(false);
            _cb_notok.setChecked(false);
            _img_pic.setImageResource(img_id[step_no]);
        }
        else{
            step_no = 0;
            _in_remarks.setText("");
            try{
            Intent intent = new Intent(CheckPoints.this, txtViewHelper.class);
            startActivity(intent);

            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
            this.finish();
        }

    }



    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if(!(_cb_ok.isChecked()) && !(_cb_notok.isChecked())){
            Toast.makeText(getApplicationContext(), "None of the above is selected",Toast.LENGTH_LONG).show();
        }
        else if(!(_cb_ok.isChecked())){
            new AlertDialog.Builder(this)
                    //set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                    .setTitle("Send SMS")
                    //set message
                    .setMessage("Do you want to inform about the fault to the person in-charge?")
                    //set positive button
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sendSMS();
                            stepChanger();
                        }
                    })
                    //set negative button
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            stepChanger();
                        }
                    })
                    .show();
        }
        else
            stepChanger();
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void sendSMS(){

        String sms_body = text_check_points[step_no] + " --- " + _in_remarks.getText().toString()+"\n";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, sms_body, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",Toast.LENGTH_LONG).show();
        } catch (Exception e) {

            Toast.makeText(getApplicationContext(),"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
