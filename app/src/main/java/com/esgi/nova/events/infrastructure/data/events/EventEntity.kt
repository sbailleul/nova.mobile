package com.esgi.nova.events.infrastructure.data.events

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.esgi.nova.events.ports.IEvent
import com.esgi.nova.infrastructure.data.UUIDConverter
import java.util.*

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey()
    @field:TypeConverters(UUIDConverter::class)
    override val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "description") override val description: String,
    @ColumnInfo(name = "title") override val title: String
): IEvent