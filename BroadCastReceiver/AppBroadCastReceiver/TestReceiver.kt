package kr.co.yeaeun.viewbasic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class TestReceiver : BroadcastReceiver() {

    // OS가 해당 브로드캐스트를 호출하면 자동으로 onReceive가 호출됨됨
   override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val str = "Broad Cast Receiver가 동작하였습니다."
        // context: 작업을 하기 위해 필요한 정보가 들어가는 것
        // Activity는 context를 상속받고 있기 때문에 this를 넣어주면 됨
        // but, BroadCast는 context를 상속받고 있지 않음. 대신 context 인자로 들어옴
        val t1 = Toast.makeText(context, str, Toast.LENGTH_SHORT)
        t1.show()
    }
}