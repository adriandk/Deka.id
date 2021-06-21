package com.adrian.dekaid.utils

import com.adrian.dekaid.data.source.local.entity.MovieEntity
import com.adrian.dekaid.data.source.local.entity.ShowEntity
import com.adrian.dekaid.data.source.remote.response.MoviesResponse

object DataDummy {

    fun getMovieData(): List<MovieEntity> {
        return listOf(
            MovieEntity(
                337404,
                "Cruella",
                8.7,
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021",
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                134,
                false
            ),
            MovieEntity(
                447362,
                "Life in a Year",
                8.5,
                "A 17 year old finds out that his girlfriend is dying, so he sets out to give her an entire life, in the last year she has left.",
                "2020",
                "/bP7u19opmHXYeTCUwGjlLldmUMc.jpg",
                107,
                false
            ),
            MovieEntity(
                761053,
                "Gabriel's Inferno Part III",
                8.7,
                "The final part of the film adaption of the erotic romance novel Gabriel's Inferno written by an anonymous Canadian author under the pen name Sylvain Reynard.  Watch now : https://stream4k.xyz",
                "2020",
                "/fYtHxTxlhzD4QWfEbrC1rypysSD.jpg",
                105,
                false
            )
        )
    }

    fun getShowData(): List<ShowEntity> {
        return listOf(
            ShowEntity(
                108261,
                "Mr. Queen",
                9.0,
                "In the modern age, Jang Bong-hwan is a chef who works for the country's top politicians at the Blue House. He has a free spirit, but he one day finds himself in the body of Queen Cheorin in the Joseon period.",
                "2020",
                "/vP6jiaPmzgHF5YyvrkK44PJGmyR.jpg",
                1,
                false
            ),
            ShowEntity(
                100,
                "I Am Not an Animal",
                9.3,
                "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
                "2004",
                "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
                1,
                false
            ),
            ShowEntity(
                88040,
                "Given",
                9.1,
                "Tightly clutching his Gibson guitar, Mafuyu Satou steps out of his dark apartment to begin another day of his high school life. While taking a nap in a quiet spot on the gymnasium staircase, he has a chance encounter with fellow student Ritsuka Uenoyama, who berates him for letting his guitar's strings rust and break. Noticing Uenoyama's knowledge of the instrument, Satou pleads for him to fix it and to teach him how to play. Uenoyama eventually agrees and invites him to sit in on a jam session with his two band mates: bassist Haruki Nakayama and drummer Akihiko Kaji.",
                "2019",
                "/pdDCcAq8RNSZNk81PXYoHNUPHjn.jpg",
                1,
                false
            )
        )
    }

    fun getMovieDetail(): MovieEntity {
        return MovieEntity(
            337404,
            "Cruella",
            8.7,
            "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            "2021",
            "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
            134,
            false
        )
    }

    fun getShowDetail(): ShowEntity {
        return ShowEntity(
            108261,
            "Mr. Queen",
            9.0,
            "In the modern age, Jang Bong-hwan is a chef who works for the country's top politicians at the Blue House. He has a free spirit, but he one day finds himself in the body of Queen Cheorin in the Joseon period.",
            "2020",
            "/vP6jiaPmzgHF5YyvrkK44PJGmyR.jpg",
            1,
            false
        )
    }

    fun getRemoteMovie(): List<MoviesResponse> {
        return listOf(
            MoviesResponse(
                337404,
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26",
                134,
                0,
                8.7,
                "",
                ""
            ),
            MoviesResponse(
                447362,
                "/bP7u19opmHXYeTCUwGjlLldmUMc.jpg",
                "Life in a Year",
                "A 17 year old finds out that his girlfriend is dying, so he sets out to give her an entire life, in the last year she has left.",
                "2020-11-27",
                107,
                0,
                8.5,
                "",
                ""
            ),
            MoviesResponse(
                761053,
                "/fYtHxTxlhzD4QWfEbrC1rypysSD.jpg",
                "Gabriel's Inferno Part III",
                "The final part of the film adaption of the erotic romance novel Gabriel's Inferno written by an anonymous Canadian author under the pen name Sylvain Reynard.  Watch now : https://stream4k.xyz",
                "2020-11-19",
                105,
                0,
                8.7,
                "",
                ""
            )
        )
    }

    fun getRemoteShow(): List<MoviesResponse> {
        return listOf(
            MoviesResponse(
                108261,
                "/ozuyMnOO5pekDklyPpUL1Htkuzy.jpg",
                "",
                "In the modern age, Jang Bong-hwan is a chef who works for the country's top politicians at the Blue House. He has a free spirit, but he one day finds himself in the body of Queen Cheorin in the Joseon period.",
                "",
                0,
                1,
                9.0,
                "2016-01-25",
                "Mr. Queen"
            ),
            MoviesResponse(
                100,
                "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
                "",
                "A small Norwegian town experiencing warm winters and violent downpours seems to be headed for another Ragnarök -- unless someone intervenes in time.",
                "",
                0,
                1,
                9.3,
                "2004-05-10",
                "I Am Not an Animal"
            ),
            MoviesResponse(
                88040,
                "/pdDCcAq8RNSZNk81PXYoHNUPHjn.jpg",
                "",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "",
                0,
                1,
                9.1,
                "2019-07-12",
                "Given"
            )
        )
    }

    fun getRemoteMovieDetail(): MoviesResponse {
        return MoviesResponse(
            337404,
            "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
            "Cruella",
            "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            "2021-05-26",
            134,
            0,
            8.7,
            "",
            ""
        )
    }

    fun getRemoteShowDetail(): MoviesResponse {
        return MoviesResponse(
            108261,
            "/ozuyMnOO5pekDklyPpUL1Htkuzy.jpg",
            "",
            "In the modern age, Jang Bong-hwan is a chef who works for the country's top politicians at the Blue House. He has a free spirit, but he one day finds himself in the body of Queen Cheorin in the Joseon period.",
            "",
            0,
            1,
            9.0,
            "2016-01-25",
            "Mr. Queen"
        )
    }

}