package com.totonero.dashboard.integration.alert.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class StatsResponseDTO(
    @JsonProperty("data")
    val matchs: List<MatchStat> = listOf(),
    val message: String = ""
)

data class MatchStat(
    val matchId: String = "",
    val stats: List<Stats> = listOf(),
    var match: Match = Match()
)

data class Stats(
    @JsonProperty("type")
    val id: Int = 0,
    var home: String = "",
    var away: String = "",
    var typeStat: TypeStat = TypeStat.BALL_POSSESSION
) {
    init {
        typeStat = TypeStat.getTypeStatById(id)
    }
}

enum class TypeStat(
    val id: Int,
    val description: String
) {
    FIRST_KICK(0, "First kick"),
    FIRST_CORNER(1, "First corner"),
    FIRST_YELLOW_CARD(2, "First yellow card"),
    KICK(3, "Total shots"),
    SHOTS_ON_TARGET(4, "Shots on target"),
    FOULS(5, "Fouls"),
    CORNER(6, "Corner"),
    CORNER_OVERTIME(7, "Corner overtime"),
    FREE_KICK(8, "Free kick"),
    OFFSIDE(9, "Offside"),
    OWN_GOAL(10, "Own goal"),
    YELLOW_CARD(11, "Yellow card"),
    YELLOW_CARD_OVERTIME(12, "Yellow card overtime"),
    RED_CARD(13, "Red card"),
    BALL_POSSESSION(14, "Ballpossession%"),
    HEADER(15, "Header"),
    SAVE(16, "Save"),
    GOALKEEPER_COME_OUT(17, "Goalkeeper come out"),
    DISPOSSESSED(18, "Dispossessed"),
    SUCCESSFUL_INTERCEPT(19, "Successful intercept"),
    CHALLENGE(20, "Challenge"),
    LONG_PASS(21, "Long pass"),
    SHORT_PASS(22, "Short pass"),
    ASSIST(23, "Assist"),
    SUCCESSFUL_CENTER(24, "Successful center"),
    FIRST_SUBSTITUTION(25, "First substitution"),
    LAST_SUBSTITUTION(26, "Last substitution"),
    FIRST_OFFSIDE(27, "First offside"),
    LAST_OFFSIDE(28, "Last offisde"),
    SUBSTITUTION(29, "Substitution"),
    LAST_CORNER(30, "Last corner"),
    LAST_YELLOW_CARD(31, "Last yellow card"),
    SUBSTITUTION_OVERTIME(32, "Substitution Overtime"),
    OFFSIDE_OVERTIME(33, "Offside overtime"),
    SHOTS_OFF_TARGET(34, "Shots off target"),
    WOODWORK(35, "Woodwork"),
    SUCCESSFUL_HEADER(36, "Successful header"),
    SHOT_BLOCKED(37, "Shot blocked"),
    TACKLE(38, "Tackle"),
    BEAT(39, "Beat"),
    THROW_IN(40, "Throw in"),
    PASSES(41, "Passes"),
    PASSES_SUCCESS(42, "Passes success"),
    ATTACKS(43, "Attacks"),
    DANGEROUS_ATTACKS(44, "Dangerous attacks"),
    FIRST_HALF_CORNER(45, "First Half corner"),
    FIRST_HALF_POSSESSION(46, "First Half Possession%");

    companion object {
        fun getTypeStatById(id: Int) : TypeStat {
            for (type in values()) {
                if (type.id == id)
                    return type
            }
            throw Exception("Não foi encontrado TypeStat para id:$id informado")
        }

        fun getAnalyzableList(): List<String> {
            return listOf(BALL_POSSESSION.name, KICK.name, CORNER.name, RED_CARD.name)
        }
    }
}