package com.bayarsahintekin.matchscores.util

import com.bayarsahintekin.matchscores.R

object TeamLogosObject {
    fun getTeamLogo(teamCode: String): Int {
        return when (teamCode) {
            "ATL"-> R.drawable.atlanta_hawks
            "BOS" -> R.drawable.ic_logo_boston_celtics
            "BKN" -> R.drawable.ic_logo_brooklyn_nets
            "CHA" -> R.drawable.charlotte_hornets
            "CHI" -> R.drawable.chicago_bulls
            "CLE" -> R.drawable.cleveland_cavaliers
            "DAL" -> R.drawable.dallas_mavericks
            "DEN" -> R.drawable.denver_nuggets
            "DET" -> R.drawable.detroit_pistons
            "GSW" -> R.drawable.golden_state_warriors
            "HOU" -> R.drawable.ic_logo_houston_rockets
            "IND" -> R.drawable.indiana_pacers
            "LAC" -> R.drawable.los_angeles_clippers
            "LAL" -> R.drawable.los_angeles_lakers
            "MEM" -> R.drawable.memphis_grizzlies
            "MIA" -> R.drawable.miami_heat
            "MIL" -> R.drawable.milwaukee_bucks
            "MIN" -> R.drawable.minnesota_timberwolves
            "NOP" -> R.drawable.new_orleans_pelicans
            "NYK" -> R.drawable.new_york_knicks
            "OKC" -> R.drawable.oklahoma_thunders
            "ORL" -> R.drawable.orlando_magic
            "PHI" -> R.drawable.philadelphia_76ers
            "PHX" -> R.drawable.phoenix_suns
            "POR" -> R.drawable.portland_trail_blazers
            "SAC" -> R.drawable.sacramento_kings
            "SAS" -> R.drawable.san_antonio_spurs
            "TOR" -> R.drawable.toronto_raptors
            "UTA" -> R.drawable.utah_jazz
            "WAS" -> R.drawable.washington_wizards
            else -> R.drawable.ic_team_default
        }
    }

}