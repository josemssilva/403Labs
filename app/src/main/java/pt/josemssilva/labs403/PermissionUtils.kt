package pt.josemssilva.labs403

import android.Manifest
import android.R
import android.app.Activity
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AlertDialog

fun Activity.hasPermission(permission: String): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        PackageManager.PERMISSION_GRANTED == checkSelfPermission(permission)
    } else false
}

fun Activity.alertAndFinish() {
    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
    builder.setMessage("Permissions Denied")

    // Add the buttons.
    builder.setPositiveButton(
        R.string.ok,
        DialogInterface.OnClickListener { _, _ -> finish() })
    val dialog: AlertDialog = builder.create()
    dialog.show()
}

fun Activity.canAccessCoarseLocation(): Boolean {
    return hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) && hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
}

fun Activity.canReadPhoneState(): Boolean {
    return hasPermission(Manifest.permission.READ_PHONE_STATE)
}