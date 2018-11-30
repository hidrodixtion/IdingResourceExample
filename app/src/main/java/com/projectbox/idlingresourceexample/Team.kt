package com.projectbox.idlingresourceexample

/**
 * Created by adinugroho
 */
data class TeamResponse(
    val teams: List<Team>
)

data class Team(
    val strTeam: String
)