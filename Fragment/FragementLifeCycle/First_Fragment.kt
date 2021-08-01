package kr.co.yeaeun.viewbasic

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FirstFragment: Fragment() {

    // 프래그먼트가 액티비티와 연결될 때 호출
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("test", "onAttach")
    }

    // 프래그먼트가 생성될 때 호출(액티비티와 연결되고 프래그먼트 객체가 생성됨)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("test","onCreate")
    }

    // (*중요) 프래그먼트를 통해 보여줄 View를 생성하기 위해 호출
    // 여기에서 만든 View를 Fragment의 View로 사용한다.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 넘어온 LayoutInflater로 View 만들기
        val view = inflater.inflate(R.layout.fragment_first, null)
        Log.d("test","onCreateView")

        return view
    }

    // (*중요) 프래그먼트를 통해 보여줄 View가 생성되면 호출(onCreateView의 작업이 끝나면 호출)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("test","onViewCreated")
    }

    // 액티비티에서 보여줄 프래그먼트가 완전히 생성되면 호출된다.
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("test","onActivityCreated")
    }

    // 프래그먼트가 화면에 표시될 때(가동될 때) 호출
    override fun onStart() {
        super.onStart()
        Log.d("test","onStart")
    }

    // 프래그먼트가 보여지고 나서 호출
    // 여기까지 Fragment가 보이기 위한 일련의 과정
    override fun onResume() {
        super.onResume()
        Log.d("test","onResume")
    }

    // 프래그먼트가 일시정지될 때 호출(프래그먼트가 화면에서 사라질 때)
    override fun onPause() {
        super.onPause()
        Log.d("test","onPause")
    }

    // (*중요) 프래그먼트가 화면에서 완전히 사라져서 더 이상 보여지지 않을 때 호출
    // 프래그먼트 정지
    override fun onStop() {
        super.onStop()
        Log.d("test","onStop")
    }

    // (*중요) 프래그먼트가 제거될 때 호출된다.(나타내진 Activity가 메모리상에서 사라질 때)
    // * (backstack에 프래그먼트 삽입시)remove메소드 또는 백버튼을 이용해서 Fragment를 사라지게 만든 것은 Fragment를 완전히 제거시킨 것이 아님. 단지 화면에 안 보일 뿐.
    // 사라진 상태에서 백버튼을 누르면 제거됨(onDestroy 동작)
    override fun onDestroy() {
        super.onDestroy()
        Log.d("test","onDestroy")
    }

    // 프래그먼트가 액티비티와 연결이 완전히 끊기기 전에 호출된다.
    override fun onDetach() {
        super.onDetach()
        Log.d("test","onDetach")
    }
}
