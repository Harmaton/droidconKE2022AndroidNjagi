/*
 * Copyright 2022 DroidconKE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android254.data.util

import com.android254.data.network.models.responses.CreatorDataResponse
import com.android254.data.network.models.responses.OrganizerDataResponse
import com.android254.domain.models.CreatorDomainModel
import com.android254.domain.models.OrganizerDomainModel

object OrganizerDataToDomainMapper {

    private fun CreatorDataResponse.toDomain() = CreatorDomainModel(
        id = id, name = name, email = email, createdAt = createdAt
    )

    fun OrganizerDataResponse.toDomain() = OrganizerDomainModel(
        id = id,
        name = name,
        email = email,
        description = description,
        facebook = facebook,
        twitter = twitter,
        instagram = instagram,
        logo = logo,
        slug = slug,
        status = status,
        createdAt = createdAt,
        creater = creator?.toDomain(),
        upcomingEventsCount = upcomingEventsCount,
        totalEventsCount = totalEventsCount
    )
}