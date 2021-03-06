package com.esgi.nova.events.infrastructure.api.responses

import com.esgi.nova.events.infrastructure.api.models.ResumedEvent
import com.esgi.nova.events.ports.IDetailedEvent
import com.esgi.nova.infrastructure.api.pagination.ApiLink
import java.util.*

data class TranslatedEventsWithBackgroundResponse(
    override val id: UUID,
    override val title: String,
    override val description: String,
    val language: String,
    override val choices: MutableList<TranslatedChoiceResponse>,
    val backgroundUrl: ApiLink,
    override val isDaily: Boolean = false
) : IDetailedEvent {

    fun toResumedEvent() = ResumedEvent(
        choices = choices.map { it.toResumedChoice() }.toMutableList(),
        title = title,
        id = id,
        description = description,
        isDaily = isDaily
    )
}
