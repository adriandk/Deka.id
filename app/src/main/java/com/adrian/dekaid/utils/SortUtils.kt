package com.adrian.dekaid.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "NEWEST"
    const val OLDEST = "OLDEST"

    const val MOVIE_TABLE = "movie"
    const val SHOW_TABLE = "show"

    fun getSortedQuery(filter: String, table_name: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $table_name ")
        when (filter) {
            NEWEST -> simpleQuery.append("ORDER BY releaseDate DESC")
            OLDEST -> simpleQuery.append("ORDER BY releaseDate ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

}