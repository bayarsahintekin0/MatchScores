package com.bayarsahintekin.matchscores.util

import com.bayarsahintekin.matchscores.R

object TeamLogosObject {
    fun getTeamLogo(teamCode: String): Int {
        return when (teamCode) {
            "BOS" -> R.drawable.ic_logo_boston_celtics
            "BKN" -> R.drawable.ic_logo_brooklyn_nets
            "HOU" -> R.drawable.ic_logo_houston_rockets
            else -> R.drawable.ic_logo_boston_celtics
        }
    }

}