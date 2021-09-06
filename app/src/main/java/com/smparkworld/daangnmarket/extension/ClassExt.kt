package com.smparkworld.daangnmarket.extension

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.smparkworld.daangnmarket.DaangnApp.Companion.REQUEST_PERMISSION_CODE
import com.smparkworld.daangnmarket.R

fun Fragment.showRequestPermissionDialog() {
    AlertDialog.Builder(requireContext())
        .setMessage(R.string.error_permissionLocation)
        .setNegativeButton("취소", null)
        .setPositiveButton("설정으로 가기") { _, _ ->
            startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${requireContext().packageName}")))
        }
        .show()
}

fun Fragment.getLastLocation(
        success: (Location) -> Unit
): Boolean {

    val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    )
    if ( ContextCompat.checkSelfPermission(requireContext(), permissions[0]) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(requireContext(), permissions[1]) != PackageManager.PERMISSION_GRANTED) {

        if ( ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permissions[0])
                || ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permissions[1]) ) {

            showRequestPermissionDialog()
        } else {
            requestPermissions(permissions, REQUEST_PERMISSION_CODE)
        }
        return false
    }

    LocationServices.getFusedLocationProviderClient(requireActivity())
            .lastLocation
            .addOnSuccessListener { success(it) }
            .addOnFailureListener { it.printStackTrace() }
    return true
}

fun Fragment.setKeyboard(show: Boolean, focus: EditText) {
    val imm = requireActivity().getSystemService<InputMethodManager>()
    if (show) {
        imm?.showSoftInput(focus, 0)
    } else {
        imm?.hideSoftInputFromWindow(focus.windowToken, 0);
    }
}

fun Fragment.showSnackbar(id: Int) {
    view?.let {
        Snackbar.make(it, id, Snackbar.LENGTH_SHORT).show()
        return
    }
    Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showSnackbar(id: Int) {
    Snackbar.make(
            (window.findViewById(android.R.id.content) as ViewGroup).getChildAt(0),
            id,
            Snackbar.LENGTH_SHORT
    ).show()
}