package com.esgi.nova.games.application

import com.esgi.nova.difficulties.ports.IDetailedDifficulty
import com.esgi.nova.files.application.model.FileWrapper
import com.esgi.nova.files.infrastructure.ports.IFileWrapper
import com.esgi.nova.games.application.models.GameResource
import com.esgi.nova.games.application.models.ResumedGameWithResourceIcons
import com.esgi.nova.games.ports.IResumedGame
import com.esgi.nova.resources.ports.IResource
import java.util.*

fun IDetailedDifficulty.getGameResources(gameId: UUID) = this.resources.map { resource ->
    GameResource(resourceId = resource.id, total = resource.startValue, gameId = gameId)
}

fun IResumedGame.toResumedGameWithResourceIcons(resourceWrappers: List<IFileWrapper<IResource>>) =
    ResumedGameWithResourceIcons(
        id = this.id,
        resources = this.resources.mapNotNull { resource ->
            resourceWrappers.firstOrNull { resourceWrapper ->
                resourceWrapper.data.id == resource.id
            }?.let { resourceWrapper ->
                FileWrapper(resource, resourceWrapper.img)
            }
        },
        duration = this.duration,
        rounds = this.rounds
    )
