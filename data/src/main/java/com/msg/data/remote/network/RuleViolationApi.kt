package com.msg.data.remote.network

import com.msg.data.remote.dto.rule_violation.requset.RuleViolationRequest
import com.msg.data.remote.dto.rule_violation.response.RuleViolationResponse
import com.msg.data.remote.dto.rule_violation.response.SearchRuleViolationResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RuleViolationApi {
    @GET("/{role}/rule/{stu_num}")
    suspend fun getDetailRuleViolation(
        @Path("role") role: String,
        @Path("stu_num") stuNum: String
    ): List<RuleViolationResponse>

    @GET("/{role}/rule")
    suspend fun getSelfRuleViolation(
        @Path("role") role: String
    ): List<RuleViolationResponse>

    @GET("/{role}/rule")
    suspend fun searchRuleViolation(
        @Path("role") role: String,
        @Query("memberName") memberName: String,
        @Query("stuNum") stuNum: String,
        @Query("gender") gender: String
    ): List<SearchRuleViolationResponse>

    @POST("/{role}/rule")
    suspend fun postRuleViolation(
        @Path("role") role: String,
        @Body ruleViolationRequest: RuleViolationRequest
    )

    @DELETE("/{role}/rule/{rule_id}")
    suspend fun deleteRuleViolation(
        @Path("role") role: String,
        @Path("rule_id") ruleId: String
    )
}