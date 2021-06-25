package com.practicaltest.reposnewapp.model.news

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("id", "name")
class Source {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("name")
    var name: String? = null

}