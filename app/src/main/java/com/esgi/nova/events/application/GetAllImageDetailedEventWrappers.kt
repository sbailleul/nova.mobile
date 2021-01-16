package com.esgi.nova.events.application

import com.esgi.nova.events.infrastructure.data.events.EventDbRepository
import com.esgi.nova.events.ports.IDetailedEvent
import com.esgi.nova.files.application.GetFileBitmapById
import com.esgi.nova.files.application.model.FileWrapper
import com.esgi.nova.files.infrastructure.ports.IFileWrapper
import com.esgi.nova.infrastructure.fs.FsConstants
import javax.inject.Inject

class GetAllImageDetailedEventWrappers @Inject constructor(
    private val eventDbRepository: EventDbRepository,
    private val getFileBitmapById: GetFileBitmapById
) {

    fun execute(): List<IFileWrapper<IDetailedEvent>> {
        return eventDbRepository
            .getAllDetailedEvent()
            .mapNotNull { event ->
                getFileBitmapById.execute(FsConstants.Paths.Events, event.id)?.let { img ->
                    return@mapNotNull FileWrapper(event, img)
                }
                return@mapNotNull null
            }.toList()
    }
}