package kr.ac.kumoh.s20180074.sap_as1_pokerhandranking

class Card(id: Int) {
    companion object {
        const val SPADE = "spades"
        const val DIAMOND = "diamonds"
        const val HEART = "hearts"
        const val CLOVER = "clubs"
        const val JOKER = "joker"
        const val ERROR = "error"
        const val ACE = "ace"
        const val JACK = "jack"
        const val QUEEN = "queen"
        const val KING = "king"
    }

    private var _id = id
    val id get() = _id

    fun getCardName(): String {
        when(_id){
            -1 -> return "c_black_${JOKER}"
        }

        return "c_${getNumberString()}_of_${getShape()}"
    }

    fun getShape(): String {
        val shape = when (_id / 13) {
            0 -> SPADE
            1 -> DIAMOND
            2 -> HEART
            3 -> CLOVER
            else -> ERROR
        }

        return shape
    }

    fun getNumberString(): String {
        val number = when (_id % 13) {
            0 -> ACE
            in 1..9 -> "${_id % 13 + 1}"
            10 -> JACK
            11 -> QUEEN
            12 -> KING
            else -> ERROR
        }

        return number
    }

    fun getNumber() : Int{
        return _id % 13
    }
}