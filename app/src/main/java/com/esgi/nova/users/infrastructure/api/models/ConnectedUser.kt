package com.esgi.nova.users.infrastructure.api.models

import com.esgi.nova.models.Role
import com.esgi.nova.users.ports.IConnectedUser
import java.util.*

data class ConnectedUser(
    override val email: String,
    override val role: Role,
    override val username: String,
    override val token: String,
    override val id: UUID
) : IConnectedUser
