package com.example.minhapokedex.model

data class Root(
val data: List<Daum>,
val page: Long,
val pageSize: Long,
val count: Long,
val totalCount: Long,
)

data class Daum(
    val id: String,
    val name: String,
    val supertype: String,
    val subtypes: List<String>,
    val level: String?,
    val hp: String,
    val types: List<String>,
    val evolvesFrom: String?,
    val abilities: List<Ability>?,
    val attacks: List<Attack>?,
    val weaknesses: List<Weakness>?,
    val resistances: List<Resistance>?,
    val retreatCost: List<String>?,
    val convertedRetreatCost: Long?,
    val set: Set,
    val number: String,
    val artist: String,
    val rarity: String?,
    val flavorText: String?,
    val nationalPokedexNumbers: List<Long>,
    val legalities: Legalities2,
    val images: Images2,
    val tcgplayer: Tcgplayer?,
    val cardmarket: Cardmarket?,
    val evolvesTo: List<String>?,
    val rules: List<String>?,
    val regulationMark: String?,
)

data class Ability(
    val name: String,
    val text: String,
    val type: String,
)

data class Attack(
    val cost: List<String>,
    val name: String,
    val text: String,
    val damage: String,
    val convertedEnergyCost: Long,
)

data class Weakness(
    val type: String,
    val value: String,
)

data class Resistance(
    val type: String,
    val value: String,
)

data class Set(
    val id: String,
    val name: String,
    val series: String,
    val printedTotal: Long,
    val total: Long,
    val legalities: Legalities,
    val ptcgoCode: String?,
    val releaseDate: String,
    val updatedAt: String,
    val images: Images,
)

data class Legalities(
    val unlimited: String,
    val expanded: String?,
)

data class Images(
    val symbol: String,
    val logo: String,
)

data class Legalities2(
    val unlimited: String,
    val expanded: String?,
)

data class Images2(
    val small: String,
    val large: String,
)


data class Tcgplayer(
    val url: String,
    val updatedAt: String,
    val prices: Prices?,
)

data class Prices(
    val holofoil: Holofoil?,
    val n1stEditionHolofoil: n1stEditionHolofoil?,
    val unlimitedHolofoil: UnlimitedHolofoil?,
    val normal: Normal?,
    val reverseHolofoil: ReverseHolofoil?,
)

data class Holofoil(
    val low: Double,
    val mid: Double,
    val high: Double,
    val market: Double,
    val directLow: Double?,
)

data class n1stEditionHolofoil(
    val low: Double,
    val mid: Double,
    val high: Double,
    val market: Double,
    val directLow: Long?,
)

data class UnlimitedHolofoil(
    val low: Double,
    val mid: Double,
    val high: Double,
    val market: Double,
    val directLow: Double?,
)

data class Normal(
    val low: Double,
    val mid: Double,
    val high: Double,
    val market: Double?,
    val directLow: Double?,
)

data class ReverseHolofoil(
    val low: Double,
    val mid: Double,
    val high: Double,
    val market: Double,
    val directLow: Double?,
)

data class Cardmarket(
    val url: String,
    val updatedAt: String,
    val prices: Prices2,
)

data class Prices2(
    val averageSellPrice: Double,
    val lowPrice: Double,
    val trendPrice: Double,
    val germanProLow: Long,
    val suggestedPrice: Long,
    val reverseHoloSell: Double,
    val reverseHoloLow: Double,
    val reverseHoloTrend: Double,
    val lowPriceExPlus: Double,
    val avg1: Double,
    val avg7: Double,
    val avg30: Double,
    val reverseHoloAvg1: Double,
    val reverseHoloAvg7: Double,
    val reverseHoloAvg30: Double,
)


