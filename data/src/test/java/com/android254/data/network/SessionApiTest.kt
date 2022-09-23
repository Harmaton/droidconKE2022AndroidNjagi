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
package com.android254.data.network

import com.android254.data.network.apis.SessionApi
import com.android254.data.network.models.responses.GenericPaginatedResponse
import com.android254.data.network.models.responses.PaginationMetaData
import com.android254.data.network.models.responses.ResponseMetaData
import com.android254.data.network.models.responses.SessionApiModel
import com.android254.data.network.util.HttpClientFactory
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`

class SessionApiTest {
    @Test
    fun `sessions are fetched successfully`() {
        val expectedResponse: GenericPaginatedResponse<List<SessionApiModel>> =
            GenericPaginatedResponse(
                data = listOf(),
                meta = ResponseMetaData(
                    paginator = PaginationMetaData(
                        count = 1,
                        current_page = 1,
                        has_more_pages = true,
                        next_page = "",
                        next_page_url = "",
                        per_page = "",
                        previous_page_url = ""
                    )
                )
            )

        val mockHttpEngine = MockEngine {
            if (it.method == HttpMethod.Get && it.url.toString() == "${Constants.BASE_URL}/events/droidconke2019-444/sessions?per_page=20") {
                respond(
                    content = Json.encodeToString(expectedResponse),
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            } else {
                respondError(HttpStatusCode.NotFound)
            }
        }

        val httpClient = HttpClientFactory(MockTokenProvider()).create(mockHttpEngine)

        runBlocking {
            // WHEN
            val response = SessionApi(httpClient).fetchSessions()
            // THEN
            assertThat(response, `is`(expectedResponse))
        }
    }
}