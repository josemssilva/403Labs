package pt.josemssilva.labs403

import android.content.Context
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import pt.josemssilva.labs403.databinding.ActivityDeviceInfoBinding


class DeviceInfoActivty : AppCompatActivity() {

//    private var mTotalRxBytes = TrafficStats.getTotalRxBytes()
//    private lateinit var mHandler: Handler
//
//    private val runnable = object : Runnable {
//        override fun run() {
//            val mrx = TrafficStats.getMobileRxBytes() / 1024 // Mobile Wifi
//            val mtx = TrafficStats.getMobileTxBytes() / 1024 // Mobile
//            val trx = ((TrafficStats.getTotalRxBytes() - mTotalRxBytes) * 1.00f / 1024).toLong()
//            mTotalRxBytes = TrafficStats.getTotalRxBytes() // Mobile WiFi
//            val ttx = TrafficStats.getTotalTxBytes() / 1024 // Mobile WiFi
//            val uidrx =
//                TrafficStats.getUidRxBytes(applicationInfo.uid) / 1024
//            val uidtx =
//                TrafficStats.getUidTxBytes(applicationInfo.uid) / 1024
//            val sb = StringBuilder()
//            sb.append("mrx:$mrx\n\r")
//                .append("mtx:$mtx\n\r")
//                .append("trx:$trx\n\r")
//                .append("ttx:$ttx\n\r")
//                .append("uidrx:$uidrx\n\r")
//                .append("uidtx:$uidtx\n\r")
//
//            mHandler.postDelayed(this, 1000)
//        }
//    }

    private var mTelephonyManager: TelephonyManager? = null
    private lateinit var binding: ActivityDeviceInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        mHandler = Handler(mainLooper)
//        mHandler.post(runnable)

        binding = ActivityDeviceInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        mHandler.sendEmptyMessageDelayed(0, 0)
        mTelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?

        //LISTEN_SIGNAL_STRENGTHS  Listen for changes to the network signal strengths (cellular).
        mTelephonyManager?.listen(
            CustomPhoneStateListener {
                binding.textview.text = it
            },
            PhoneStateListener.LISTEN_SIGNAL_STRENGTHS or PhoneStateListener.LISTEN_SERVICE_STATE
        )
    }

    override fun onDestroy() {
        super.onDestroy()
//        mHandler.removeCallbacksAndMessages(null)
    }
}