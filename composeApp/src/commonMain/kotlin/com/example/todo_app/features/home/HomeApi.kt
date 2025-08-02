package com.example.todo_app.features.home

import com.example.todo_app.core.api.KTorInstance
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

object HomeApi {
   private val client = KTorInstance.client;

   suspend fun getPosts (page: Int, limit: Int): HttpResponse {
      return client.get("/posts?_page=${page}&_limit=${limit}")
   }

   suspend fun update (body: HomeModal):HttpResponse {
      return client.put("/posts/${body.id}"){
         setBody(body)
      }
   }

   suspend fun delete (id: Int):HttpResponse {
      return client.delete("/posts/${id}")
   }


   suspend fun create (body: CreatePostModal):HttpResponse {
      return client.post("/posts/"){
         setBody(body)
      }
   }
}