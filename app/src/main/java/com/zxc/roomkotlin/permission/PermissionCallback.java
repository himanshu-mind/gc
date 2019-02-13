package com.zxc.roomkotlin.permission;

public interface PermissionCallback {
    void onPermissionResult(int requestCode, PermissionEnum permissionResult);
}
