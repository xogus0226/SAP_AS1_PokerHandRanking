package kr.ac.kumoh.s20180074.sap_as1_pokerhandranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.s20180074.sap_as1_pokerhandranking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var _viewModel: PokerViewModel
    private lateinit var _mainActivity: ActivityMainBinding
    private lateinit var _listImageViews: Array<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewModel = ViewModelProvider(this)[PokerViewModel::class.java]
        _mainActivity = ActivityMainBinding.inflate(layoutInflater)
        _listImageViews = arrayOf(_mainActivity.card1, _mainActivity.card2, _mainActivity.card3, _mainActivity.card4, _mainActivity.card5)

        setContentView(_mainActivity.root)

        renderCards()

        _mainActivity.btnShuffle.setOnClickListener(this)
    }

    fun renderCards() {
        // view model 숫자 값의 해당하는 이미지를 화면에 렌더링 하기
        _listImageViews.forEachIndexed { index, imageView ->
            val resId = resources.getIdentifier(
                _viewModel.numbers[index].getCardName(), "drawable", packageName
            )
            imageView.setImageResource(resId)
        }

        // view model 숫자 값에 해당하는 족보를 화면에 렌더링 하기
        _mainActivity.title.text = _viewModel.cardRanking
    }

    override fun onClick(v: View?) {
        _viewModel.shuffle()
        renderCards()
    }
}