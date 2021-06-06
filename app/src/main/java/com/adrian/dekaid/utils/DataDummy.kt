package com.adrian.dekaid.utils

import com.adrian.dekaid.data.source.model.MovieData
import com.adrian.dekaid.data.source.remote.response.MoviesResponse

object DataDummy {

    fun getMovieData(): List<MovieData> {
        val imageUrl = "https://image.tmdb.org/t/p/w500"
        return listOf(
            MovieData(
                337404,
                "Cruella",
                8.8,
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021",
                "$imageUrl/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                134,
                0,
                "",
                ""
            ),
            MovieData(
                632357,
                "The Unholy",
                7.1,
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "2021",
                "$imageUrl/6wxfWZxQcuv2QgxIQKj0eYTdKTv.jpg",
                99,
                0,
                "",
                ""
            ),
            MovieData(
                503736,
                "Army of the Dead",
                6.6,
                "Following a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble: venturing into the quarantine zone to pull off the greatest heist ever attempted.",
                "2021",
                "$imageUrl/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                148,
                0,
                "",
                ""
            )
        )
    }

    fun getShowData(): List<MovieData> {
        val imageUrl = "https://image.tmdb.org/t/p/w500"
        return listOf(
            MovieData(
                63174,
                "",
                8.5,
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "",
                "$imageUrl/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                0,
                6,
                "2016",
                "Lucifer"
            ),
            MovieData(
                91557,
                "",
                8.0,
                "A small Norwegian town experiencing warm winters and violent downpours seems to be headed for another Ragnarök -- unless someone intervenes in time.",
                "",
                "$imageUrl/xUtOM1QO4r8w8yeE00QvBdq58N5.jpg",
                0,
                2,
                "2020",
                "Ragnarok"
            ),
            MovieData(
                60735,
                "",
                7.7,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "",
                "$imageUrl/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                0,
                7,
                "2014",
                "The Flash"
            )
        )
    }

    fun getRemoteMovie(): List<MoviesResponse> {
        val imageUrl = "https://image.tmdb.org/t/p/w500"
        return listOf(
            MoviesResponse(
                503736,
                "$imageUrl/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "Army of the Dead",
                "Following a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble: venturing into the quarantine zone to pull off the greatest heist ever attempted.",
                "2021-05-14",
                148,
                0,
                6.6,
                "",
                ""
            ),
            MoviesResponse(
                632357,
                "$imageUrl/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "The Unholy",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "2021-03-31",
                99,
                0,
                7.1,
                "",
                ""
            ),
            MoviesResponse(
                337404,
                "$imageUrl/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26",
                134,
                0,
                8.8,
                "",
                ""
            )
        )
    }

    fun getRemoteShow(): List<MoviesResponse> {
        val imageUrl = "https://image.tmdb.org/t/p/w500"

        return listOf(
            MoviesResponse(
                63174,
                "$imageUrl/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "",
                0,
                6,
                8.5,
                "2016-01-25",
                "Lucifer"
            ),
            MoviesResponse(
                91557,
                "$imageUrl/xUtOM1QO4r8w8yeE00QvBdq58N5.jpg",
                "",
                "A small Norwegian town experiencing warm winters and violent downpours seems to be headed for another Ragnarök -- unless someone intervenes in time.",
                "",
                0,
                2,
                8.0,
                "2020-01-31",
                "Ragnarok"
            ),
            MoviesResponse(
                60735,
                "$imageUrl/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "",
                0,
                7,
                7.7,
                "2014-10-07",
                "The Flash"
            )
        )
    }

}