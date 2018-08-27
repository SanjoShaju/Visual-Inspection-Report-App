package com.santechs.zfapp2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class txtViewHelper extends AppCompatActivity {
    Button _bt_send,_bt_test;
    TextView _txt_saved_text;
    LinearLayout _layoutTxtView;

    private static String pdfFilenme = "data.pdf";

    FileHelper ma = new FileHelper();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.txtviewer1);

        initViews();
        setText();
        dynamic_layout();

        _bt_send.setOnClickListener(sendOnClickListener);

        _bt_test.setOnClickListener(testOnClickListener);


    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        FileHelper.empty_txt_file();
        this.finish();
    }


    private View.OnClickListener sendOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            File file = new File(ma.path + ma.fileName);
            String str = file.getAbsolutePath();
            Toast.makeText(txtViewHelper.this,str,Toast.LENGTH_SHORT).show();
            sendIntentToGmailApp(file);
        }
    };


    private View.OnClickListener testOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bitmap b = null;
            b = getScreenShot(_layoutTxtView);

            File file = new File(FileHelper.path , pdfFilenme);
            try {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document,new FileOutputStream(file.getAbsolutePath())); //  Change pdf's name.
                document.open();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG, 100, stream);
                Image img = Image.getInstance(stream.toByteArray());
                float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                        - document.rightMargin() - 0) / img.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.
                img.scalePercent(scaler);
                img.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
                document.add(img);
                document.close();


                Uri path = FileProvider.getUriForFile(txtViewHelper.this,getPackageName()+".provider", file);
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_SUBJECT, "Send Text File As Attachment Example");
                email.putExtra(Intent.EXTRA_TEXT, "");
                email.putExtra(Intent.EXTRA_STREAM, path);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Send Text File"));



            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }


        }
    };

    public static Bitmap getScreenShot(View view) {

        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;


    }



    private void initViews(){
        _txt_saved_text = findViewById(R.id.txt_saved_text);
        _bt_send = findViewById(R.id.bt_send);
        _layoutTxtView = findViewById(R.id.layoutTxtView);
        _bt_test = findViewById(R.id.bt_test);
    }

    private void dynamic_layout(){
        LinearLayout parent = new LinearLayout(this);
        parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        parent.setOrientation(LinearLayout.HORIZONTAL);
        for(int i =0; i < CheckPoints.bitmapArray.size();i++){
            LinearLayout sub = new LinearLayout(parent.getContext());
            sub.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            sub.setOrientation(LinearLayout.VERTICAL);
            sub.setGravity(Gravity.CENTER);
            sub.setPadding(2,2,2,2);
            ImageView img = new ImageView(this);
            img.setImageBitmap(CheckPoints.bitmapArray.get(i));
            sub.addView(img);
            TextView txt = new TextView(this);
            txt.setText("Fig : " + (i+1));
            sub.addView(txt);
            parent.addView(sub);
        }
        _layoutTxtView.addView(parent);
    }


    public void setText(){
        String str = FileHelper.ReadFile(txtViewHelper.this);
        _txt_saved_text.setText(str);
    }

    private void sendIntentToGmailApp(File file) {
    try
    {
            if (file != null) {
                Uri path = FileProvider.getUriForFile(txtViewHelper.this,getPackageName()+".provider", file);
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_SUBJECT, "Send Text File As Attachment Example");
                email.putExtra(Intent.EXTRA_TEXT, "");
                email.putExtra(Intent.EXTRA_STREAM, path);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Send Text File"));
                this.finish();

            }
    }
    catch (Exception e)
    {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    }

}
