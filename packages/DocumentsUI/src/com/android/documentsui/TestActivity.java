/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.documentsui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestActivity extends Activity {
    private TextView mResult;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        final Context context = this;

        final LinearLayout view = new LinearLayout(context);
        view.setOrientation(LinearLayout.VERTICAL);

        final CheckBox multiple = new CheckBox(context);
        multiple.setText("ALLOW_MULTIPLE");
        view.addView(multiple);

        Button button;
        button = new Button(context);
        button.setText("OPEN_DOC */*");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                if (multiple.isChecked()) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
                startActivityForResult(intent, 42);
            }
        });
        view.addView(button);

        button = new Button(context);
        button.setText("OPEN_DOC image/*");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                if (multiple.isChecked()) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
                startActivityForResult(intent, 42);
            }
        });
        view.addView(button);

        button = new Button(context);
        button.setText("OPEN_DOC text/plain, application/msword");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[] {
                        "text/plain", "application/msword" });
                if (multiple.isChecked()) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
                startActivityForResult(intent, 42);
            }
        });
        view.addView(button);

        button = new Button(context);
        button.setText("CREATE_DOC text/plain");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TITLE, "foobar.txt");
                startActivityForResult(intent, 42);
            }
        });
        view.addView(button);

        mResult = new TextView(context);
        view.addView(mResult);

        setContentView(view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mResult.setText("resultCode=" + resultCode + ", data=" + String.valueOf(data));
    }
}