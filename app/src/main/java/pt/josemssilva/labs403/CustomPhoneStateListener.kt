package pt.josemssilva.labs403

import android.telephony.*
import android.util.Log

class CustomPhoneStateListener(
    val callback: (String) -> Unit
) : PhoneStateListener() {
    /**
     * ServiceState.STATE_EMERGENCY_ONLY
     * ServiceState.STATE_IN_SERVICE
     * ServiceState.STATE_OUT_OF_SERVICE
     * ServiceState.STATE_POWER_OFF
     */
    override fun onServiceStateChanged(serviceState: ServiceState) {
        super.onServiceStateChanged(serviceState)
        Log.d(TAG, "onServiceStateChanged() called with serviceState = [$serviceState]")
    }

    override fun onSignalStrengthChanged(asu: Int) {
        super.onSignalStrengthChanged(asu)
        Log.d(TAG, "onSignalStrengthChanged() called with asu = [$asu]")
    }

    override fun onMessageWaitingIndicatorChanged(mwi: Boolean) {
        super.onMessageWaitingIndicatorChanged(mwi)
        Log.d(TAG, "onMessageWaitingIndicatorChanged() called with mwi = [$mwi]")
    }

    override fun onCallForwardingIndicatorChanged(cfi: Boolean) {
        super.onCallForwardingIndicatorChanged(cfi)
        Log.d(TAG, "onCallForwardingIndicatorChanged() called with cfi = [$cfi]")
    }

    override fun onCellLocationChanged(location: CellLocation) {
        super.onCellLocationChanged(location)
        Log.d(TAG, "onCellLocationChanged() called with location = [$location]")
    }

    override fun onCallStateChanged(state: Int, incomingNumber: String) {
        super.onCallStateChanged(state, incomingNumber)
        Log.d(
            TAG,
            "onCallStateChanged() called with state = [$state], incomingNumber = [$incomingNumber]"
        )
    }

    override fun onDataConnectionStateChanged(state: Int) {
        super.onDataConnectionStateChanged(state)
        Log.d(TAG, "onDataConnectionStateChanged() called with state = [$state]")
    }

    override fun onDataConnectionStateChanged(state: Int, networkType: Int) {
        super.onDataConnectionStateChanged(state, networkType)
        Log.d(
            TAG,
            "onDataConnectionStateChanged() called with state = [$state], networkType = [$networkType]"
        )
    }

    override fun onDataActivity(direction: Int) {
        super.onDataActivity(direction)
        Log.d(TAG, "onDataActivity() called with direction = [$direction]")
    }

    /**
     * signalStrength.isGsm()                   GSM 2G or 3G
     * signalStrength.getCdmaDbm();             3G
     * signalStrength.getCdmaEcio();            3G
     * signalStrength.getEvdoDbm();             3G
     * signalStrength.getEvdoEcio();            3G
     * signalStrength.getEvdoSnr();             3G
     * signalStrength.getGsmSignalStrength();   2G
     * signalStrength.getGsmBitErrorRate();     2G
     */
    override fun onSignalStrengthsChanged(signalStrength: SignalStrength) {
        super.onSignalStrengthsChanged(signalStrength)
        Log.d(
            TAG,
            "onSignalStrengthsChanged() called with signalStrength :$signalStrength;"
        )

//            if (mTelephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
//                mTvSignalInfo.setText("GSM Strength" + signalStrength.getGsmSignalStrength());
//            } else if (mTelephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
//                mTvSignalInfo.setText("CDMA Strength" + signalStrength.getCdmaDbm() + " dBm");
//            } else {
//                mTvSignalInfo.setText("Unknown PhoneType: " + mTelephonyManager.getPhoneType());
//            }

        signalStrength.run {
            level.also {
                println("Value from Callback -> $it")
                callback("$it")
            }

            cellSignalStrengths.forEach { signalStrength ->
                val type = when (signalStrength) {
                    is CellSignalStrengthLte -> "LTE"
                    is CellSignalStrengthNr -> "5G"
                    is CellSignalStrengthGsm -> "Gsm"
                    is CellSignalStrengthCdma -> "Cdma"
                    else -> "unknown"
                }
                println("$type -> ${signalStrength.level}")
            }
        }
    }

    override fun onCellInfoChanged(cellInfo: List<CellInfo>) {
        super.onCellInfoChanged(cellInfo)
        Log.d(TAG, "onCellInfoChanged() called with cellInfo = [$cellInfo]")
    }

    companion object {
        const val TAG = "CustomPhoneStateListener"
    }
}


//private val cellInfoCallback = @RequiresApi(Build.VERSION_CODES.S)
//    object : TelephonyCallback(), TelephonyCallback.CellInfoListener {
//        override fun onCellInfoChanged(cellInfo: MutableList<CellInfo>) {
//            cellInfo.forEach { info ->
//                println("Status ${info.cellConnectionStatus} ; isRegistered ${info.isRegistered} ; Asu ${info.cellSignalStrength.asuLevel} ; Level ${info.cellSignalStrength.level} ")
//            }
//        }
//    }
//
//    private val signalStrengthCallback = @RequiresApi(Build.VERSION_CODES.S)
//    object : TelephonyCallback(), TelephonyCallback.SignalStrengthsListener {
//        override fun onSignalStrengthsChanged(signalStrength: SignalStrength) {
//            println("level -> ${signalStrength.level}")
//        }
//    }