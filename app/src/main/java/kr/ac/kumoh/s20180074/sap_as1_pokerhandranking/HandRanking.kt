package kr.ac.kumoh.s20180074.sap_as1_pokerhandranking

class HandRanking {
    companion object{
        const val TOP = "탑(뻥카)"
        const val ONE_FAIR = "원페어"
        const val TWO_PAIR = "투페어"
        const val TRIPLE = "트리플"
        const val STRAIGHT = "스트레이트"
        const val BACK_STRAIGHT = "백스트레이트"
        const val MOUNTAIN = "마운틴"
        const val FLUSH = "플러쉬"
        const val FULL_HOUSE = "풀하우스"
        const val FOUR_CARD = "포카드"
        const val STRAIGHT_FLUSH = "스트레이트 플러쉬"
        const val BACK_STRAIGHT_FLUSH = "백 스트레이트 플러쉬"
        const val ROYAL_STRAIGHT_FLUSH = "로열 스트레이트 플러쉬"

        fun getHandRanking(hand : Array<Card>) : String{
            // 카드들을 번호 순으로 정렬
            val sortedHand = hand.sortedBy { it.getNumber() }

            // 같은 무늬의 카드가 5장인지 확인(플러쉬)
            val isFlush = hand.map { it.getShape() }.toSet().size == 1

            // 카드 번호들의 연속성 확인(스트레이트)
            val isStraight = sortedHand
                .zipWithNext() // 인접한 두 개의 원소를 묶고, 이것을 원소로 갖는 리스트 반환
                .all { (a, b) -> a.getNumber() + 1 == b.getNumber() } // 리스트의 모든 원소가 람다식을 만족하는지 검사

            // 카드 번호들의 빈도수를 구함 (원페어, 투페어, 트리플, 포카드, 풀하우스 관련)
            val frequencyMap = sortedHand
                .groupingBy { it.getNumber() }
                .eachCount()
            // groupingBy() => 그루핑 객체를 반환 => (카드번호 / 이 카드번호 요소들의 리스트 형태로 그룹화 해줌)
            // eachCount() => 카드번호를 키로, 리스트 원소의 개수를 값으로 가지는 map을 반환
            // eg. (4,4,4,4,7)일 때, eachCount()의 결과는 {4:4, 7:1}인 map이 됨

            // 족보 판별
            return when {
                isFlush && isStraight && sortedHand.last().getNumberString() == Card.KING -> ROYAL_STRAIGHT_FLUSH
                isFlush && isStraight -> if (sortedHand.first().getNumberString() == Card.ACE) BACK_STRAIGHT_FLUSH else STRAIGHT_FLUSH
                frequencyMap.containsValue(4) -> FOUR_CARD
                frequencyMap.containsValue(3) && frequencyMap.containsValue(2) -> FULL_HOUSE
                isFlush -> FLUSH
                isStraight && sortedHand.last().getNumberString() == Card.KING -> MOUNTAIN
                isStraight -> if (sortedHand.first().getNumberString() == Card.ACE) BACK_STRAIGHT else STRAIGHT
                frequencyMap.containsValue(3) -> TRIPLE
                frequencyMap.filterValues { it == 2 }.size == 2 -> TWO_PAIR
                frequencyMap.containsValue(2) -> ONE_FAIR
                else -> TOP
            }
        }
    }

}