package com.zxc.roomkotlin.loc;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxc.roomkotlin.R;
import com.zxc.roomkotlin.permission.PermissionCallback;
import com.zxc.roomkotlin.permission.PermissionEnum;
import com.zxc.roomkotlin.permission.PermissionHelper;

public class LocationFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private PermissionHelper permissionHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionHelper = new PermissionHelper(getActivity(), permissionCallback);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    private PermissionCallback permissionCallback = new PermissionCallback() {
        @Override
        public void onPermissionResult(int requestCode, PermissionEnum permissionResult) {

        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
