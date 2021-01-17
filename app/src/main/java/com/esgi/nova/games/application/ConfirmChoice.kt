package com.esgi.nova.games.application

import com.esgi.nova.events.infrastructure.data.choice_resource.ChoiceResourceDbRepository
import com.esgi.nova.events.infrastructure.data.choices.ChoiceDbRepository
import com.esgi.nova.games.application.models.Game
import com.esgi.nova.games.application.models.GameResource
import com.esgi.nova.games.infrastructure.api.GameApiRepository
import com.esgi.nova.games.infrastructure.data.game.GameDbRepository
import com.esgi.nova.games.infrastructure.data.game_event.GameEventDbRepository
import com.esgi.nova.games.infrastructure.data.game_resource.GameResourceDbRepository
import java.util.*
import javax.inject.Inject

class ConfirmChoice @Inject constructor(
    private val gameDbRepository: GameDbRepository,
    private val gameApiRepository: GameApiRepository,
    private val gameResourceDbRepository: GameResourceDbRepository,
    private val choiceResourceDbRepository: ChoiceResourceDbRepository
) {

    fun execute(gameId: UUID, choiceId: UUID, duration: Int): Boolean {
        var isEnded = false

        gameDbRepository.getById(gameId)?.let { game ->
            choiceResourceDbRepository.getDetailedChoiceById(choiceId)?.let { choice ->

                choice.resources.forEach { resource ->
                    gameResourceDbRepository.getById(gameId)?.let { gameResource ->
                        val updateResourceValue = gameResource.total + resource.changeValue
                        gameResourceDbRepository.update(
                            GameResource(
                                resourceId = resource.id,
                                gameId = gameId,
                                total = updateResourceValue
                            )
                        )
                        if (updateResourceValue <= 0) {
                            isEnded = true
                        }
                    }
                }

                gameDbRepository.update(
                    Game(
                        id = gameId,
                        difficultyId = game.difficultyId,
                        isEnded = isEnded,
                        duration = duration
                    )
                )

                gameDbRepository.getGameEditionById(gameId)?.let { game ->
                    gameApiRepository.update(gameId, game)
                }

            }
        }
        return isEnded
    }
}
