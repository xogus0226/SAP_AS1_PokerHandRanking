package kr.ac.kumoh.s20180074.sap_as1_pokerhandranking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import kotlin.random.Random

class PokerViewModel : ViewModel() {
    private var _numbers = MutableLiveData(IntArray(5) { -1 })
    val numbers: Array<Card>
        get() = _numbers.value!!.map { Card(it) }.toTypedArray()
    val cardRanking : String
        get(){
            if(_numbers.value!!.all{ it == -1 })
                return "????"
            else
                return HandRanking.getHandRanking(numbers)
        }

    fun shuffle() {
        var randNum = 0
        val newCards = IntArray(5) { -1 }
        for(i in newCards.indices){
            do{
                randNum = Random.nextInt(52)

            }while (newCards.contains(randNum))
            newCards[i] = randNum
        }
        newCards.sort()
        _numbers.value = newCards
    }
}