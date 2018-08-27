package com.santechs.zfapp2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ImageDialogHelper extends Dialog{

    public static int[] bt_id = {
            R.id.a0,R.id.a1,R.id.a2,R.id.a3,R.id.a4,R.id.a5,R.id.a6,R.id.a7,R.id.a8,R.id.a9,R.id.a10,R.id.a11,R.id.a12,R.id.a13,R.id.a14,
            R.id.b0,R.id.b1,R.id.b2,R.id.b3,R.id.b4,R.id.b5,R.id.b6,R.id.b7,R.id.b8,R.id.b9,R.id.b10,R.id.b11,R.id.b12,R.id.b13,R.id.b14,
            R.id.c0,R.id.c1,R.id.c2,R.id.c3,R.id.c4,R.id.c5,R.id.c6,R.id.c7,R.id.c8,R.id.c9,R.id.c10,R.id.c11,R.id.c12,R.id.c13,R.id.c14,
            R.id.d0,R.id.d1,R.id.d2,R.id.d3,R.id.d4,R.id.d5,R.id.d6,R.id.d7,R.id.d8,R.id.d9,R.id.d10,R.id.d11,R.id.d12,R.id.d13,R.id.d14,
            R.id.e0,R.id.e1,R.id.e2,R.id.e3,R.id.e4,R.id.e5,R.id.e6,R.id.e7,R.id.e8,R.id.e9,R.id.e10,R.id.e11,R.id.e12,R.id.e13,R.id.e14,
            R.id.f0,R.id.f1,R.id.f2,R.id.f3,R.id.f4,R.id.f5,R.id.f6,R.id.f7,R.id.f8,R.id.f9,R.id.f10,R.id.f11,R.id.f12,R.id.f13,R.id.f14,
            R.id.g0,R.id.g1,R.id.g2,R.id.g3,R.id.g4,R.id.g5,R.id.g6,R.id.g7,R.id.g8,R.id.g9,R.id.g10,R.id.g11,R.id.g12,R.id.g13,R.id.g14,
            R.id.h0,R.id.h1,R.id.h2,R.id.h3,R.id.h4,R.id.h5,R.id.h6,R.id.h7,R.id.h8,R.id.h9,R.id.h10,R.id.h11,R.id.h12,R.id.h13,R.id.h14,
            R.id.i0,R.id.i1,R.id.i2,R.id.i3,R.id.i4,R.id.i5,R.id.i6,R.id.i7,R.id.i8,R.id.i9,R.id.i10,R.id.i11,R.id.i12,R.id.i13,R.id.i14,
            R.id.j0,R.id.j1,R.id.j2,R.id.j3,R.id.j4,R.id.j5,R.id.j6,R.id.j7,R.id.j8,R.id.j9,R.id.j10,R.id.j11,R.id.j12,R.id.j13,R.id.j14,
            R.id.k0,R.id.k1,R.id.k2,R.id.k3,R.id.k4,R.id.k5,R.id.k6,R.id.k7,R.id.k8,R.id.k9,R.id.k10,R.id.k11,R.id.k12,R.id.k13,R.id.k14,
            R.id.l0,R.id.l1,R.id.l2,R.id.l3,R.id.l4,R.id.l5,R.id.l6,R.id.l7,R.id.l8,R.id.l9,R.id.l10,R.id.l11,R.id.l12,R.id.l13,R.id.l14};

    public static String[] position = {
            "A0","A1","A2","A3","A4","A5","A6","A7","A8","A9","A10","A11","A12","A13","A14",
            "B0","B1","B2","B3","B4","B5","B6","B7","B8","B9","B10","B11","B12","B13","B14",
            "C0","C1","C2","C3","C4","C5","C6","C7","C8","C9","C10","C11","C12","C13","C14",
            "D0","D1","D2","D3","D4","D5","D6","D7","D8","D9","D10","D11","D12","D13","D14",
            "E0","E1","E2","E3","E4","E5","E6","E7","E8","E9","E10","E11","E12","E13","E14",
            "F0","F1","F2","F3","F4","F5","F6","F7","F8","F9","F10","F11","F12","F13","F14",
            "G0","G1","G2","G3","G4","G5","G6","G7","G8","G9","G10","G11","G12","G13","G14",
            "H0","H1","H2","H3","H4","H5","H6","H7","H8","H9","H10","H11","H12","H13","H14",
            "I0","I1","I2","I3","I4","I5","I6","I7","I8","I9","I10","I11","I12","I13","I14",
            "J0","J1","J2","J3","J4","J5","J6","J7","J8","J9","J10","J11","J12","J13","J14",
            "K0","K1","K2","K3","K4","K5","K6","K7","K8","K9","K10","K11","K12","K13","K14",
            "L0","L1","L2","L3","L4","L5","L6","L7","L8","L9","L10","L11","L12","L13","L14"};

    public Activity c;
    public Button bt,btn;



    public ImageDialogHelper(@NonNull Context context) {
        super(context);
        this.c = (Activity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_dialog);

        for(int i=0;i<bt_id.length;i++){

            btn = findViewById(bt_id[i]);
            btn.setText(position[i]);
            btn.setOnClickListener(buttonOnClickListener);
        }
    }

    View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b= (Button)v;

            TextView txtView = (TextView) ((Activity)c).findViewById(R.id.in_remarks);
            txtView.setText("Rust is at " + b.getText().toString());
            dismiss();
        }
    };

}

