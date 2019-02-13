package com.zxc.roomkotlin.permission;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zxc.roomkotlin.R;

import java.util.ArrayList;

public class PermissionActivity extends AppCompatActivity {

    private static final String TAG = "PermissionActivity";

    private PermissionHelper permissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        permissionHelper = new PermissionHelper(this, permissionCallback);
    }

    public void askPermissions(View view) {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.RECORD_AUDIO);
        permissionHelper.processPermission(permissions, "Test", 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private PermissionCallback permissionCallback = new PermissionCallback() {
        @Override
        public void onPermissionResult(int requestCode, PermissionEnum permissionResult) {
            Log.e(TAG, "onPermissionResult: " + requestCode + " - " + permissionResult.name());
        }
    };
}
