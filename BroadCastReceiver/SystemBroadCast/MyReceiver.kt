package kr.co.yeaeun.viewbasic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.TelephonyManager

class MyReceiver : BroadcastReceiver() {

    // 브로드캐스트 리시버가 인텐트로 인해 수행될 때 자동 호출
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        // 발생된 브로드캐스트 인텐트 정보 가져오기
        var action: String? = intent.action
        if(action.equals("android.intent.action.NEW_OUTGOING_CALL")){
            var phoneNumber:String? = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
            val intent1: Intent = Intent(context,DialogActivity::class.java)
            intent1.putExtra("number", phoneNumber)
            intent1.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent1)
        }else if(action.equals("android.intent.action.PHONE_STATE")){
            val bundle: Bundle? = intent.extras
            val state:String? = bundle!!.getString(TelephonyManager.EXTRA_STATE)
            val phoneNumber:String? = bundle!!.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING) && phoneNumber != null){
                val intent1: Intent = Intent(context, DialogActivity::class.java)
                intent1.putExtra("number", phoneNumber)
                intent1.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent1)
            }
        }
    }
}