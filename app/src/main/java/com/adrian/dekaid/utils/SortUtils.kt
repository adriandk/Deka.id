package com.adrian.dekaid.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val BEST_VOTE = "BEST"
    const val WORST_VOTE = "WORST"
    const val RANDOM = "RANDOM"

    const val MOVIE_TABLE = "movie"
    const val SHOW_TABLE = "show"

    fun getSortedQuery(filter: String, table_name: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $table_name ")
        when (filter) {
            BEST_VOTE -> simpleQuery.append("ORDER BY fansVote DESC")
            WORST_VOTE -> simpleQuery.append("ORDER BY fansVote ASC")
            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

}