package com.vinteque.back_mmakome.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@Table(name = "user_permissions")
data class UserPermission(

    @EmbeddedId
    val id: UserPermissionId,

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id")
    val permission: Permission
)

@Embeddable
data class UserPermissionId(
    @Column(name = "user_id")
    val userId: Long,
    @Column(name = "permission_id")
    val permissionId: Long
): Serializable